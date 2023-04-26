package com.example.tugas4;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ProfileImage extends AppCompatActivity {
    ImageView image, back;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_image);

        image = findViewById(R.id.image);
        back = findViewById(R.id.back);
        name = findViewById(R.id.name);

        int foto = getIntent().getIntExtra("image", 0);
        Glide.with(this).load(foto).into(image);

        String nama = getIntent().getStringExtra("nama");
        name.setText(nama);

        back.setOnClickListener(v -> {
            finish();
        });
    }
}