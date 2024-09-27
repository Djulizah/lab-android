package com.example.tugas4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MessageActivity extends AppCompatActivity {
    private TextView nameTextView;
    private ImageView profilePicImageView, backButtonImageView;
    private LinearLayout linearLayout;
    private final String KEY_USER_ID = "KEY_USER_ID";
    private final int DEFAULT_USER_ID = 1;

    private int userId;
    private UserDataSource userDataSource;
    private User user;

    private RecyclerView recyclerViewMessages;
    private MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        userId = getIntent().getIntExtra(KEY_USER_ID, DEFAULT_USER_ID);
        userDataSource = new UserDataSource();
        user = userDataSource.getUserById(userId);

        initializeViews();
        setListeners();
        setUpRecyclerView();
    }

    private void initializeViews() {
        backButtonImageView = findViewById(R.id.back_button);
        nameTextView = findViewById(R.id.profile_name);
        profilePicImageView = findViewById(R.id.profile_picture);
        linearLayout = findViewById(R.id.linlay);

        nameTextView.setText(user.getName());
        profilePicImageView.setImageResource(user.getProfilePicture());
    }

    private void setListeners() {
        linearLayout.setOnClickListener(view -> {
            Intent intent = new Intent(MessageActivity.this, ProfileActivity.class);
            intent.putExtra(KEY_USER_ID, userId);
            startActivity(intent);
        });

        backButtonImageView.setOnClickListener(view -> {
            finish();
        });
    }

    private void setUpRecyclerView() {
        recyclerViewMessages = findViewById(R.id.rv_message);
        MessageDataSource messageDataSource = new MessageDataSource();
        messageAdapter = new MessageAdapter(messageDataSource.getMessageForUser(userId), this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true); // reverse layout to align to the right
        recyclerViewMessages.setLayoutManager(layoutManager);
        recyclerViewMessages.setAdapter(messageAdapter);
    }
}
