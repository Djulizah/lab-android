package com.example.assignment_3_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.assignment_3_2.databinding.ActivityResultSectionBinding;

public class ResultSection extends AppCompatActivity {

    private ActivityResultSectionBinding binding;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultSectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = getIntent().getParcelableExtra(QuizSection.EXSTRA_USER);

        binding.scoreNumber.setText(Integer.toString(user.Score));
        binding.bestScoreNumber.setText(Integer.toString(user.BestScore));
        binding.backToHome.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra(QuizSection.EXSTRA_USER,user);
            startActivity(intent);
        });
    }

}