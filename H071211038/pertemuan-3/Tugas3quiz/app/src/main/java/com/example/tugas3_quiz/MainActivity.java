package com.example.tugas3_quiz;

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
import android.widget.TextView;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button btnApply, btnPlay;
    private TextView tvName, tvBestScore;
    private EditText editName;
    private ImageView profileImg;
    private Uri imageUri;
    private String name;
    private final int REQUEST_CODE = 1;
    private final int QUIZ_REQUEST_CODE = 2;
    private final String KEY_NAME = "name";
    private final String KEY_BEST_SCORE = "best score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileImg = findViewById(R.id.profile_image);
        btnApply = findViewById(R.id.btn_apply);
        btnPlay = findViewById(R.id.btn_play);
        tvName = findViewById(R.id.name);
        tvBestScore = findViewById(R.id.best_score);
        editName = findViewById(R.id.edit_name);

        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editName.length() > 0) {
                    name = editName.getText().toString();
                    tvName.setText(name);
                    editName.setVisibility(View.GONE);
                    tvName.setVisibility(View.VISIBLE);
                    tvBestScore.setVisibility(View.VISIBLE);
                    btnPlay.setVisibility(View.VISIBLE);
                    btnApply.setVisibility(View.GONE);
                } else {
                    editName.setError("Please enter your name!");
                }
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setProfile = new Intent(MainActivity.this, QuizActivity.class);
                setProfile.putExtra(KEY_NAME, name);
                startActivityForResult(setProfile, QUIZ_REQUEST_CODE);
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
                profileImg.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (requestCode == QUIZ_REQUEST_CODE && resultCode == RESULT_OK) {
            btnPlay.setText("Play Again");
            String bestScore = data.getStringExtra(KEY_BEST_SCORE);
            tvBestScore.setText("Best score : " + bestScore);
        }
    }
}