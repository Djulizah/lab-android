package com.example.tugas2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tugas2.databinding.ActivityMainBinding;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    String data;
    String i;
    int j;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.hapusAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.view.input.setText("");
                binding.view.output.setText("");
                binding.button.tambah.setEnabled(true);
                binding.button.kurang.setEnabled(true);
                binding.button.btnx.setEnabled(true);
                binding.button.bagi.setEnabled(true);
                j = 0;
                i = null;
            }
        });

        binding.button.btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = binding.view.input.getText().toString();
                int k = data.length();
                if(i != null){
                    j++;
                }
                if (k == 1 && data.equals("0")){
                    binding.view.input.setText(data.replace("0", "0"));

                }
                else {
                    binding.view.input.setText(data +"0");
                }

            }
        });

        binding.button.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = binding.view.input.getText().toString();
                int k = data.length();
                if(i != null){
                    j++;
                }
                if (k == 1 && data.equals("0")){
                    binding.view.input.setText(data.replace("0", "1"));

                }
                else {
                    binding.view.input.setText(data +"1");
                }
            }
        });

        binding.button.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = binding.view.input.getText().toString();
                int k = data.length();
                if(i != null){
                    j++;
                }
                if (k == 1 && data.equals("0")){
                    binding.view.input.setText(data.replace("0", "2"));

                }
                else {
                    binding.view.input.setText(data +"2");
                }
            }
        });

        binding.button.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = binding.view.input.getText().toString();
                int k = data.length();
                if(i != null){
                    j++;
                }
                if (k == 1 && data.equals("0")){
                    binding.view.input.setText(data.replace("0", "3"));

                }
                else {
                    binding.view.input.setText(data +"3");
                }
            }
        });

        binding.button.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = binding.view.input.getText().toString();
                int k = data.length();
                if(i != null){
                    j++;
                }
                if (k == 1 && data.equals("0")){
                    binding.view.input.setText(data.replace("0", "4"));

                }
                else {
                    binding.view.input.setText(data +"4");
                }
            }
        });

        binding.button.btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = binding.view.input.getText().toString();
                int k = data.length();
                if(i != null){
                    j++;
                }
                if (k == 1 && data.equals("0")){
                    binding.view.input.setText(data.replace("0", "5"));

                }
                else {
                    binding.view.input.setText(data +"5");
                }
            }
        });

        binding.button.btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = binding.view.input.getText().toString();
                int k = data.length();
                if(i != null){
                    j++;
                }
                if (k == 1 && data.equals("0")){
                    binding.view.input.setText(data.replace("0", "6"));

                }
                else {
                    binding.view.input.setText(data +"6");
                }
            }
        });

        binding.button.btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = binding.view.input.getText().toString();
                int k = data.length();
                if(i != null){
                    j++;
                }
                if (k == 1 && data.equals("0")){
                    binding.view.input.setText(data.replace("0", "7"));

                }
                else {
                    binding.view.input.setText(data +"7");
                }
            }
        });

        binding.button.btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = binding.view.input.getText().toString();
                int k = data.length();
                if(i != null){
                    j++;
                }
                if (k == 1 && data.equals("0")){
                    binding.view.input.setText(data.replace("0", "8"));

                }
                else {
                    binding.view.input.setText(data +"6");
                }
            }
        });

        binding.button.btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = binding.view.input.getText().toString();
                int k = data.length();
                if(i != null){
                    j++;
                }
                if (k == 1 && data.equals("0")){
                    binding.view.input.setText(data.replace("0", "9"));

                }
                else {
                    binding.view.input.setText(data +"9");
                }
            }
        });

        binding.button.tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(j == 0){
                    data = binding.view.input.getText().toString();
                    if(i == null){
                        binding.view.input.setText(data + "+");
                    }else {
                        binding.view.input.setText(data.substring(0, data.length() - 1) + "+");
                    }
                    i = "+";
                }else {
                    binding.button.kurang.setEnabled(false);
                    binding.button.btnx.setEnabled(false);
                    binding.button.bagi.setEnabled(false);
                }
            }
        });

        binding.button.kurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(j == 0){
                    data = binding.view.input.getText().toString();
                    if(i == null){
                        binding.view.input.setText(data + "-");
                    }else {
                        binding.view.input.setText(data.substring(0, data.length() - 1) + "-");
                    }
                    i = "-";
                }else {
                    binding.button.tambah.setEnabled(false);
                    binding.button.btnx.setEnabled(false);
                    binding.button.bagi.setEnabled(false);
                }
            }
        });

        binding.button.btnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(j == 0){
                    data = binding.view.input.getText().toString();
                    if(i == null){
                        binding.view.input.setText(data + "x");
                    }else {
                        binding.view.input.setText(data.substring(0, data.length() - 1) + "x");
                    }
                    i = "x";
                }else {
                    binding.button.kurang.setEnabled(false);
                    binding.button.tambah.setEnabled(false);
                    binding.button.bagi.setEnabled(false);
                }
            }
        });

        binding.button.bagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(j == 0){
                    data = binding.view.input.getText().toString();
                    if(i == null){
                        binding.view.input.setText(data + "/");
                    }else {
                        binding.view.input.setText(data.substring(0, data.length() - 1) + "/");
                    }
                    i = "/";
                }else {
                    binding.button.kurang.setEnabled(false);
                    binding.button.btnx.setEnabled(false);
                    binding.button.tambah.setEnabled(false);
                }
            }
        });

        binding.button.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = binding.view.input.getText().toString();
                int panjang = data.length();
                binding.button.tambah.setEnabled(true);
                binding.button.kurang.setEnabled(true);
                binding.button.btnx.setEnabled(true);
                binding.button.bagi.setEnabled(true);
                if (panjang > 0){
                    binding.view.input.setText(data.substring(0, panjang - 1));
                    if (j == 0){
                        i = null;
                        j = 0;
                        binding.button.tambah.setEnabled(true);
                        binding.button.kurang.setEnabled(true);
                        binding.button.btnx.setEnabled(true);
                        binding.button.bagi.setEnabled(true);
                    } else {
                        j--;
                    }
                }
            }
        });

        binding.button.equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = binding.view.input.getText().toString();
                String all [] = data.split("[/x+-]");
                float num1 = Float.parseFloat(all[0]);
                float num2 = Float.parseFloat(all[1]);

                switch (i){
                    case "+" :
                        result = String.valueOf(num1 + num2);
                        result = result.endsWith(".0")?result.substring(0, result.length()-2):result;
                        binding.view.output.setText(result);
                        break;
                    case "-" :
                        result = String.valueOf(num1 - num2);
                        result = result.endsWith(".0")?result.substring(0, result.length()-2):result;
                        binding.view.output.setText(result);
                        break;
                    case "x" :
                        result = String.valueOf(num1 * num2);
                        result = result.endsWith(".0")?result.substring(0, result.length()-2):result;
                        binding.view.output.setText(result);
                        break;
                    case "/" :
                        result = String.valueOf(num1 / num2);
                        result = result.endsWith(".0")?result.substring(0, result.length()-2):result;
                        binding.view.output.setText(result);
                        break;
                }
            }
        });
    }
}