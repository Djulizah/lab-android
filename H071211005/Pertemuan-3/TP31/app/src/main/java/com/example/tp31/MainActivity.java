package com.example.tp31;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity extends AppCompatActivity {

    EditText fullname, username;

    Button submit;


    Boolean isEmpty = false;

    ShapeableImageView profil;

    User user;

    private ActivityResultLauncher<Intent> imageCaptureLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri uri = result.getData().getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            user.setImageUri(uri.toString());
                            profil.setImageBitmap(bitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullname = findViewById(R.id.etName);
        username = findViewById(R.id.etUser);
        submit = findViewById(R.id.btnSubmit);
        profil = findViewById(R.id.ivProfil);
        user = new User();

        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                imageCaptureLauncher.launch(Intent.createChooser(intent, "Pilih Gambar"));
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setUsername(String.valueOf(username.getText()));
                user.setFullname(String.valueOf(fullname.getText()));
                isEmpty = false;

                if (user.getImageUri() == null) {
                    Toast.makeText(MainActivity.this, "Pick Your Profil First", Toast.LENGTH_SHORT).show();
                    isEmpty = true;
                    return;
                }
                if (user.getFullname().isEmpty()) {
                    fullname.setError("field ini tidak boleh kosong!");
                    Toast.makeText(MainActivity.this, "Enter Your Full Name", Toast.LENGTH_SHORT).show();
                    isEmpty = true;

                }
                if (user.getUsername().isEmpty()) {
                    username.setError("field ini tidak boleh kosong!");
                    Toast.makeText(MainActivity.this, "Enter Your Username ", Toast.LENGTH_SHORT).show();
                    isEmpty = true;

                }
                if (!isEmpty) {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
            }
        });
    }
}