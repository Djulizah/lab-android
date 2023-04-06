package com.example.a1;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.imageview.ShapeableImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ShapeableImageView foto_profil;
    User user;
    EditText name, username;
    Button submit;
    private ActivityResultLauncher<Intent> imageCaptureLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = new User();

        foto_profil = findViewById(R.id.foto_profil);
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        submit = findViewById(R.id.submit);


        imageCaptureLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK){
                        Intent data = result.getData();
                        assert data != null;
                        Bitmap bitmap = null;
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                            user.setProfilUri(data.getData().toString());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        foto_profil.setImageBitmap(bitmap);
                    }
                });



        foto_profil.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            imageCaptureLauncher.launch(Intent.createChooser(intent, "Pilih Gambar"));
        });

        submit.setOnClickListener(v -> {
            user.setFullName(name.getText().toString());
            user.setUsername(username.getText().toString());
            Intent intent = new Intent(this, DescActivity.class);
            intent.putExtra(DescActivity.EXTRA_USER, user);
            setResult(RESULT_OK, intent);
            startActivity(intent);
        });
    }
}