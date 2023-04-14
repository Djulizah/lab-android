package com.example.tugas5;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {
    ImageView img;
    TextView tv1, tv2;
    Button btnApply;
    Uri uri;

    ActivityResultLauncher<Intent> resultActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        img = findViewById(R.id.fp1);
        tv1 = findViewById(R.id.profilename);
        tv2 = findViewById(R.id.best);
        btnApply= findViewById(R.id.btnPlay);

        String uriCaption = getIntent().getStringExtra("img");
        img.setImageURI(Uri.parse(uriCaption));
        tv1.setText(getIntent().getExtras().getString("extranamalengkap"));

        resultActivityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), v ->{
            if (v.getResultCode() == RESULT_OK) {
                tv2.setText(String.valueOf(v.getData().getIntExtra("BestScore",0)));
            }
        });

        btnApply.setOnClickListener(view -> {
            Intent play = new Intent(this, MainActivity.class);
            resultActivityLauncher.launch(play);
        });
    }
}