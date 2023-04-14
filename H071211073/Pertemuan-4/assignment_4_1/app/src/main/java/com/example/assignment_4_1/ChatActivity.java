package com.example.assignment_4_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import javax.sql.DataSource;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Contacts contacts = getIntent().getParcelableExtra("contact");

        View profileTab = findViewById(R.id.profile_tab);
        profileTab.setOnClickListener(view -> {
            Intent toProfile = new Intent(ChatActivity.this, ProfileActivity.class);
            toProfile.putExtra("contact", contacts);
            startActivity(toProfile);
        });

        ImageView btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(view -> {
            finish();
        });

        ImageView ivProfile = findViewById(R.id.iv_profile);
        ivProfile.setImageResource(contacts.getImg());

        TextView tvName = findViewById(R.id.tv_name);
        tvName.setText(contacts.getName());

        RecyclerView rvChats =  findViewById(R.id.rv_chats);
        rvChats.setHasFixedSize(true);
        rvChats.setLayoutManager(new LinearLayoutManager(this));
        rvChats.addItemDecoration(new DividerItemDecoration(rvChats.getContext(), DividerItemDecoration.VERTICAL));

        ChatsAdapter adapter = new ChatsAdapter(contacts.getChats());
        rvChats.setAdapter(adapter);
    }
}