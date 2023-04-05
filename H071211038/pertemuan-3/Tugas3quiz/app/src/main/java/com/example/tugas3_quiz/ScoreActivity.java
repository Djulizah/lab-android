package com.example.tugas3_quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class ScoreActivity extends AppCompatActivity {
    private TextView tvScoreResult, tvBestScoreResult, tvBestScore, tvNameResult;
    private Button btnBack;

    private final String KEY_BEST_SCORE = "best score";
    private final String KEY_NEW_SCORE = "new score";
    private final String KEY_NAME = "name";
    private static int maxScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        btnBack = findViewById(R.id.btn_back);
        tvScoreResult = findViewById(R.id.score_result);
        tvBestScoreResult = findViewById(R.id.best_score_result);
        tvBestScore = findViewById(R.id.text_best_score);
        tvNameResult = findViewById(R.id.name_result);

        int newScore = Integer.parseInt(getIntent().getStringExtra(KEY_NEW_SCORE));
        String name = getIntent().getStringExtra(KEY_NAME);

        if ( maxScore < newScore && newScore > 0) {
            maxScore = newScore;
            tvBestScore.setText("New Best Score");
        } else {
            tvBestScore.setText("Best Score");
        }

        String bestScore = String.valueOf(maxScore);
        String score = String.valueOf(newScore);

        tvNameResult.setText("GGWP " + name + "!!");
        tvScoreResult.setText(score);
        tvBestScoreResult.setText(bestScore);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backToHome = new Intent();
                backToHome.putExtra(KEY_BEST_SCORE, bestScore);
                setResult(RESULT_OK, backToHome);
                finish();
            }
        });
    }
}
