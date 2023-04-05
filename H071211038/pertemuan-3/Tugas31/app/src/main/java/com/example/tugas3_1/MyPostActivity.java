package com.example.tugas3_1;

import androidx.appcompat.app.AppCompatActivity;


import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MyPostActivity extends AppCompatActivity {
    private ImageView profileImage, postImage;
    private TextView fullname, username, caption;
    private final String KEY_POST= "KEY_POST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);

        profileImage = findViewById(R.id.profile_image);
        fullname = findViewById(R.id.fullname);
        username = findViewById(R.id.username);
        postImage = findViewById(R.id.post_image);
        caption = findViewById(R.id.post_caption);

        User user = getIntent().getParcelableExtra(KEY_POST);
        
        Uri profileImgUri = Uri.parse(user.getProfileImg());
        Uri postImgUri = Uri.parse(user.getPostImg());
        profileImage.setImageURI(profileImgUri);
        fullname.setText(user.getFullname());
        username.setText(user.getUsername());
        postImage.setImageURI(postImgUri);
        caption.setText(user.getPostCaption());

    }
}