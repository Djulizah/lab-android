package com.example.assignment_3_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.assignment_3_2.databinding.ActivityQuizSectionBinding;

public class QuizSection extends AppCompatActivity {

    private ActivityQuizSectionBinding binding;

    public final static String EXSTRA_USER = "exstra-user";
    int score = 0;
    int totalQuestion = 5 ;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    User user;
    QuestionList[] daftarQuiz;

    QuestionList quiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizSectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        daftarQuiz = QuestionList.getQuizSample();

        user = getIntent().getParcelableExtra(EXSTRA_USER);

        binding.answerA.setOnClickListener(this::onClick);
        binding.answerB.setOnClickListener(this::onClick);
        binding.answerC.setOnClickListener(this::onClick);
        binding.answerD.setOnClickListener(this::onClick);

        loadNewQuestion();

    }

    public void onClick(View view) {
        Button button = (Button) view;
        selectedAnswer = button.getText().toString();
        int jawabanBenar = quiz.getJawabanBenar();

        if (selectedAnswer.equals(quiz.getPilihanBenar(jawabanBenar))){
            score += quiz.bobot;
            button.setBackgroundColor(Color.parseColor("#4AFF50"));
        } else {
            button.setBackgroundColor(Color.parseColor("#FF4A4A"));
        }


        if (score > user.BestScore){
            user.BestScore = score;
        }

        user.Score = score;
        currentQuestionIndex++;

        button.postDelayed(() -> loadNewQuestion(), 1000);

    }

    void loadNewQuestion(){

        if (currentQuestionIndex == totalQuestion){
            Intent intent = new Intent(this, ResultSection.class);
            intent.putExtra(QuizSection.EXSTRA_USER,user);
            startActivity(intent);
            return;
        }

        binding.answerA.setBackgroundColor(Color.parseColor("#3B6A48"));
        binding.answerB.setBackgroundColor(Color.parseColor("#3B6A48"));
        binding.answerC.setBackgroundColor(Color.parseColor("#3B6A48"));
        binding.answerD.setBackgroundColor(Color.parseColor("#3B6A48"));

        quiz = daftarQuiz[currentQuestionIndex];
        binding.tvQuestion.setText(quiz.soal);
        binding.answerA.setText(quiz.opsi[0]);
        binding.answerB.setText(quiz.opsi[1]);
        binding.answerC.setText(quiz.opsi[2]);
        binding.answerD.setText(quiz.opsi[3]);
        binding.questionTextNumber.setText("Question " + (currentQuestionIndex + 1) + " of 5");
    }

}