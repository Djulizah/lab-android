package com.example.tugas3_quiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {
    private TextView tvQuestionCounter, tvQuestion;
    private Button btnOpt1, btnOpt2, btnOpt3, btnOpt4;

    private ArrayList<Question> questions = new ArrayList<>();
    private ArrayList<Question> randomizedQuestions = new ArrayList<>();
    private int currentQuestionIndex, btnColor;
    private boolean isCorrect;
    private long startTime;
    private int totalScore;
    private int currentScore;

    private Button[] answerButtons;
    private final String KEY_NEW_SCORE = "new score";
    private final String KEY_NAME = "name";
    private final String KEY_BEST_SCORE = "best score";
    private final int SCORE_REQUEST_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tvQuestionCounter = findViewById(R.id.question_counter);
        tvQuestion = findViewById(R.id.question);
        btnOpt1 = findViewById(R.id.btn_opt1);
        btnOpt2 = findViewById(R.id.btn_opt2);
        btnOpt3 = findViewById(R.id.btn_opt3);
        btnOpt4 = findViewById(R.id.btn_opt4);

        btnColor = getResources().getColor(R.color.purple_500);

        answerButtons = new Button[]{btnOpt1, btnOpt2, btnOpt3, btnOpt4};

        createQuestions();
        startQuiz();
    }

    private void startQuiz() {
        startTime = 0;
        currentQuestionIndex = 0;
        totalScore = 0;
        currentScore = 0;
        loadQuestions(currentQuestionIndex);
    }

    public void loadQuestions(int questionIndex) {
        Question currentQuestion = randomizedQuestions.get(questionIndex);
        tvQuestion.setText(currentQuestion.getQuestion());
        btnOpt1.setText(currentQuestion.getOpt1());
        btnOpt2.setText(currentQuestion.getOpt2());
        btnOpt3.setText(currentQuestion.getOpt3());
        btnOpt4.setText(currentQuestion.getOpt4());

        tvQuestionCounter.setText("Question " + (questionIndex + 1) + " of 5");
        startTime = System.currentTimeMillis();

        for (Button answerButton : answerButtons) {
            answerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (Button answerButton : answerButtons){
                        answerButton.setEnabled(false);
                        answerButton.setTextColor(Color.parseColor("#FFFFFF"));
                    }

                    isCorrect = answerButton.getText().toString().equals(randomizedQuestions.get(currentQuestionIndex).getAnswer());

                    if (isCorrect) {
                        answerButton.setBackgroundColor(Color.parseColor("#00D166"));
                    } else {
                        answerButton.setBackgroundColor(Color.parseColor("#ED4337"));
                    }

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getScore();
                        }
                    }, 1200);
                }
            });

            answerButton.setEnabled(true);
            answerButton.setBackgroundColor(btnColor);
        }
    }

    private void getScore() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        int maxScore = 100;

        if (isCorrect) {
            currentScore = maxScore - (int) (elapsedTime / 590);
            if (currentScore < 20) {
                currentScore = 20;
            }
        } else {
            currentScore = 0;
        }

        totalScore += currentScore;
        if (currentQuestionIndex < 4) {
            currentQuestionIndex++;
            loadQuestions(currentQuestionIndex);
        } else {
            endQuiz();
        }
    }

    private void endQuiz() {
        String name = getIntent().getStringExtra(KEY_NAME);

        Intent intent = new Intent(QuizActivity.this, ScoreActivity.class);
        intent.putExtra(KEY_NEW_SCORE, String.valueOf(totalScore));
        intent.putExtra(KEY_NAME, name);
        startActivityForResult(intent, SCORE_REQUEST_CODE);
    }

    public void createQuestions() {
        questions.add(new Question("Siapa Girl Group pemilik lagu \"What is Love?\"?", "STAYC", "BLACKPINK", "TWICE", "RED VELVET", "TWICE"));
        questions.add(new Question("Apa nama fandom dari TWICE?", "ARMY", "ONCE", "MY", "Reveluv", "ONCE"));
        questions.add(new Question("Siapa leader di TWICE?", "Jihyo", "Nayeon", "Momo", "Tzuyu", "Jihyo"));
        questions.add(new Question("Siapa nama member AESPA yang berasal dari Taiwan?", "Ningning", "Winter", "Giselle", "Karina", "Ningning"));
        questions.add(new Question("Apa single debut dari ITZY?", "WANNABE ME", "SNEAKERS", "MAFIA In The Morning", "DALLA DALLA", "DALLA DALLA"));
        questions.add(new Question("Siapa Girl Group pemilik lagu \"Cupid\"?", "TWICE", "IVE", "BLACKPINK", "Fifty Fifty", "Fifty Fifty"));
        questions.add(new Question("Siapa member paling slay di IVE?", "Leeseo", "Wonyoung", "Rei", "Liz", "Wonyoung"));
        questions.add(new Question("Siapa member tertua di BLACKPINK?", "Lisa", "Jennie", "Jisoo", "Rose", "Jisoo"));
        questions.add(new Question("Siapa member yang dijuluki penguin di TWICE?", "Momo", "Sana", "Mina", "Dahyun", "Mina"));
        questions.add(new Question("Siapa itu JYP?", "Tidak tahu", "Bapak TWICE", "Orang biasa", "Pendiri JYPE", "Pendiri JYPE"));

        randomizedQuestions.addAll(questions);
        Collections.shuffle(randomizedQuestions, new Random());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SCORE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String bestScore = data.getStringExtra(KEY_BEST_SCORE);

            Intent home = new Intent();
            home.putExtra(KEY_BEST_SCORE, bestScore);
            setResult(RESULT_OK, home);
            finish();
        }
    }
}