package com.example.tugas4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class StatusActivity extends AppCompatActivity {
    ImageView image, back;
    TextView name, nomor, status, statusTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        image = findViewById(R.id.image);
        back = findViewById(R.id.back);
        name = findViewById(R.id.name);
        nomor = findViewById(R.id.nomor);
        status = findViewById(R.id.status);
        statusTime = findViewById(R.id.statusTime);

        int foto = getIntent().getIntExtra("putFoto", 0);
        Glide.with(this).load(foto).into(image);

        String getnama = getIntent().getStringExtra("putNama");
        String getstatus = getIntent().getStringExtra("putStatus");
        String getstatusTime = getIntent().getStringExtra("putStatusTime");
        String getnomor = getIntent().getStringExtra("putNomor");

        name.setText(getnama);
        nomor.setText(getnomor);
        status.setText(getstatus);
        statusTime.setText(getstatusTime);

        back.setOnClickListener(v -> {
            finish();
        });

        image.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfileImage.class);
            intent.putExtra("nama", getnama);
            intent.putExtra("image", foto);
            startActivity(intent);
        });
    }
}