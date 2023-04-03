package com.example.tugas4;

import static com.example.tugas4.ResultActivity.EXTRA_USER;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.tugas4.databinding.ActivityPlayBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayActivity extends AppCompatActivity {
    ActivityPlayBinding binding;
    List<Question> listQuestion = new ArrayList<>();
    int nomor, score, bestScore;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = getIntent().getParcelableExtra(EXTRA_USER);

        gas();

        binding.op1.setOnClickListener(v -> onAnswer(binding.op1.getText().toString()));
        binding.op2.setOnClickListener(v -> onAnswer(binding.op2.getText().toString()));
        binding.op3.setOnClickListener(v -> onAnswer(binding.op3.getText().toString()));
        binding.op4.setOnClickListener(v -> onAnswer(binding.op4.getText().toString()));

        binding.home.setOnClickListener(v -> {
            user.setScore(Integer.parseInt(binding.bestScore.getText().toString()));
            Intent intent = new Intent();
            intent.putExtra(EXTRA_USER, user);
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    private void gas(){
        nomor = 1;
        score = 0;
        bestScore = 0;

        Question q1 = new Question("Berapa jumlah ban mobil?", "1", "2", "3", "4", "4");
        Question q2 = new Question("Sekarang bulan apa?", "April", "Ramadhan", "Maret", "November", "Ramadhan");
        Question q3 = new Question("Bagaimana lab tahun ini?", "Sangat Seru", "Mengasyikkan", "WOW", "Asikk", "WOW");
        Question q4 = new Question("Berapa ram minimal untuk menggunakan android studio?", "4gb", "16gb", "8gb", "32gb", "32gb");
        Question q5 = new Question("Berapa maksimal sks yang diambil tiap semester?", "20", "10", "4", "24", "24");

        listQuestion.add(q1);
        listQuestion.add(q2);
        listQuestion.add(q3);
        listQuestion.add(q4);
        listQuestion.add(q5);

        Collections.shuffle(listQuestion);

        binding.quiz.setText("Quiz " + nomor + " of 5");

        Question question = listQuestion.get(0);
        binding.question.setText(question.getQuestion());
        binding.op1.setText(question.getOp1());
        binding.op2.setText(question.getOp2());
        binding.op3.setText(question.getOp3());
        binding.op4.setText(question.getOp4());
    }

    public void onAnswer(String opAnswer){
        Question question = listQuestion.remove(0);
        int biru = Color.parseColor("#FFA5D7E8");
        int merah = Color.parseColor("#FFFF0000");

        if (opAnswer.equals(question.getAnswer())){
            if (opAnswer.equals(question.getOp1())){
                binding.card1.setCardBackgroundColor(biru);
            }else if (opAnswer.equals(question.getOp2())){
                binding.card2.setCardBackgroundColor(biru);
            }else if (opAnswer.equals(question.getOp3())){
                binding.card3.setCardBackgroundColor(biru);
            }else if (opAnswer.equals(question.getOp4())){
                binding.card4.setCardBackgroundColor(biru);
            }
            score += 20;
        }else {
            if (opAnswer.equals(question.getOp1())){
                binding.card1.setCardBackgroundColor(merah);
            }else if (opAnswer.equals(question.getOp2())){
                binding.card2.setCardBackgroundColor(merah);
            }else if (opAnswer.equals(question.getOp3())){
                binding.card3.setCardBackgroundColor(merah);
            }else if (opAnswer.equals(question.getOp4())){
                binding.card4.setCardBackgroundColor(merah);
            }
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                check();
            }
        }, 1000);

    }

    private void check(){
        if (listQuestion.size() == 0){
            binding.layoutQ.setVisibility(View.GONE);
            binding.layoutA.setVisibility(View.VISIBLE);
            if (score > bestScore){
                user.setScore(score);
                binding.bestScore.setText(String.valueOf(user.getScore()));
            }else{
                binding.bestScore.setText(String.valueOf(user.getScore()));
            }
            binding.score.setText(String.valueOf(score));
            binding.name.setText("GGWP " + user.getName());
            score = 0;
        }else {
            nomor++;
            binding.quiz.setText("Quiz " + nomor + " of 5");

            ColorStateList colorDefault = binding.cardQ.getCardBackgroundColor();

            binding.card1.setCardBackgroundColor(colorDefault);
            binding.card2.setCardBackgroundColor(colorDefault);
            binding.card3.setCardBackgroundColor(colorDefault);
            binding.card4.setCardBackgroundColor(colorDefault);

            Question question = listQuestion.get(0);
            binding.question.setText(question.getQuestion());
            binding.op1.setText(question.getOp1());
            binding.op2.setText(question.getOp2());
            binding.op3.setText(question.getOp3());
            binding.op4.setText(question.getOp4());
        }
    }
}


class Question{
    private final String question, op1, op2, op3, op4, answer;

    public Question(String question, String op1, String op2, String op3, String op4, String answer){
        this.question = question;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        this.answer = answer;
    }

    public String getQuestion(){
        return question;
    }

    public String getOp1(){
        return op1;
    }

    public String getOp2(){
        return op2;
    }

    public String getOp3(){
        return op3;
    }

    public String getOp4(){
        return op4;
    }

    public String getAnswer(){
        return answer;
    }
}