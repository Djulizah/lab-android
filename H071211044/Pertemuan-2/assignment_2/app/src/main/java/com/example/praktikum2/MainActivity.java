package com.example.praktikum2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.praktikum2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String display = "";
    private String aktifOperator;
    private int panjangAngkaKedua = 0;
    private boolean angkaPertamaAda = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // tombol angka
        binding.button0.setOnClickListener(v -> onNumberClick("0") );
        binding.button1.setOnClickListener(v -> onNumberClick("1") );
        binding.button2.setOnClickListener(v -> onNumberClick("2") );
        binding.button3.setOnClickListener(v -> onNumberClick("3") );
        binding.button4.setOnClickListener(v -> onNumberClick("4") );
        binding.button5.setOnClickListener(v -> onNumberClick("5") );
        binding.button6.setOnClickListener(v -> onNumberClick("6") );
        binding.button7.setOnClickListener(v -> onNumberClick("7") );
        binding.button8.setOnClickListener(v -> onNumberClick("8") );
        binding.button9.setOnClickListener(v -> onNumberClick("9") );

        //tombol operator
        binding.buttonPlus.setOnClickListener(v -> onOperatorClick("﹢"));
        binding.buttonMinus.setOnClickListener(v -> onOperatorClick("-"));
        binding.buttonTimes.setOnClickListener(v -> onOperatorClick("x"));
        binding.buttonDivide.setOnClickListener(v -> onOperatorClick("/"));

        //tombol hapus
        binding.buttonAc.setOnClickListener(v -> onDeleteClick("AC"));
        binding.buttonDel.setOnClickListener(v -> onDeleteClick("DEL"));

        //tombol result
        binding.buttonEquals.setOnClickListener(v -> onResultClick());

    }
    private void onNumberClick(String number){
        //jika belum ada angka pertama, saat angka diklik maka akan ada
        if (angkaPertamaAda == false){
            angkaPertamaAda = true;
        }
        //jika sdh ada operator aktif, maka kita mengetik di angka kedua
        if (aktifOperator != null){
            panjangAngkaKedua = panjangAngkaKedua + 1;
        }
        //jika baru ada satu angka dan angka itu 0
        if (display.length()==1 && display.equals("0")){
            display = display.replace("0", number);
        }else{
            display = display+number;
        }

        binding.operasi.setText(display);
    }
    private void onOperatorClick(String operator) {
        if (angkaPertamaAda == true){

            //mengecek apakah ada operator yg aktif
            if(aktifOperator != null){
                //saat ada operator aktif akan diganti
                display = display.replace(aktifOperator, operator);
            }else {
                //saat tdk ada operator akan di tambahkan
                display = display + operator;
            }
            aktifOperator = operator;
            binding.operasi.setText(display);
        }
    }

    private void onDeleteClick(String delete) {
        if (delete.equals("AC")) {
            //saat di clear display kosong, tdk ada operator aktif, tdk ada prtama dan kedua
            display = "";
            aktifOperator = null;
            angkaPertamaAda = false;
            panjangAngkaKedua = 0;
        } else {
            //jika angka belum habis, maka di hapus satu angka
           if (display.length()>0){
               //jika ini angka terakhir, setelah di hapus angka pertama jadi false
               if (display.length()==1){
                   angkaPertamaAda = false;
               }
               //kode untuk hapus satu angka di belakang
               display = display.substring(0, display.length() - 1);
               //jika belum ada angka kedua, dan sdh ada operator, setelah di hapus maka operatornya tidak ada.
               if(panjangAngkaKedua == 0 && aktifOperator != null){
                   aktifOperator = null;
               }
           }

        }
        binding.operasi.setText(display);
        binding.result.setText(display);
    }
    private void onResultClick(){
        //mengecek apakah sudah ada operator yg aktif
        if (aktifOperator == null || panjangAngkaKedua == 0 ){
            return;
        }

        // memisahkan angka pertama dengan kedua berdasarkan operator yg aktif
            String[] temp = display.split(aktifOperator);
            int depan = Integer.parseInt(temp[0]);
            int belakang = Integer.parseInt(temp[1]);



            //melakukan operasi sesuai dengan operator yg aktif
        try {
            if (aktifOperator.equals("x")){
                display = String.valueOf(depan * belakang);
            }else if (aktifOperator.equals("-")){
                display = String.valueOf(depan - belakang);
            }else if (aktifOperator.equals("﹢")){
                display = String.valueOf(depan + belakang);

            }else if (aktifOperator.equals("/")){


                double dpn = Double.parseDouble(temp[0]);
                double bel = Double.parseDouble(temp[1]);

                //jika operatornya pembagian dan angka keduanya = 0
                if (bel == 0.0){
                    //untuk menjadikan kondisinya error
                    throw new  Exception();
                }

                display = String.valueOf(dpn / bel);
            }

            binding.result.setText(display);
            aktifOperator = null;
            panjangAngkaKedua = 0;
            angkaPertamaAda = false;
            display = "";

        }catch (Exception e) {
            System.out.println(e.toString());

            Toast.makeText(getApplicationContext(), "tidak dapat dihitung", Toast.LENGTH_SHORT).show();

        }

    }
}
