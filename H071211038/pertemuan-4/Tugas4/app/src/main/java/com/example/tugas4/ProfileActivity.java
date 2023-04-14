package com.example.tugas4;

import static android.media.MediaFormat.KEY_PROFILE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private TextView nameTextView, phoneTextView, statusTextView, joinedDateTextView;
    private ImageView profilePictureImageView, backButtonImageView;
    private int userId;
    private User user;
    private UserDataSource userDataSource;

    private final String KEY_USER_ID = "KEY_USER_ID";
    private final int DEFAULT_USER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        backButtonImageView = findViewById(R.id.btn_back);
        nameTextView = findViewById(R.id.profile_name);
        phoneTextView = findViewById(R.id.phone);
        statusTextView = findViewById(R.id.profile_status);
        joinedDateTextView = findViewById(R.id.joined_date);
        profilePictureImageView = findViewById(R.id.profile_picture);

        userId = getIntent().getIntExtra(KEY_USER_ID, DEFAULT_USER_ID);
        userDataSource = new UserDataSource();
        user = userDataSource.getUserById(userId);

        nameTextView.setText(user.getName());
        phoneTextView.setText(user.getPhone());
        statusTextView.setText(user.getStatus());
        joinedDateTextView.setText(user.getDateJoined());
        profilePictureImageView.setImageResource(user.getProfilePicture());

        backButtonImageView.setOnClickListener(view -> {
            finish();
        });

        profilePictureImageView.setOnClickListener(view -> {
            Intent intent = new Intent(this, ProfilePictureDetailActivity.class);
            intent.putExtra(KEY_USER_ID, userId);
            startActivity(intent);
        });
    }
}