package com.example.tp31;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    TextView tvUser, tvName, tvCapt;
    ImageView ivProfil, ivPost;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvUser = findViewById(R.id.tvUser);
        tvName = findViewById(R.id.tvName);
        tvCapt = findViewById(R.id.tvCapt);
        ivProfil = findViewById(R.id.ivProfil);
        ivPost = findViewById(R.id.ivPost);
        user = getIntent().getParcelableExtra("user");

        tvUser.setText(user.getUsername());
        tvName.setText(user.getFullname());
        tvCapt.setText(user.getCaption());
        ivProfil.setImageURI(Uri.parse(user.getImageUri()));
        ivPost.setImageURI(Uri.parse(user.getPostUri()));

    }
}