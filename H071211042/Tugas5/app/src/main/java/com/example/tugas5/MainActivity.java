package com.example.tugas5;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    int score=0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;

    int bestScore = 0;
    String selectedAnswer = "";

    ActivityResultLauncher<Intent> resultActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        resultActivityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), v ->{
            if (v.getResultCode() == RESULT_OK) {
                Intent intent = new Intent();
                intent.putExtra("BestScore", bestScore);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        totalQuestionsTextView.setText("Total questions : "+totalQuestion);

        loadNewQuestion();




    }

    @Override
    public void onClick(View view) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn){
            if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();

        } else if (selectedAnswer == null) {
            Toast.makeText(getApplicationContext(),"pilih dlu ngab",Toast.LENGTH_SHORT).show();
        } else{
            //choices button clicked
            selectedAnswer  = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }

    }

    void loadNewQuestion(){

        if(currentQuestionIndex == totalQuestion ){
            finishQuiz();
            return;
        }

        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

    }

    void finishQuiz(){
        if(score < totalQuestion*20){
            int hasil = Integer.parseInt(String.valueOf(score * 20));
            bestScore= hasil;
            String hasilfix = String.valueOf(hasil);
            String best = String.valueOf(score * 20);


            if (score * 20 > hasil) {

                Intent toScore = new Intent(this, ScoreActivity.class);
                toScore.putExtra("status", hasilfix);
                toScore.putExtra("best", hasilfix);
                resultActivityLauncher.launch(toScore);
            }else {
                Intent score = new Intent(this, ScoreActivity.class);
                score.putExtra("status", best);
                score.putExtra("best", hasilfix);
                resultActivityLauncher.launch(score);
            }

        }else{

        }


    }

    public void restartQuiz(){
        score = 0;
        currentQuestionIndex =0;
        loadNewQuestion();
    }

}