package com.example.tp32;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity implements  View.OnClickListener {
    TextView tvQues, tvNomor;
    Button btnmc, btnmc2, btnmc3;
    // untuk menyimpan jumlah soal
    int sumQues = Question.ques.length;
    //index soal skrg
    int index = 0;
    int bscore =0;

    User user;

    private ActivityResultLauncher<Intent> actLauncher=registerForActivityResult(new
                    ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
//                     untuk menghancurkan hlamn ini dn kmbali ke halamn smblmnya
                    user = result.getData().getParcelableExtra("userResult");
                    setResult(RESULT_OK, new Intent().putExtra("userResult", user));
                    finish();
                }
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvQues = findViewById(R.id.tvQues);
        tvNomor = findViewById(R.id.tvNomor);
        btnmc = findViewById(R.id.btnmc);
        btnmc2 = findViewById(R.id.btnmc2);
        btnmc3 = findViewById(R.id.btnmc3);

        btnmc.setOnClickListener(this);
        btnmc2.setOnClickListener(this);
        btnmc3.setOnClickListener(this);
        //untuk mengakses user yang dikrimkan halmn sblmy
        user = getIntent().getParcelableExtra("user");


        // untuk menampilkan prtnyaa bru
        newQues();
    }
    void newQues() {
        //jika nomor soal skrg blum smpai soal trkhr
        if (index < sumQues) {
            // untuk mnpilkan nomor soal skrg dan jumlah selruh soal
            tvNomor.setText(String.format("Question %d of %d", index +1, sumQues));
            // set wrna awal sblum di klik untuk dijwab
            btnmc.setBackgroundColor(getResources().getColor(R.color.awal));
            btnmc2.setBackgroundColor(getResources().getColor(R.color.awal));
            btnmc3.setBackgroundColor(getResources().getColor(R.color.awal));
            // ganti soal dn jwbn dngn index soal skrg
            tvQues.setText(Question.ques[index]);
            btnmc.setText(Question.choice[index][0]);
            btnmc2.setText(Question.choice[index][1]);
            btnmc3.setText(Question.choice[index][2]);
        }
        // jika soal sdh habis
        else {
            Intent intent = new Intent(MainActivity3.this, MainActivity4.class);

            intent.putExtra("user", user);
            intent.putExtra("score", bscore);
            actLauncher.launch(intent);
        }
    }

    @Override
    public void onClick(View view) {
        // utk mengambil button yg dklik
        Button button = (Button) view;
        // utk mengambil text dri tombol jwbn yg dpilih
        String selectedAns = ((Button) view).getText().toString().trim();
            // jika jwbn yg dpilih sma dgn jwbn yg bnar pd index skrg
            if (selectedAns.equalsIgnoreCase(Question.correctAns[index])) {
                button.setBackgroundColor(getResources().getColor(R.color.benar));
                // score trtmbah
                bscore+=100;
            }else {
                button.setBackgroundColor(getResources().getColor(R.color.salah));
            }
        // membuat delay 1 detik sblum pidh ke soal slnjtnya
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                index++;
                newQues();
            }
        }, 1000);
    }
}