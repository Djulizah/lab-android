package com.example.assignment_3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.assignment_3_1.databinding.ActivityFeedSectionBinding;

public class FeedSection extends AppCompatActivity {

    private ActivityFeedSectionBinding binding;
    ImageAccess profile, post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedSectionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();

        String fullname = intent.getStringExtra("fullname");
        String username = intent.getStringExtra("username");
        String caption = intent.getStringExtra("caption");
        profile = intent.getParcelableExtra("profile");
        post = intent.getParcelableExtra("picture");

        binding.fullname.setText(fullname);
        binding.username.setText(username);
        binding.caption.setText(caption);
        binding.imgProfile.setImageURI(profile.getImageUri());
        binding.feedImage.setImageURI(post.getImageUri());

    }

}