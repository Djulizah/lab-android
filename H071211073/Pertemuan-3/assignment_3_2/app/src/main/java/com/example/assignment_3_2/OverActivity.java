package com.example.assignment_3_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OverActivity extends AppCompatActivity {
    TextView tvname, currentscore, bestscore;
    Button btnhome;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over);

        tvname = findViewById(R.id.tvname);
        currentscore = findViewById(R.id.currentscore);
        bestscore = findViewById(R.id.bestscore);
        btnhome = findViewById(R.id.btnhome);

        user = getIntent().getParcelableExtra("user");

        tvname.setText(user.getName());
        currentscore.setText(String.valueOf(user.getScore()));

        if (user.getScore() > user.getbScore()) {
            user.setbScore(user.getScore());
            bestscore.setText(String.valueOf(user.getScore()));
        } else {
            bestscore.setText(String.valueOf(user.getbScore()));
        }

        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backQuiz = new Intent();
                backQuiz.putExtra("user", user);
                setResult(RESULT_OK, backQuiz);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent backQuiz = new Intent();
        backQuiz.putExtra("user", user);
        setResult(RESULT_OK, backQuiz);
        finish();
    }
}