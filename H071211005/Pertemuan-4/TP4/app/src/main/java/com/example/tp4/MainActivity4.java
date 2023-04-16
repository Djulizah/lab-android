package com.example.tp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity4 extends AppCompatActivity {
    TextView tvBack, tvName;
    CircleImageView civProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        tvBack= findViewById(R.id.tvBack);
        tvName = findViewById(R.id.tvName);
        civProfil = findViewById(R.id.civProfil);

        Chat chat = getIntent().getParcelableExtra("chat");
        civProfil.setImageResource(chat.getIm1());
        tvName.setText(chat.getTvName());

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