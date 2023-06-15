package com.example.assignment_3_1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.assignment_3_1.databinding.ActivityMainBinding;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    ImageAccess profileImage;

    private ActivityResultLauncher<Intent> ProfileLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri ChooseImage = result.getData().getData();
                        binding.ivProfile.setImageURI(ChooseImage);
                        profileImage.setImageUri(ChooseImage);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        profileImage = new ImageAccess();

        binding.ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentImage = new Intent(Intent.ACTION_PICK);
                intentImage.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                ProfileLauncher.launch(Intent.createChooser(intentImage, "Choose your photo!"));
            }

        });

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = binding.fullname.getText().toString();
                String username = binding.username.getText().toString();

                Boolean isEmpty = false;

                if (profileImage.getImageUri() == null) {
                    Toast.makeText(MainActivity.this, "Choose your profile picture first", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (fullname.isEmpty()) {
                    binding.fullname.setError("Field can't be empty");
                    Toast.makeText(MainActivity.this, "Fill in your name first", Toast.LENGTH_SHORT).show();
                    isEmpty = true;
                }

                if (username.isEmpty()) {
                    binding.username.setError("Field can't be empty");
                    Toast.makeText(MainActivity.this, "Fill in your username first", Toast.LENGTH_SHORT).show();
                    isEmpty = true;
                }

                if (!isEmpty){
                    Intent intent = new Intent(MainActivity.this, FeedSection.class);
                    intent.putExtra("fullname", fullname);
                    intent.putExtra("username", username);
                    intent.putExtra("profile", profileImage);
                    startActivity(intent);
                }
            }
        });


    }

}