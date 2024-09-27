package com.example.tuprak4chat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.imageview.ShapeableImageView;

    public class MainActivity4 extends AppCompatActivity {

        TextView tvBack, tvNama;
        ImageView profile;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_profile);

            tvBack = findViewById(R.id.tv_back);
            tvNama = findViewById(R.id.nama);
            profile = findViewById(R.id.profile);

            //menerima data dari main2
            Chat chat = getIntent().getParcelableExtra("chat");

            profile.setImageResource(chat.getPhoto());
            tvNama.setText(chat.getName());

            tvBack.setOnClickListener(v-> {
                finish();
            });



        }
    }

