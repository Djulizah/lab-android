package com.example.tugas4;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.example.tugas4.databinding.ActivityMainBinding;
import com.example.tugas4.databinding.ActivityResultBinding;

import java.io.IOException;

public class ResultActivity extends AppCompatActivity {
    ActivityResultBinding binding;
    public final static String EXTRA_USER = "extra_user";
    private ActivityResultLauncher<Intent> resultActivityLauncher;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = getIntent().getParcelableExtra(EXTRA_USER);

        binding.vUser.setText(user.getName());
        binding.userScore.setText("Best Score : " + user.getScore());

        resultActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK){
                        user = result.getData().getParcelableExtra(EXTRA_USER);
                        binding.userScore.setText("Best Score : " + user.getScore());
                    }
                });

        if (user.getFotoUri() != null){
            binding.profil.setImageURI(Uri.parse(user.getFotoUri()));
        }

        binding.play.setOnClickListener(v -> {
            Intent move = new Intent(this, PlayActivity.class);
            move.putExtra(EXTRA_USER, user);
            resultActivityLauncher.launch(move);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}