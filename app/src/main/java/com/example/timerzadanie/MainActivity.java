package com.example.timerzadanie;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;
import android.app.AlertDialog;
import android.graphics.Color;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Button btnNr1, btnNr2, btnNr3, btnNr4, btnNr5;
    TextView textViewTimer;
    Handler handler;
    Random random;
    boolean gameRunning = true;
    int score = 0;
    int attempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnNr1 = findViewById(R.id.btnNr1);
        btnNr2 = findViewById(R.id.btnNr2);
        btnNr3 = findViewById(R.id.btnNr3);
        btnNr4 = findViewById(R.id.btnNr4);
        btnNr5 = findViewById(R.id.btnNr5);
        textViewTimer = findViewById(R.id.textViewTimer);

        handler = new Handler(Looper.getMainLooper());
        random = new Random();
        startTimer();

        setUniqueButtonNumbers();

        btnNr1.setOnClickListener(v -> checkMatch(btnNr1));
        btnNr2.setOnClickListener(v -> checkMatch(btnNr2));
        btnNr3.setOnClickListener(v -> checkMatch(btnNr3));
        btnNr4.setOnClickListener(v -> checkMatch(btnNr4));
        btnNr5.setOnClickListener(v -> checkMatch(btnNr5));
    }

    private void startTimer() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (gameRunning) {
                    int randomNumber = random.nextInt(10) + 1;
                    textViewTimer.setText(String.valueOf(randomNumber));
                    handler.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }

    private void checkMatch(Button button) {
        if (!button.isEnabled()) {
            return;
        }

        int textViewValue = Integer.parseInt(textViewTimer.getText().toString());
        int buttonValue = Integer.parseInt(button.getText().toString());
        attempts++;

        if (textViewValue == buttonValue) {
            button.setBackgroundColor(Color.GREEN);
            score++;
        } else {
            button.setBackgroundColor(Color.RED);
        }

        button.setEnabled(false);


        if (attempts == 5) {
            if (score >= 3) {
                endGame(true);
            } else {
                endGame(false);
            }
        }
    }

    private void endGame(boolean isWin) {
        gameRunning = false;
        handler.removeCallbacksAndMessages(null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (isWin) {
            builder.setTitle("KONIEC GRY - WYGRANA");
            builder.setMessage("Gratulacje! Zdobyles " + score + "/5 punktów.");
        } else {
            builder.setTitle("KONIEC GRY - PRZEGRANA");
            builder.setMessage("Niestety, zdobyles tylko " + score + "/5 punktów.\nPotrzebujesz co najmniej 3, aby wygrać.");
        }

        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.setCancelable(false);
        builder.show();
    }

    private void setUniqueButtonNumbers() {
        Set<Integer> usedNumbers = new HashSet<>();
        Button[] buttons = {btnNr1, btnNr2, btnNr3, btnNr4, btnNr5};

        for (Button button : buttons) {
            int randomNumber;
            do {
                randomNumber = random.nextInt(10) + 1;
            } while (usedNumbers.contains(randomNumber));

            usedNumbers.add(randomNumber);
            button.setText(String.valueOf(randomNumber));
        }
    }
}