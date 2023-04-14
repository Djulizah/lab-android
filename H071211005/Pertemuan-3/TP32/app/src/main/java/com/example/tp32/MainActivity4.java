package com.example.tp32;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {

    TextView tvUsername, tvscore, tvBS;
    Button btnBTH;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        //mengambil skor dri hlmn sblmny
        int bscore = getIntent().getIntExtra("score", 0);
        user = getIntent().getParcelableExtra("user");
        // jika score skrg lbih bsr dripd best score sblumnya maka best score user dengan yang bru
        if (bscore > user.getBestscore()) {
            user.setBestscore(bscore);
        }
        tvUsername = findViewById(R.id.tvUsername);
        tvBS = findViewById(R.id.tvBS);
        tvscore = findViewById(R.id.tvScore);
        btnBTH = findViewById(R.id.btnBTH);

        tvUsername.setText(user.getUsername());
        tvBS.setText(String.valueOf(user.getBestscore()));
        tvscore.setText(String.valueOf(bscore));

        btnBTH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK, new Intent().putExtra("userResult", user));
                finish();
            }
        });
    }
}