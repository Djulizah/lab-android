package com.example.quiz;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private ImageView profil;
    private TextView name, bestscore;
    private Button btnapply;
    private User user;
    final static String USER_KEY = "user_key";

    private ActivityResultLauncher<Intent> actLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        profil = findViewById(R.id.ivprofile);
        name = findViewById(R.id.tvname);
        bestscore = findViewById(R.id.tvbestscore);
        btnapply = findViewById(R.id.apply);

        actLauncher = registerForActivityResult(new
                        ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        user = result.getData().getParcelableExtra(USER_KEY);
                        bestscore.setText(String.valueOf(user.getBestscore()));
                    }
                }
        );

        // mengambil user kiriman halaman 1
        user = getIntent().getParcelableExtra(USER_KEY);
        name.setText(user.getUsername());

        //utk mngganti fto profil sma image uri tdk kosong
        if (user.getImageuri() != null) {
            profil.setImageURI(Uri.parse(user.getImageuri()));
        }

        btnapply.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
            intent.putExtra(MainActivity2.USER_KEY, user);
            actLauncher.launch(intent);
        });

    }

}

