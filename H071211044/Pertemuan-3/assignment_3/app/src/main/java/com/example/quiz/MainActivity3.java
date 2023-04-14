package com.example.quiz;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {

    TextView tvpertanyaan, tvnmrsoal;
    Button btnans1, btnans2, btnans3, btnans4;
    int jumlahsoal = Question.Pertanyaan.length;
    int indexsoalsekarang = 0;
    int hscore = 0;
    final static String SCORE_KEY = "score_key";
    User user;
//    int nomorpertanyaan = 0;



    private ActivityResultLauncher<Intent> actLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvpertanyaan = findViewById(R.id.pertanyaan);
        tvnmrsoal = findViewById(R.id.nomorsoal);
        btnans1 = findViewById(R.id.ans1);
        btnans2 = findViewById(R.id.ans2);
        btnans3 = findViewById(R.id.ans3);
        btnans4 = findViewById(R.id.ans4);

        actLauncher = registerForActivityResult(new
                        ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    //  untuk menghancurkan hlmn ini dan kmbali ke hlmn smblmnya
                        user = result.getData().getParcelableExtra(MainActivity2.USER_KEY);
                        setResult(RESULT_OK, new Intent().putExtra(MainActivity2.USER_KEY, user));
                        finish();
                    }
                }
        );

        //utk mngakses user yg dikrimkan hlmn sblmny
        user = getIntent().getParcelableExtra(MainActivity2.USER_KEY);

        btnans1.setOnClickListener(this);
        btnans2.setOnClickListener(this);
        btnans3.setOnClickListener(this);
        btnans4.setOnClickListener(this);

        //utk mnmpilkan soalbaru
        soalbaru();
    }

    private void soalbaru() {
        //jika nmr soal skrg blm smpai soal trkhir
        if (indexsoalsekarang < jumlahsoal) {
            // untuk mnpilkan nomor soal skrg dan jumlah selruh soal
            tvnmrsoal.setText(String.format("Question Question %d of %d", indexsoalsekarang +1, jumlahsoal));

            //btn wrna awal sblm diklik
            btnans1.setBackgroundColor(getResources().getColor(R.color.pertama));
            btnans2.setBackgroundColor(getResources().getColor(R.color.pertama));
            btnans3.setBackgroundColor(getResources().getColor(R.color.pertama));
            btnans4.setBackgroundColor(getResources().getColor(R.color.pertama));

            //gnti soal sm jwban dgn index soal skrg
            tvpertanyaan.setText(Question.Pertanyaan[indexsoalsekarang]);
            btnans1.setText(Question.pilihan[indexsoalsekarang][0]);
            btnans2.setText(Question.pilihan[indexsoalsekarang][1]);
            btnans3.setText(Question.pilihan[indexsoalsekarang][2]);
            btnans4.setText(Question.pilihan[indexsoalsekarang][3]);

            //jika soal sdh hbs
        } else {
            Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
            intent.putExtra(SCORE_KEY, hscore);
            intent.putExtra(MainActivity2.USER_KEY, user);
            actLauncher.launch(intent);
        }

    }

    @Override
    public void onClick(View view) {
        //fungsi button
        Button button = (Button) view;
        String[] jawabBenar = Question.jawabanBenar;
        String jawaban = ((Button) view).getText().toString().trim();
            if (jawaban.equalsIgnoreCase(Question.jawabanBenar[indexsoalsekarang])) {
                button.setBackgroundColor(getResources().getColor(R.color.green));
                hscore+=50;
            } else {
                button.setBackgroundColor(getResources().getColor(R.color.red));

            }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                indexsoalsekarang++;
                soalbaru();
            }
        }, 1000);
    }
}

