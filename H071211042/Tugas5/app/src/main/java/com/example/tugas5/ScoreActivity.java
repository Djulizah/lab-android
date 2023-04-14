package com.example.tugas5;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    ActivityResultLauncher<Intent> resultActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        TextView score, bestScore;
        Button btnRestart;

        score = findViewById(R.id.currentScore);
        bestScore = findViewById(R.id.bestScore);
        btnRestart = findViewById(R.id.btnRestart);

        btnRestart.setOnClickListener(view -> {

            setResult(RESULT_OK, new Intent());
            finish();
        });


        score.setText(getIntent().getExtras().getString("status"));
        bestScore.setText(getIntent().getExtras().getString("best"));




    }
}