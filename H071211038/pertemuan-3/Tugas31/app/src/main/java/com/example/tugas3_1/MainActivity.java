package com.example.tugas3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ImageView profileImage;
    private EditText fullname, username;
    private Button btnSubmit;
    private Uri imageUri;
    private final String KEY_PROFILE = "KEY_PROFILE";
    private final int REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileImage = findViewById(R.id.profile_image);
        fullname = findViewById(R.id.fullname);
        username = findViewById(R.id.username);
        btnSubmit = findViewById(R.id.btn_submit);

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageUri!=null) {
                    if (fullname.length() > 0 && username.length() > 0) {
                        User user = new User(imageUri.toString(), fullname.getText().toString(), username.getText().toString(), "", "");
                        Intent setProfile = new Intent(MainActivity.this, AddNewPostActivity.class);
                        setProfile.putExtra(KEY_PROFILE, user);
                        startActivity(setProfile);
                    } else {
                        if (fullname.length() < 0) {
                            fullname.setError("Please fill out the field!");
                        }
                        if (username.length() < 0) {
                            username.setError("Please fill out the field");
                        }
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please set profile image first!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profileImage.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}