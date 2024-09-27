package com.example.tp32;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView username, tvBSc;
    ImageView profil;
    User user;
    Button play;

    private ActivityResultLauncher<Intent> actLauncher=registerForActivityResult(new
                    ActivityResultContracts.StartActivityForResult(),
            result -> {

                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    user = result.getData().getParcelableExtra("userResult");
                    tvBSc.setText(String.valueOf(user.getBestscore()));
                }
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        username = findViewById(R.id.tvUsername);
        profil = findViewById(R.id.ivProfil);
        play = findViewById(R.id.btnPlay);
        tvBSc = findViewById(R.id.tvBSc);
        user = getIntent().getParcelableExtra("user");

        username.setText(user.getUsername());


        // untuk menggganti foto profil saam imageuri tdk kosong
        if (user.getImageuri() != null) {
            profil.setImageURI(Uri.parse(user.getImageuri()));
        }

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                intent.putExtra("user", user);
                actLauncher.launch(intent);
            }
        });

    }


}