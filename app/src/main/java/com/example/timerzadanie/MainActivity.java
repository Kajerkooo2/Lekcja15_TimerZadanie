package com.example.timerzadanie;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textViewTimer;
    private Button[] buttons;
    private Random random;
    private Handler handler;
    private Runnable runnable;
    private int currentNumber;

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


        textViewTimer = findViewById(R.id.textViewTimer);
        buttons = new Button[] {
                findViewById(R.id.btnNr1),
                findViewById(R.id.btnNr2),
                findViewById(R.id.btnNr3),
                findViewById(R.id.btnNr4),
                findViewById(R.id.btnNr5)
        };
        random = new Random();

        for (Button button : buttons) {
            button.setText(String.valueOf(random.nextInt(10) + 1));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Integer.parseInt(((Button) v).getText().toString()) == currentNumber) {
                        v.setEnabled(false);
                        v.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    }
                }
            });
        }

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                currentNumber = random.nextInt(10) + 1;
                textViewTimer.setText(String.valueOf(currentNumber));
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(runnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}

