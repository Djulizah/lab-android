package com.example.tp4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity2 extends AppCompatActivity {

    TextView tvBack, tvName;
    ShapeableImageView civProfil;
    RecyclerView rvRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvBack = findViewById(R.id.tv_back);
        tvName = findViewById(R.id.nama);
        civProfil = findViewById(R.id.iv_profile);
        rvRoom= findViewById(R.id.rvRoomChat);


        //dapat chat dri activity 1
        Chat chat = getIntent().getParcelableExtra("chat");
        civProfil.setImageResource(chat.getIm1());
        tvName.setText(chat.getTvName());

        rvRoom.setHasFixedSize(true);
        rvRoom.setLayoutManager(new LinearLayoutManager(this));

        Adapter2 adapter = new Adapter2(chat.getListMsg());
        rvRoom.setAdapter(adapter);

        tvBack.setOnClickListener(view -> {
            finish();
        });
        civProfil.setOnClickListener(view -> {
            Intent intent = new Intent(this,MainActivity3.class);
            intent.putExtra("chat", chat);
            startActivity(intent);
        });
    }

}