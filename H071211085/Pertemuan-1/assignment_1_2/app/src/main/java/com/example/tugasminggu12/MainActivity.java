package com.example.tugasminggu12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    LinearLayout layoutBalok, layoutHasil;
    EditText lebarBalok, panjangBalok, tinggi, jariSemua;
    TextView hasil, tvPanjang, tvLebar, tvJari, tvTinggi;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.bangunRuang_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);

        layoutBalok = findViewById(R.id.rBalok);
        layoutHasil = findViewById(R.id.LayHasil);

        tinggi = findViewById(R.id.tinggi);
        lebarBalok = findViewById(R.id.lebarBalok);
        jariSemua = findViewById(R.id.jari);
        panjangBalok = findViewById(R.id.panjangBalok);

        tvPanjang = findViewById(R.id.tvPanjang);
        tvLebar = findViewById(R.id.tvLebar);
        tvJari = findViewById(R.id.tvJari);
        tvTinggi = findViewById(R.id.tvTinggi);

        hasil = findViewById(R.id.tvHasil);

        button = findViewById(R.id.button);

        double phi = 3.14;


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1 :
                        jariSemua.setText(" ");
                        button.setVisibility(view.VISIBLE);
                        layoutBalok.setVisibility(view.VISIBLE);
                        tinggi.setVisibility(view.GONE);
                        jariSemua.setVisibility(view.VISIBLE);
                        panjangBalok.setVisibility(view.GONE);
                        lebarBalok.setVisibility(view.GONE);
                        tvLebar.setVisibility(view.GONE);
                        tvPanjang.setVisibility(view.GONE);
                        tvTinggi.setVisibility(view.GONE);
                        tvJari.setVisibility(view.VISIBLE);
                        layoutHasil.setVisibility(view.GONE);

                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (TextUtils.isEmpty(jariSemua.getText().toString().trim())) {

                                    jariSemua.setError("Masukkan jari-jari terlebih dahulu!");

                                } else {
                                    // Bola
                                    double r = Double.parseDouble(jariSemua.getText().toString());

                                    double result = (4.0 / 3.0) * phi * (r * r * r);

                                    layoutHasil.setVisibility(view.VISIBLE);
                                    hasil.setText("Hasil : " + String.format("%.2f", result));
                                }
                            }
                        });

                        break;

                    case 2:
                        tinggi.setText(" ");
                        jariSemua.setText(" ");
                        button.setVisibility(view.VISIBLE);
                        layoutBalok.setVisibility(view.VISIBLE);
                        tinggi.setVisibility(view.VISIBLE);
                        jariSemua.setVisibility(view.VISIBLE);
                        panjangBalok.setVisibility(view.GONE);
                        lebarBalok.setVisibility(view.GONE);
                        tvPanjang.setVisibility(view.GONE);
                        tvLebar.setVisibility(view.GONE);
                        tvTinggi.setVisibility(view.VISIBLE);
                        tvJari.setVisibility(view.VISIBLE);
                        layoutHasil.setVisibility(view.GONE);

                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(TextUtils.isEmpty(jariSemua.getText().toString().trim()) && TextUtils.isEmpty(tinggi.getText().toString().trim())) {

                                    jariSemua.setError("Masukkan jari-jari terlebih dahulu");
                                    tinggi.setError("Masukkan tinggi terlebih dahulu");

                                } else if(TextUtils.isEmpty(jariSemua.getText().toString().trim())) {

                                    jariSemua.setError("Masukkan jari-jari terlebih dahulu");

                                } else if(TextUtils.isEmpty(tinggi.getText().toString().trim())) {

                                    tinggi.setError("Masukkan tinggi terlebih dahulu");

                                } else {
                                    // Kerucut //
                                    double r = Double.parseDouble(jariSemua.getText().toString());
                                    double t = Double.parseDouble(tinggi.getText().toString());

                                    double result = ((1.0 / (3.0) * (phi * r * r) * t));

                                    layoutHasil.setVisibility(view.VISIBLE);
                                    hasil.setText("Hasil : " + String.format("%.2f", result));
                                }
                            }
                        });
                        break;
                    case 3:
                        panjangBalok.setText(" ");
                        lebarBalok.setText(" ");
                        tinggi.setText(" ");
                        button.setVisibility(view.VISIBLE);
                        layoutBalok.setVisibility(view.VISIBLE);
                        tinggi.setVisibility(view.VISIBLE);
                        jariSemua.setVisibility(view.GONE);
                        panjangBalok.setVisibility(view.VISIBLE);
                        lebarBalok.setVisibility(view.VISIBLE);
                        tvJari.setVisibility(view.GONE);
                        tvPanjang.setVisibility(view.VISIBLE);
                        tvLebar.setVisibility(view.VISIBLE);
                        tvTinggi.setVisibility(view.VISIBLE);
                        layoutHasil.setVisibility(view.GONE);

                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if(TextUtils.isEmpty(panjangBalok.getText().toString().trim()) &&
                                        TextUtils.isEmpty(lebarBalok.getText().toString().trim()) &&
                                        TextUtils.isEmpty(tinggi.getText().toString().trim())) {

                                    panjangBalok.setError("Masukkan jumlah panjang!");
                                    lebarBalok.setError("Masukkan jumlah lebar!");
                                    tinggi.setError("Masukkan jumlah tinggi!");

                                } else if(TextUtils.isEmpty(lebarBalok.getText().toString().trim()) && TextUtils.isEmpty(panjangBalok.getText().toString().trim())) {

                                    lebarBalok.setError("Masukkan jumlah lebar!");
                                    panjangBalok.setError("Masukkan jumlah panjang!");

                                } else if (TextUtils.isEmpty(tinggi.getText().toString().trim()) && TextUtils.isEmpty(panjangBalok.getText().toString().trim())) {

                                    tinggi.setError("Masukkan jumlah tinggi!");
                                    panjangBalok.setError("Masukkan jumlah panjang!");

                                } else if (TextUtils.isEmpty(tinggi.getText().toString().trim()) && TextUtils.isEmpty(lebarBalok.getText().toString().trim())) {

                                    tinggi.setError("Masukkan jumlah tinggi!");
                                    lebarBalok.setError("Masukkan jumlah lebar!");

                                } else if(TextUtils.isEmpty(tinggi.getText().toString().trim())) {

                                    tinggi.setError("Masukkan jumlah tinggi!");

                                } else if (TextUtils.isEmpty(lebarBalok.getText().toString().trim())) {

                                    lebarBalok.setError("Masukkan jumlah lebar!");

                                } else if (TextUtils.isEmpty(panjangBalok.getText().toString().trim())) {

                                    panjangBalok.setError("Masukkan jumlah lebar!");

                                } else {
                                    // Balok //
                                    double p = Double.parseDouble(panjangBalok.getText().toString());
                                    double l = Double.parseDouble(lebarBalok.getText().toString());
                                    double t = Double.parseDouble(tinggi.getText().toString());

                                    double result = p * l * t;

                                    layoutHasil.setVisibility(view.VISIBLE);
                                    hasil.setText("Hasil : " + String.format("%.2f", result));
                                }
                            }
                        });
                        break;

                    default:
                        layoutBalok.setVisibility(view.GONE);
                        layoutHasil.setVisibility(view.GONE);
                        button.setVisibility(view.GONE);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        }

}