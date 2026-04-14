package com.example.timerzadanie;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnNr1, btnNr2, btnNr3, btnNr4, btnNr5;
    TextView textViewTimer;

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
    }
}

