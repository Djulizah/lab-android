package com.example.assignment_4_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvContacts = findViewById(R.id.rv_contacts);
        rvContacts.setHasFixedSize(true);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        rvContacts.addItemDecoration(new DividerItemDecoration(rvContacts.getContext(), DividerItemDecoration.VERTICAL));

        ContactsAdapter adapter = new ContactsAdapter(ContactsSource.contacts);
        adapter.setClickListener(new ContactsAdapter.ClickListener() {
            @Override
            public void onItemClicked(Contacts contact) {
                Intent toChat = new Intent(MainActivity.this, ChatActivity.class);
                toChat.putExtra("contact", contact);
                startActivity(toChat);
            }
        });
        rvContacts.setAdapter(adapter);
    }
}