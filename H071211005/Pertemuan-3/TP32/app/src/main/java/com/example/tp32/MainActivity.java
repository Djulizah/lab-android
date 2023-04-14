package com.example.tp32;

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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity extends AppCompatActivity {

    EditText username;
    Button apply;
    ShapeableImageView profil;
    User user;

        // mengambil kembalian dri aktivity yg dibuka dgn aktivity result launcer
    private ActivityResultLauncher<Intent> imageCaptureLauncher=registerForActivityResult(new
                    ActivityResultContracts.StartActivityForResult(),
            result -> {
                // untuk menghandle data yang dikmbalikan galeri
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri uri = result.getData().getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        user.setImageuri(uri.toString());
                        profil.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.etUsername);
        apply = findViewById(R.id.btnApply);
        profil = findViewById(R.id.ivProfil);

        // membuat user baru
        user = new User();
        user.setBestscore(0);
        // untuk buka galeri
        profil.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                imageCaptureLauncher.launch(Intent.createChooser(intent, "Pilih Gambar"));
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setUsername(String.valueOf(username.getText().toString()));
                //
                if(user.getUsername().isEmpty()) {
                    username.setError("Enter Your Name!");
                    Toast.makeText(MainActivity.this, "Enter Your Username ", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }

            }
        });
    }
}