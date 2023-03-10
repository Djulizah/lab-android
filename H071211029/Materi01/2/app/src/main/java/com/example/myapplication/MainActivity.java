package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    EditText isiJari, isiPanjang, isiLebar, isiTinggi;
    TextView jari, panjang, lebar, tinggi, hasil;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        isiJari = findViewById(R.id.isiJari);
        isiPanjang = findViewById(R.id.isiPanjang);
        isiLebar = findViewById(R.id.isiLebar);
        isiTinggi = findViewById(R.id.isiTinggi);
        jari = findViewById(R.id.jari);
        panjang = findViewById(R.id.panjang);
        lebar = findViewById(R.id.lebar);
        tinggi = findViewById(R.id.tinggi);
        button = findViewById(R.id.button);
        hasil = findViewById(R.id.hasil);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.drop, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){
            case 0 :
                jari.setVisibility(View.VISIBLE);
                isiJari.setVisibility(View.VISIBLE);
                panjang.setVisibility(View.GONE);
                isiPanjang.setVisibility(View.GONE);
                lebar.setVisibility(View.GONE);
                isiLebar.setVisibility(View.GONE);
                tinggi.setVisibility(View.GONE);
                isiTinggi.setVisibility(View.GONE);

                isiJari.setText("");
                isiJari.setError(null);
                hasil.setText("Hasil");

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (TextUtils.isEmpty(isiJari.getText().toString())){
                            isiJari.setError("Masukkan jumlah jari-jari");
                        }
                        else {
                            float jari2 = Float.valueOf(isiJari.getText().toString());
                            float result = (float) ((Math.pow(jari2, 3) * Math.PI * 4) / 3);
                            String akhir = String.valueOf(result);
                            hasil.setText(akhir);
                        }

                    }
                });
                break;
            case 1 :
                jari.setVisibility(View.VISIBLE);
                isiJari.setVisibility(View.VISIBLE);
                panjang.setVisibility(View.GONE);
                isiPanjang.setVisibility(View.GONE);
                lebar.setVisibility(View.GONE);
                isiLebar.setVisibility(View.GONE);
                tinggi.setVisibility(View.VISIBLE);
                isiTinggi.setVisibility(View.VISIBLE);

                isiJari.setText("");
                isiJari.setError(null);
                isiTinggi.setText("");
                isiTinggi.setError(null);
                hasil.setText("Hasil");

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Boolean checkJari = (TextUtils.isEmpty(isiJari.getText().toString()));
                        Boolean checkTinggi = (TextUtils.isEmpty(isiTinggi.getText().toString()));
                        if(checkJari){
                            isiJari.setError("Masukkan jari-jari");
                        }
                        if (checkTinggi) {
                            isiTinggi.setError("Masukkan tinggi");
                        }
                        if(!checkJari && !checkTinggi) {
                            float jari2 = Float.valueOf(isiJari.getText().toString());
                            float tinggi2 = Float.valueOf(isiTinggi.getText().toString());
                            float result = (float) ((Math.PI * Math.pow(jari2, 2) * tinggi2) / 3);
                            String akhir = String.valueOf(result);
                            hasil.setText(akhir);
                        }
                    }
                });
                break;
            case 2 :
                jari.setVisibility(View.GONE);
                isiJari.setVisibility(View.GONE);
                panjang.setVisibility(View.VISIBLE);
                isiPanjang.setVisibility(View.VISIBLE);
                lebar.setVisibility(View.VISIBLE);
                isiLebar.setVisibility(View.VISIBLE);
                tinggi.setVisibility(View.VISIBLE);
                isiTinggi.setVisibility(View.VISIBLE);

                isiPanjang.setText("");
                isiPanjang.setError(null);
                isiLebar.setText("");
                isiLebar.setError(null);
                isiTinggi.setText("");
                isiTinggi.setError(null);
                hasil.setText("Hasil");

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Boolean checkPanjang = (TextUtils.isEmpty(isiPanjang.getText().toString()));
                        Boolean checkLebar = (TextUtils.isEmpty(isiLebar.getText().toString()));
                        Boolean checkTinggi = (TextUtils.isEmpty(isiTinggi.getText().toString()));
                        if (checkPanjang){
                            isiPanjang.setError("Masukkan panjang");
                        }
                        if (checkLebar) {
                            isiLebar.setError("Masukkan lebar");
                        }
                        if (checkTinggi) {
                            isiTinggi.setError("Masukkan tinggi");
                        }
                        if (!checkPanjang && !checkLebar && !checkTinggi){
                            float panjang2 = Float.valueOf(isiPanjang.getText().toString());
                            float lebar2 = Float.valueOf(isiLebar.getText().toString());
                            float tinggi2 = Float.valueOf(isiTinggi.getText().toString());
                            float result = panjang2 * lebar2 * tinggi2;
                            String akhir = String.valueOf(result);
                            hasil.setText(akhir);
                        }
                    }
                });
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}