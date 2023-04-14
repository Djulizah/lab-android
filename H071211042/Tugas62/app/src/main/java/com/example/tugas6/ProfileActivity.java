package com.example.tugas6;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ProfileActivity extends AppCompatActivity {

    private ImageView iv_foto;

    private TextView tv_nama, tv_no, tv_status, tv_time;

    private ImageView btnBack;

    ModelChat modelChat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        iv_foto = findViewById(R.id.iv_foto);
        tv_nama = findViewById(R.id.tv_namaprofile);
        tv_no = findViewById(R.id.tv_no);
        tv_status = findViewById(R.id.tv_status);
        tv_time = findViewById(R.id.tv_time);
        btnBack = findViewById(R.id.btnBack);

        String nama = getIntent().getStringExtra("nama");
        String foto = getIntent().getStringExtra("foto");
        String status = getIntent().getStringExtra("status");
        String noHP = getIntent().getStringExtra("nohp");
        String time = getIntent().getStringExtra("time");
        Intent intent = getIntent();
        modelChat = intent.getParcelableExtra("chats");
        tv_nama.setText(nama);
        tv_no.setText(noHP);
        tv_status.setText(status);
        tv_time.setText(time);
        Glide.with(ProfileActivity.this)
                .load(foto)
                .into(iv_foto);

        btnBack.setOnClickListener(view -> finish());

        iv_foto.setOnClickListener(view -> {
            Intent toPict = new Intent(this, PictureActivity.class);
            toPict.putExtra("foto", foto);
            toPict.putExtra("nama",nama);
            startActivity(toPict);
        });




    }
}
