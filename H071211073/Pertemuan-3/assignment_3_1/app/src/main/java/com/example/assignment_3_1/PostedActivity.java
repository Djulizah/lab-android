package com.example.assignment_3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PostedActivity extends AppCompatActivity {
    ImageView img, imgpost;
    TextView uname, fname, capt;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posted);

        img = findViewById(R.id.img);
        imgpost = findViewById(R.id.imgpost);
        uname = findViewById(R.id.uname);
        fname = findViewById(R.id.fname);
        capt = findViewById(R.id.capt);

        //Get Parcels
        user = getIntent().getParcelableExtra("user");

        uname.setText(user.username);
        fname.setText(user.fullname);
        capt.setText(user.caption);

        img.setImageURI(Uri.parse(user.imguri));
        imgpost.setImageURI(Uri.parse(user.posturi));
    }
}