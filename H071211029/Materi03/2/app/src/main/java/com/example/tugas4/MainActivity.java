package com.example.tugas4;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.tugas4.databinding.ActivityMainBinding;
import com.google.android.material.imageview.ShapeableImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    User user;
    private ActivityResultLauncher<Intent> imageCaptureLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = new User();

        imageCaptureLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK){
                        try {
                            Intent data = result.getData();
                            assert data != null;
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                            user.setFotoUri(data.getData().toString());
                            binding.profil.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });

        binding.profil.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            imageCaptureLauncher.launch(Intent.createChooser(intent, "Pilih Gambar"));
        });

        binding.apply.setOnClickListener(v -> {
            if(binding.username.getText().toString().length() > 0){
                user.setName(binding.username.getText().toString());
                user.setScore(0);
                Intent move = new Intent(this, ResultActivity.class);
                move.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                move.putExtra(ResultActivity.EXTRA_USER, user);
                setResult(RESULT_OK, move);
                startActivity(move);
            }else {
                Toast.makeText(this, "Silahkan masukkan nickname", Toast.LENGTH_SHORT).show();
            }
        });
    }
}