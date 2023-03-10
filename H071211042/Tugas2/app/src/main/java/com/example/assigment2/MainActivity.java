package com.example.assigment2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerBangunRuang;
    private EditText panjang, lebar, tinggi, radius;
    private Button buttonHitung;
    private TextView hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerBangunRuang = findViewById(R.id.spinnerBangunRuang);
        panjang = findViewById(R.id.panjang);
        lebar = findViewById(R.id.lebar);
        tinggi = findViewById(R.id.tinggi);
        radius = findViewById(R.id.radius);
        buttonHitung = findViewById(R.id.buttonHitung);
        hasil = findViewById(R.id.hasil);


        String[] daftarBangunRuang = {"Pilih Bangun Ruang", "Bola", "Balok", "Kerucut"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, daftarBangunRuang);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBangunRuang.setAdapter(adapter);


        spinnerBangunRuang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1 :
                        panjang.setVisibility(View.GONE);
                        lebar.setVisibility(View.GONE);
                        tinggi.setVisibility(View.GONE);
                        radius.setVisibility(View.VISIBLE);
                        radius.setText(null);
                        radius.setError(null);
                        radius.setHint("Masukkan jari-jari bola");
                        buttonHitung.setVisibility(View.VISIBLE);

                        buttonHitung.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String radiusInput = radius.getText().toString();

                                if (radiusInput.isEmpty()){
                                    radius.setError("Harap isi jari-jari kerucut.");
                                } else {
                                    double radiusBola = Double.parseDouble(radiusInput);
                                    double volume = (4.0/3.0) * Math.PI * Math.pow(radiusBola, 3);
                                    hasil.setText("Volume bola = " + String.format("%.2f", volume));
                                }
                            }
                        });


                        break;

                    case 2 :
                        panjang.setVisibility(View.VISIBLE);
                        panjang.setHint("Masukkan panjang balok");
                        panjang.setText(null);
                        panjang.setError(null);
                        lebar.setVisibility(View.VISIBLE);
                        lebar.setHint("Masukkan lebar balok");
                        lebar.setText(null);
                        lebar.setError(null);
                        tinggi.setVisibility(View.VISIBLE);
                        tinggi.setHint("Masukkan tinggi balok");
                        tinggi.setText(null);
                        tinggi.setError(null);
                        radius.setVisibility(View.GONE);
                        buttonHitung.setVisibility(View.VISIBLE);

                        buttonHitung.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String panjangInput = panjang.getText().toString();
                                String lebarInput = lebar.getText().toString();
                                String tinggiInput = tinggi.getText().toString();

                                if(panjangInput.isEmpty() && lebarInput.isEmpty() && tinggiInput.isEmpty()){
                                    panjang.setError("Harap isi panjang balok.");
                                    lebar.setError("Harap isi lebar balok.");
                                    tinggi.setError("Harap isi tinggi balok.");
                                } else if (panjangInput.isEmpty() && lebarInput.isEmpty()) {
                                    panjang.setError("Harap isi panjang balok.");
                                    lebar.setError("Harap isi lebar balok.");
                                } else if (panjangInput.isEmpty() && tinggiInput.isEmpty()) {
                                    panjang.setError("Harap isi panjang balok.");
                                    tinggi.setError("Harap isi tinggi balok.");
                                } else if (lebarInput.isEmpty() && tinggiInput.isEmpty()) {
                                    lebar.setError("Harap isi lebar balok.");
                                    tinggi.setError("Harap isi tinggi balok.");
                                } else if (panjangInput.isEmpty()) {
                                    panjang.setError("Harap isi panjang balok.");
                                } else if (lebarInput.isEmpty()) {
                                    lebar.setError("Harap isi lebar balok.");
                                } else if (tinggiInput.isEmpty()) {
                                    tinggi.setError("Harap isi tinggi balok.");
                                } else {
                                    double panjangBalok = Double.parseDouble(panjangInput);
                                    double lebarBalok = Double.parseDouble(lebarInput);
                                    double tinggiBalok = Double.parseDouble(tinggiInput);
                                    double volume = panjangBalok * lebarBalok * tinggiBalok;
                                    hasil.setText("Volume balok = " + String.format("%.2f", volume));
                                }
                            }
                        });


                        break;

                    case 3 :
                        panjang.setVisibility(View.GONE);
                        lebar.setVisibility(View.GONE);
                        tinggi.setVisibility(View.VISIBLE);
                        tinggi.setHint("Masukkan tinggi kerucut");
                        tinggi.setText(null);
                        tinggi.setError(null);
                        radius.setVisibility(View.VISIBLE);
                        radius.setHint("Masukkan jari-jari kerucut");
                        radius.setText(null);
                        radius.setError(null);
                        buttonHitung.setVisibility(View.VISIBLE);

                        buttonHitung.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String radiusInput = radius.getText().toString();
                                String tinggiInput = tinggi.getText().toString();

                                if(radiusInput.isEmpty() && tinggiInput.isEmpty()) {
                                    radius.setError("Harap isi jari-jari kerucut.");
                                    tinggi.setError("Harap isi tinggi kerucut.");
                                } else if(radiusInput.isEmpty()) {
                                    radius.setError("Harap isi jari-jari kerucut.");
                                } else if(tinggiInput.isEmpty()) {
                                    tinggi.setError("Harap isi tinggi kerucut.");

                                } else {
                                    double radiusKerucut = Double.parseDouble(radiusInput);
                                    double tinggiKerucut = Double.parseDouble(tinggiInput);
                                    double volume = (1.0/3.0) * Math.PI * Math.pow(radiusKerucut, 2) * tinggiKerucut;
                                    hasil.setText("Volume kerucut = " + String.format("%.2f", volume));
                                    hasil.setTextColor(Color.parseColor("#D10F0F"));
                                }
                            }
                        });


                        break;

                    default :
                        panjang.setVisibility(View.GONE);
                        lebar.setVisibility(View.GONE);
                        tinggi.setVisibility(View.GONE);
                        radius.setVisibility(View.GONE);
                        buttonHitung.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}