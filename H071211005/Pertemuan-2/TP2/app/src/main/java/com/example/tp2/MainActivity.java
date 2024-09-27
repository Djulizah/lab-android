package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tp2.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String display = "";
    private String selectOperator;
    private int panjangAngka2 = 0;

    private boolean aktifNUm1 = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //untuk tombol angka
        binding.btn0.setOnClickListener(v -> onNumberClick("0"));
        binding.btn1.setOnClickListener(v -> onNumberClick("1"));
        binding.btn2.setOnClickListener(v -> onNumberClick("2"));
        binding.btn3.setOnClickListener(v -> onNumberClick("3"));
        binding.btn4.setOnClickListener(v -> onNumberClick("4"));
        binding.btn5.setOnClickListener(v -> onNumberClick("5"));
        binding.btn6.setOnClickListener(v -> onNumberClick("6"));
        binding.btn7.setOnClickListener(v -> onNumberClick("7"));
        binding.btn8.setOnClickListener(v -> onNumberClick("8"));
        binding.btn9.setOnClickListener(v -> onNumberClick("9"));

        //untuk operatornya
        binding.btnplus.setOnClickListener(v -> onOperatorClick("﹢"));
        binding.btnmin.setOnClickListener(v -> onOperatorClick("-"));
        binding.btnmulti.setOnClickListener(v -> onOperatorClick("x"));
        binding.btndivide.setOnClickListener(v -> onOperatorClick("/"));

        // untuk hapus
        binding.btnAC.setOnClickListener(v -> onDeleteClick("AC"));
        binding.btnback.setOnClickListener(v -> onDeleteClick("C"));

        //untuk hasil
        binding.btneq.setOnClickListener(v -> onResultClick());

    }
    private void onNumberClick(String number) {
        if(aktifNUm1 == false) {
            aktifNUm1 = true;
        }
        if(selectOperator != null) {
            panjangAngka2 = panjangAngka2 + 1;
        }
        if(display.length()==1 && display.equals("0")) {
            display = display.replace("0", number);
        } else {
            display = display+number;
        }
        binding.tv.setText(display);
    }

    private void onOperatorClick(String operator) {
        // mengecek apakah ada operator yang aktif
        if (aktifNUm1 == true) {
            if(selectOperator != null){
                display = display.replace(selectOperator, operator);

            } else {
                display = display+operator;
            }
            selectOperator =  operator;
            binding.tv.setText(display);
        }
    }

    private void onDeleteClick(String delete) {
        if(delete.equals("AC")){
            display = "";
            selectOperator = null;
            panjangAngka2 = 0;
            aktifNUm1 = false;
        }else {
            if(display.length()>0) {
                if(display.length() == 1) {
                    aktifNUm1 = false;
                }
            }
            display = display.substring(0,display.length()-1);
            if(panjangAngka2 == 0 && selectOperator != null) {
                selectOperator = null;
            }
        }
        binding.tvhasil.setText(display);
        binding.tv.setText(display);
    }
    private void onResultClick() {

        // mengecek apakah ada operator yang aktif
        if (selectOperator == null || panjangAngka2 == 0) {
            return;
        }

        String[] temp = display.split(selectOperator);
        int depan = Integer.parseInt(temp[0]);
        int belakang = Integer.parseInt(temp[1]);


        // melakukan operasi sesuai dengan operator yg aktif
        try{
            if(selectOperator.equals("x")){
                display = String.valueOf(depan * belakang);
            } else if (selectOperator.equals("-")) {
                display = String.valueOf(depan - belakang);
            } else if (selectOperator.equals("﹢")) {
                display = String.valueOf(depan + belakang);
            }
            else if (selectOperator.equals("/")) {

                double dep = Double.parseDouble(temp[0]);
                double bel = Double.parseDouble(temp[1]);

                if (bel == 0.0) {
                    throw new Exception();
                }

                display = String.valueOf(dep / bel);
            }
            binding.tvhasil.setText(display);
            selectOperator = null;
            panjangAngka2 = 0;
            aktifNUm1 = false;
            display = "";

        }catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Tidak dapat dioperasikan", Toast.LENGTH_SHORT).show();;
        }
    }

}