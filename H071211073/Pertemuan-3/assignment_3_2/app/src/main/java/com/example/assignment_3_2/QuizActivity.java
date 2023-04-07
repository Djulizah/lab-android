package com.example.assignment_3_2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    TextView qNum, question;
    Button chcA, chcB, chcC, chcD;
    User user;

    int score = 0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";
    ActivityResultLauncher<Intent> resultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        qNum = findViewById(R.id.qNum);
        question = findViewById(R.id.question);
        chcA = findViewById(R.id.chca);
        chcB = findViewById(R.id.chcb);
        chcC = findViewById(R.id.chcc);
        chcD = findViewById(R.id.chcd);

        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        assert result.getData() != null;
                        User user = result.getData().getParcelableExtra("user");
                        Intent intent = new Intent();
                        intent.putExtra("user", user);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });

        chcA.setOnClickListener(this);
        chcB.setOnClickListener(this);
        chcC.setOnClickListener(this);
        chcD.setOnClickListener(this);

        qNum.setText(currentQuestionIndex+1 + " out of " + totalQuestion);

        user = getIntent().getParcelableExtra("user");
        loadNewQuestion();

    }

    private void loadNewQuestion() {

        if (currentQuestionIndex == totalQuestion) {
            finishQuiz();
            return;
        }

        qNum.setText(currentQuestionIndex+1 + " out of " + totalQuestion);

        chcA.setBackgroundColor(Color.WHITE);
        chcB.setBackgroundColor(Color.WHITE);
        chcC.setBackgroundColor(Color.WHITE);
        chcD.setBackgroundColor(Color.WHITE);

        question.setText(QuestionAnswer.question[currentQuestionIndex]);
        chcA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        chcB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        chcC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        chcD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
    }

    private void finishQuiz() {
        Intent toOver = new Intent(QuizActivity.this, OverActivity.class);
        toOver.putExtra("user", user);
        resultLauncher.launch(toOver);
    }

    @Override
    public void onClick(View view) {

        Button clickedButton = (Button) view;

        selectedAnswer = String.valueOf(clickedButton.getText());

        if (selectedAnswer == QuestionAnswer.correctAnswers[currentQuestionIndex]) {
            clickedButton.setBackgroundColor(Color.GREEN);
            score = score + 20;
            user.setScore(score);
            delay();
        } else {
            clickedButton.setBackgroundColor(Color.RED);
            delay();
        }

    }

    private void delay() {
        currentQuestionIndex++;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadNewQuestion();
            }
        } ,1000);
    }
}