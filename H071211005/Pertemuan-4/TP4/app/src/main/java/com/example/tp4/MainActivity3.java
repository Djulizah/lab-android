package com.example.tp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity3 extends AppCompatActivity {
    TextView tvName, tvNum, tvStatus, tvDate, tvBack;
    ShapeableImageView civProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvBack = findViewById(R.id.tvBack2);
        tvNum = findViewById(R.id.tvNum);
        tvStatus = findViewById(R.id.tvStatus);
        tvDate = findViewById(R.id.tvDate);
        civProfil = findViewById(R.id.civProfil2);
        tvName = findViewById(R.id.tvName);

        Chat chat = getIntent().getParcelableExtra("chat");

        tvName.setText(chat.getTvName());
        tvNum.setText(chat.getTvNumber());
        tvStatus.setText(chat.getTvStatus());
        tvDate.setText(chat.getTvDate());
        civProfil.setImageResource(chat.getIm1());

        tvBack.setOnClickListener(view -> {
            finish();
        });
        civProfil.setOnClickListener(view -> {
            Intent intent = new Intent(this,MainActivity4.class);
            intent.putExtra("chat", chat);
            startActivity(intent);
        });

    }
}