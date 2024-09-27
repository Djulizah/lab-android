package com.example.h071211038_nomor2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    EditText input, input2, input3;
    TextView textView, textView2, textView3, result;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        input = findViewById(R.id.input);
        textView = findViewById(R.id.textView);
        input2 = findViewById(R.id.input2);
        textView2 = findViewById(R.id.textView2);
        input3 = findViewById(R.id.input3);
        textView3 = findViewById(R.id.textView3);
        button = findViewById(R.id.button);
        result = findViewById(R.id.result);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i) {
                    case 0:
                        textView.setVisibility(View.GONE);
                        input.setVisibility(View.GONE);
                        textView2.setVisibility(View.GONE);
                        input2.setVisibility(View.GONE);
                        textView3.setText("Jari-jari");
                        input3.setText(null);
                        break;
                    case 1:
                        textView.setVisibility(View.GONE);
                        input.setVisibility(View.GONE);
                        textView2.setVisibility(View.VISIBLE);
                        input2.setVisibility(View.VISIBLE);
                        textView2.setText("Jari-jari");
                        textView3.setText("Tinggi");
                        input2.setText(null);
                        input3.setText(null);
                        break;
                    case 2:
                        textView.setVisibility(View.VISIBLE);
                        input.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        input2.setVisibility(View.VISIBLE);
                        textView.setText("Panjang");
                        textView2.setText("Lebar");
                        textView3.setText("Tinggi");
                        input.setText(null);
                        input2.setText(null);
                        input3.setText(null);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setVisibility(View.GONE);
                String a, b, c;
                a = input.getText().toString();
                b = input2.getText().toString();
                c = input3.getText().toString();

                String selectedItem = spinner.getSelectedItem().toString();
                switch (selectedItem) {
                    case "Bola":
                        try {
                            if (c.isEmpty()) {
                                input3.setError("Input value");
                            } else {
                                double nilai = Double.parseDouble(c);
                                result.setVisibility(View.VISIBLE);
                                double bola = 4.0 / 3.0 * 3.14 * Math.pow(nilai, 3);
                                result.setText("Hasil : " + String.valueOf(decimalFormat.format(bola)));
                            }


                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
                        break;
                    case "Kerucut":
                        if (b.isEmpty()) {
                            input2.setError("Input value");
                        }
                        if (c.isEmpty()) {
                            input3.setError("Input value");
                        }

                        if (!b.isEmpty() && !c.isEmpty()) {
                            double nilai = Double.parseDouble(c);
                            result.setVisibility(View.VISIBLE);
                            double nilai2 = Double.parseDouble(b);
                            double kerucut = 1.0 / 3.0 * 3.14 * Math.pow(nilai2, 2) * nilai;
                            result.setText("Hasil : " + String.valueOf(decimalFormat.format(kerucut)));
                        }
                        break;
                    case "Balok":
                        if (a.isEmpty()) {
                            input.setError("Input value");
                        }
                        if (b.isEmpty()) {
                            input2.setError("Input value");
                        }
                        if (c.isEmpty()) {
                            input3.setError("Input value");
                        }

                        if (!a.isEmpty() && !b.isEmpty() && !c.isEmpty()) {
                            result.setVisibility(View.VISIBLE);
                            double nilai = Double.parseDouble(c);
                            double nilai3 = Double.parseDouble(b);
                            double nilai4 = Double.parseDouble(a);
                            double balok = nilai * nilai3 * nilai4;
                            result.setText("Hasil : " + String.valueOf(decimalFormat.format(balok)));
                        }
                        break;
                }
            }
        });
    }
}