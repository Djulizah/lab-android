package com.example.tuprak4chat;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity3 extends AppCompatActivity {

TextView tvBack, tvNama, tvStatus, tvInfo, tvNotelp;
ShapeableImageView profile;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiledetail);

        tvBack = findViewById(R.id.tv_back);
        tvNama = findViewById(R.id.nama);
        tvStatus = findViewById(R.id.Status);
        tvInfo = findViewById(R.id.Info);
        tvNotelp = findViewById(R.id.notelp);
        profile = findViewById(R.id.profile);

        //menerima data dari main2
        Chat chat = getIntent().getParcelableExtra("chat");


        tvNama.setText(chat.getName());
        tvStatus.setText(chat.getStatus());
        tvNotelp.setText(chat.getPhone());
        tvInfo.setText(chat.getDate());
        profile.setImageResource(chat.getPhoto());

        tvBack.setOnClickListener(v-> {
            finish();
        });

        //TEKAN FOTO KE DETAIL FOTO
        profile.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity4.class);
            intent.putExtra("chat", chat);
            startActivity(intent);
        });

    }
}
