package com.example.tp12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    EditText etjari, etpanjang, ettinggi, etlebar;
    Button btn;
    TextView hasil, tvjari, tvtinggi, tvlebar, tvpanjang;

    LinearLayout lltinggi, lljari, lllebar, llpanjang;

    Spinner spinner;

    ArrayList<String> bgr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvpanjang = findViewById(R.id.tvpanjang);
        tvlebar = findViewById(R.id.tvlebar);
        tvtinggi = findViewById(R.id.tvtinggi);
        spinner = findViewById(R.id.spinner1);
        tvjari = findViewById(R.id.tvjari);
        etjari = findViewById(R.id.etjari);
        etpanjang = findViewById(R.id.etpanjang);
        etlebar = findViewById(R.id.etlebar);
        ettinggi = findViewById(R.id.ettinggi);
        btn = findViewById(R.id.btn);
        hasil = findViewById(R.id.hasil);
        lljari =findViewById(R.id.lljari);
        lllebar =findViewById(R.id.lllebar);
        lltinggi =findViewById(R.id.lltinggi);
        llpanjang =findViewById(R.id.llpanjang);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spin, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        btn.setOnClickListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (i) {
            case 0:
                etjari.setText(null);
                lljari.setVisibility(View.VISIBLE);
                llpanjang.setVisibility(View.GONE);
                lllebar.setVisibility(View.GONE);
                lltinggi.setVisibility(View.GONE);
                break;


            case 1:
                etjari.setText(null);
                ettinggi.setText(null);
                lljari.setVisibility(View.VISIBLE);
                llpanjang.setVisibility(View.GONE);
                lllebar.setVisibility(View.GONE);
                lltinggi.setVisibility(View.VISIBLE);
                break;

            case 2:
                ettinggi.setText(null);
                lljari.setVisibility(View.GONE);
                llpanjang.setVisibility(View.VISIBLE);
                lllebar.setVisibility(View.VISIBLE);
                lltinggi.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    @Override
    public void onClick(View view) {

        String j = etjari.getText().toString();
        String p = etpanjang.getText().toString();
        String l = etlebar.getText().toString();
        String t = ettinggi.getText().toString();
        int sp = spinner.getSelectedItemPosition();

        if (sp == 0) {
            try {
                if (j.isEmpty()) {
                    etjari.setError("Jangan dikosongkan!");
                } else {
                    Long jari = Long.valueOf(etjari.getText().toString());
                    double Hasil = calcVolume(jari);
                    hasil.setText((String.format("%.2f", Hasil)));

                }
            } catch (NumberFormatException ex) {
                Toast.makeText(MainActivity.this, "inputan terlalu besar", Toast.LENGTH_SHORT).show();
            }
        } else if (sp == 1) {
            try {
                boolean isEmpty = false;
                if (t.isEmpty()) {
                    ettinggi.setError("Jangan dikosongkan!");
                    isEmpty = true;
                }
                if (j.isEmpty()) {
                    etjari.setError("Jangan dikosongkan!");
                    isEmpty = true;
                }
                if (!isEmpty) {
                    Long jj = Long.valueOf(etjari.getText().toString());
                    Long ttg = Long.valueOf(ettinggi.getText().toString());

                    double Hasil = calcVolume(jj,ttg);
                    hasil.setText(String.format("%.2f", Hasil));
                }
            } catch (NumberFormatException ex) {
                Toast.makeText(MainActivity.this, "Inputan terlalu besar", Toast.LENGTH_SHORT).show();
            }
        } else if (sp == 2) {
            try {
                boolean isEmpty = false;
                if (p.isEmpty()) {
                    etpanjang.setError("Jangan dikosongkan!");
//                            Toast.makeText(MainActivity.this, "Jangan dikosongkan!", Toast.LENGTH_SHORT).show();
                    isEmpty = true;
                }
                if (t.isEmpty()) {
                    ettinggi.setError("Jangan dikosongkan!");
//                            Toast.makeText(MainActivity.this, "Jangan dikosongkan!", Toast.LENGTH_SHORT).show();
                    isEmpty = true;
                }
                if (l.isEmpty()) {
                    etlebar.setError("Jangan dikosongkan!");
//                            Toast.makeText(MainActivity.this, "Masukkan lebar balok!", Toast.LENGTH_SHORT).show();
                    isEmpty = true;
                }
                if (!isEmpty) {
                    Long Panjang = Long.valueOf(etpanjang.getText().toString());
                    Long Lebar = Long.valueOf(etlebar.getText().toString());
                    Long Tinggi = Long.valueOf(ettinggi.getText().toString());

                    double Hasil = calcVolume(Lebar,Tinggi, Panjang);
                    hasil.setText(String.valueOf(Hasil));
                    ettinggi.clearFocus();
                }
            } catch (NumberFormatException ex) {
                Toast.makeText(MainActivity.this, "inputan terlalu besar", Toast.LENGTH_SHORT).show();
            }
        }

    }
    private double calcVolume(Long radius){
        double Hasil = 4.00 / 3.00 * 3.14 * Math.pow(radius,3);
        return Hasil;
    }

    private double calcVolume(Long radius,Long height){
        double Hasil = 1.00 / 3.00 * Math.pow(radius,2) * height * 3.14;
        return Hasil;
    }

    private double calcVolume(Long width,Long height, Long length){
        double Hasil = width * height * length;
        return Hasil;
    }
}