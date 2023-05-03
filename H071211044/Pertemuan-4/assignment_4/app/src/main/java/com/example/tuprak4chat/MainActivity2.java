package com.example.tuprak4chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    RelativeLayout profileinfo;
    ShapeableImageView profil;
    TextView name, tvback;
    RecyclerView rv_roomchat;

//    private ArrayList<ModelRoomChat> data = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        profileinfo = findViewById(R.id.profile_info);
        profil = findViewById(R.id.profile);
        name = findViewById(R.id.nama);
        tvback = findViewById(R.id.tv_back);

        //dapat chat dari activity1
        Chat chat = getIntent().getParcelableExtra("chat");
        profil.setImageResource(chat.getPhoto());
        name.setText(chat.getName());

        tvback.setOnClickListener(v-> {
            finish();
        });

        profileinfo.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity3.class);
            intent.putExtra("chat", chat);
            startActivity(intent);
        });

        rv_roomchat = findViewById(R.id.rvRoomChat);
        rv_roomchat.setHasFixedSize(true);
        rv_roomchat.setLayoutManager(new LinearLayoutManager(this));

        RoomChatAdapter adapter = new RoomChatAdapter(chat.getListChat());
        rv_roomchat.setAdapter(adapter);

        }
    }