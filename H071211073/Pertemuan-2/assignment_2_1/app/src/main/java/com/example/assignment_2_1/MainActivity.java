package com.example.assignment_2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.assignment_2_1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //declaring variables
    private ActivityMainBinding binding;
    String calculate, sign;
    int resultDone = 0;
    int signOn = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.grid.button0.setOnClickListener(view1 -> onNumberClick(0));
        binding.grid.button1.setOnClickListener(view1 -> onNumberClick(1));
        binding.grid.button2.setOnClickListener(view1 -> onNumberClick(2));
        binding.grid.button3.setOnClickListener(view1 -> onNumberClick(3));
        binding.grid.button4.setOnClickListener(view1 -> onNumberClick(4));
        binding.grid.button5.setOnClickListener(view1 -> onNumberClick(5));
        binding.grid.button6.setOnClickListener(view1 -> onNumberClick(6));
        binding.grid.button7.setOnClickListener(view1 -> onNumberClick(7));
        binding.grid.button8.setOnClickListener(view1 -> onNumberClick(8));
        binding.grid.button9.setOnClickListener(view1 -> onNumberClick(9));

        binding.grid.buttonmin.setOnClickListener(view1 -> onOperatorClick("-"));
        binding.grid.buttonpls.setOnClickListener(view1 -> onOperatorClick("＋"));
        binding.grid.buttonx.setOnClickListener(view1 -> onOperatorClick("x"));
        binding.grid.buttondvd.setOnClickListener(view1 -> onOperatorClick("/"));

        binding.grid.buttondel.setOnClickListener(view1 -> onDelClick(true));
        binding.grid.buttonac.setOnClickListener(view1 -> onDelClick(false));

        binding.grid.buttoneql.setOnClickListener(view1 -> onResultClick());

    }

    private void onResultClick() {
        calculate = binding.tvResult.getText().toString();

        String[] val = calculate.split(sign);
        Double val1 = Double.parseDouble(val[0]);
        Double val2 = Double.parseDouble(val[1]);
        double resultCalc = 0.0;

        if (sign.equals("＋")) {
            resultCalc = val1 + val2;
        } else if (sign.equals("-")) {
            resultCalc = val1 - val2;
        } else if (sign.equals("x")) {
            resultCalc = val1 * val2;
        } else if (sign.equals("/")) {
            resultCalc = val1 / val2;
        }

        String temp = resultCalc==(int) resultCalc? String.valueOf((int) resultCalc) : String.valueOf(resultCalc);
        binding.tvResult.setText(temp);

        resultDone = 1;
        sign = null;
        signOn = 0;
    }

    private void onDelClick(boolean isDel) {
        if (isDel) {
            switch (resultDone) {
                case 1 :
                    binding.tvResult.setText("");
                    resultDone = 0;
                    sign = null;
                    signOn = 0;
                    break;
                case 0 :
                    calculate = binding.tvResult.getText().toString();
                    String temp = binding.tvResult.getText().toString();
                    int input = temp.length();
                    if(input > 0) {
                        binding.tvResult.setText(temp.substring(0, input-1));
                        if (!sign.isEmpty()){
                            if (signOn == 0) {
                                sign = null;
                            } else {
                                signOn--;
                            }
                        }
                    }
                    break;
            }

        } else {
            binding.tvResult.setText("");
            sign = null;
            signOn = 0;
            resultDone = 0;
        }
    }

    private void onOperatorClick(String s) {
        if (resultDone == 0) {
            calculate = binding.tvResult.getText().toString();
            if(sign == null) {

                binding.tvResult.setText(calculate + s);
            } else {
                binding.tvResult.setText(calculate.replace(sign,s));
            }
            sign = s;
        }

    }

    private void onNumberClick(int i) {
        try {
            if (resultDone == 0) {
                calculate = binding.tvResult.getText().toString();
                if(calculate.length() == 1 && calculate.equals("0")) {
                    binding.tvResult.setText(calculate.replace("0", String.valueOf(i)));
                } else {
                    binding.tvResult.setText(calculate + String.valueOf(i));
                    if (!sign.isEmpty()) {
                        signOn++;
                    }
                }
            } else {
                resultDone = 0;
                binding.tvResult.setText(String.valueOf(i));
                signOn = 0;
                sign = null;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}