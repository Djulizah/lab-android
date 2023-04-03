package com.example.a1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {
    TextView result_name, result_username, result_desc;
    ImageView result_foto_desc;
    ShapeableImageView result_foto_profil;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result_name = findViewById(R.id.result_name);
        result_username = findViewById(R.id.result_username);
        result_desc = findViewById(R.id.result_desc);
        result_foto_profil = findViewById(R.id.result_foto_profil);
        result_foto_desc = findViewById(R.id.result_foto_desc);

        user = getIntent().getParcelableExtra(DescActivity.EXTRA_USER);
        result_name.setText(user.getFullName());
        result_username.setText(user.getUsername());
        result_desc.setText(user.getCaption());
        result_foto_profil.setImageURI(Uri.parse(user.getProfilUri()));
        result_foto_desc.setImageURI(Uri.parse(user.getPostUri()));
    }
}