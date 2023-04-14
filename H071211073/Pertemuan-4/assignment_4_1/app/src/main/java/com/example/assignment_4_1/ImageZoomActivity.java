package com.example.assignment_4_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageZoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_zoom);

        Contacts contacts = getIntent().getParcelableExtra("contact");

        ImageView btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(view -> {
            finish();
        });

        TextView tvName = findViewById(R.id.tv_name);
        tvName.setText(contacts.getName());

        ImageView ivProfile = findViewById(R.id.iv_profile);
        ivProfile.setImageResource(contacts.getImg());
    }
}