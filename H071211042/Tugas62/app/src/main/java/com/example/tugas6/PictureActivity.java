package com.example.tugas6;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class PictureActivity extends AppCompatActivity {

    private ImageView btn_back, iv_foto;

    private TextView tv_nama;

    ModelChat modelChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        btn_back = findViewById(R.id.btnBack);
        iv_foto = findViewById(R.id.iv_foto);
        tv_nama = findViewById(R.id.tv_nama);

        String foto = getIntent().getStringExtra("foto");
        String nama = getIntent().getStringExtra("nama");
        Intent intent = getIntent();
        System.out.println(foto);
        tv_nama.setText(nama);


        Glide.with(PictureActivity.this)
                .load(foto)
                .into(iv_foto);


        btn_back.setOnClickListener(view -> finish());

    }
}
