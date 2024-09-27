package com.example.tugas4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfilePictureDetailActivity extends AppCompatActivity {
    private ImageView profilePicImageView, backButtonImageView;
    private TextView profileNameTextView;
    private final String KEY_USER_ID = "KEY_USER_ID";
    private final int DEFAULT_USER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_picture_detail);

        profilePicImageView = findViewById(R.id.profile_picture);
        backButtonImageView = findViewById(R.id.back_button);
        profileNameTextView = findViewById(R.id.profile_name);

        int userId = getIntent().getIntExtra(KEY_USER_ID, DEFAULT_USER_ID);
        UserDataSource userDataSource = new UserDataSource();
        User user = userDataSource.getUserById(userId);

        profileNameTextView.setText(user.getName());
        profilePicImageView.setImageResource(user.getProfilePicture());

        backButtonImageView.setOnClickListener(view -> {
            finish();
        });

    }
}