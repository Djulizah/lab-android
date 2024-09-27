package com.example.tugas4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        setUpAdapter();
    }

    private void initRecyclerView() {
        recyclerViewUsers = findViewById(R.id.rv_user);
        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewUsers.addItemDecoration(new DividerItemDecoration(recyclerViewUsers.getContext(), DividerItemDecoration.VERTICAL));
    }

    private void setUpAdapter() {
        UserDataSource userDataSource = new UserDataSource();
        UserAdapter userAdapter = new UserAdapter(userDataSource.getUserList(), MainActivity.this);
        recyclerViewUsers.setAdapter(userAdapter);
    }
}