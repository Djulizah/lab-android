package com.example.assignment_4_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Contacts contacts = getIntent().getParcelableExtra("contact");

        //handle back button
        ImageView btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(view -> {
            finish();
        });

        //handle zoom in profile picture
        ImageView ivProfile = findViewById(R.id.iv_profile);
        ivProfile.setImageResource(contacts.getImg());
        ivProfile.setOnClickListener(view -> {
            Intent zoomIn = new Intent(ProfileActivity.this, ImageZoomActivity.class);
            zoomIn.putExtra("contact", contacts);
            startActivity(zoomIn);
        });

        TextView tvName = findViewById(R.id.tv_name);
        tvName.setText(contacts.getName());

        TextView tvNumber = findViewById(R.id.tv_number);
        tvNumber.setText(contacts.getNumber());

        TextView tvStatus = findViewById(R.id.tv_status);
        tvStatus.setText(contacts.getStatus());

        TextView tvStatusTime = findViewById(R.id.tv_status_time);
        tvStatusTime.setText(contacts.getStatusTime());
    }
}