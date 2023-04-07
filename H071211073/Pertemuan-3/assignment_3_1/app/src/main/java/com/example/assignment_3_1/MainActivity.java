package com.example.assignment_3_1;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    TextInputEditText fname, uname;
    Button btnsub;
    User user;
    ActivityResultLauncher<Intent> imageCaptureLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.img);
        fname = findViewById(R.id.fname);
        uname = findViewById(R.id.uname);
        btnsub = findViewById(R.id.btnsub);

        user = new User();

        imageCaptureLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri uri = result.getData().getData();
                        user.setImguri(uri.toString());
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            img.setImageBitmap(bitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setFullname(String.valueOf(fname.getText()));
                user.setUsername(String.valueOf(uname.getText()));

                //toast alert when fields are empty each
                if (TextUtils.isEmpty((CharSequence) user.getUsername())) {
                    Toast.makeText(MainActivity.this, "Enter Username!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty((CharSequence) user.getFullname())) {
                    Toast.makeText(MainActivity.this, "Enter Fullname!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Intent
                Intent toPost = new Intent(MainActivity.this, PostActivity.class);
                toPost.putExtra("user", user);
                startActivity(toPost);
            }
        });

        img.setOnClickListener(view -> onBtnSelectImageClicked());
    }

    private void onBtnSelectImageClicked(){
        //Implicit Intent to Choice Image
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        imageCaptureLauncher.launch(Intent.createChooser(intent, "Pilih Gambar"));
    }

}