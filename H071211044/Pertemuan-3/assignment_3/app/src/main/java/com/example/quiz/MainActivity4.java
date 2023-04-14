package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {
    private TextView tvgg, tvscore, tvbestscore;
    private Button btnback;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        tvgg = findViewById(R.id.tv_gg);
        tvbestscore = findViewById(R.id.tv_bestscore);
        tvscore = findViewById(R.id.tv_score);
        btnback = findViewById(R.id.btn_back);

        int bestscore = getIntent().getIntExtra(MainActivity3.SCORE_KEY, 0);
        user = getIntent().getParcelableExtra(MainActivity2.USER_KEY);
         //jika score skrg lbih bsr dripd best score sblumnya maka best score user dengan yang bru
        if (bestscore >user.getBestscore()) {
          user.setBestscore(bestscore);
        }

        tvgg.setText(user.getUsername());
        tvbestscore.setText(String.valueOf(user.getBestscore()));
        tvscore.setText(String.valueOf(bestscore));
        btnback.setOnClickListener(v->{
            setResult(RESULT_OK, new Intent().putExtra(MainActivity2.USER_KEY, user));
            finish();
        });
    }
}
