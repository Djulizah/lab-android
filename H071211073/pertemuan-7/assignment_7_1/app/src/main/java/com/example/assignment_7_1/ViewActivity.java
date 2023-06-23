package com.example.assignment_7_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

public class ViewActivity extends AppCompatActivity {
    ShapeableImageView imageView;
    TextView tvName, tvEmail;
    ProgressBar progressBar;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        progressBar = findViewById(R.id.progressbar);
        imageView = findViewById(R.id.iv_avatar);
        tvName = findViewById(R.id.tv_name);
        tvEmail = findViewById(R.id.tv_email);
        cardView = findViewById(R.id.card_view);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Retrieve the API data from the Intent
                Intent intent = getIntent();
                String firstName = intent.getStringExtra("firstName");
                String lastName = intent.getStringExtra("lastName");
                String email = intent.getStringExtra("email");
                String avatarUrl = intent.getStringExtra("avatarUrl");

                // Update the views with the retrieved data
                tvName.setText(firstName + " " + lastName);
                tvEmail.setText(email);
                Glide.with(ViewActivity.this)
                        .load(avatarUrl)
                        .into(imageView);

                // Hide the progress bar and show the other views
                progressBar.setVisibility(View.GONE);
                cardView.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.VISIBLE);
                tvEmail.setVisibility(View.VISIBLE);
            }
        }, 1000); // Delay of 1 second (1000 milliseconds)
    }
}