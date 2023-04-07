package com.example.assignment_1_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;


public class MainActivity extends AppCompatActivity {
    String[] blocks = {"Bola", "Kerucut", "Balok"};
    EditText fieldtxtj, fieldtxtt, fieldtxtp, fieldtxtl;
    Button btn;
    TextView result;

    Spinner blockSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        blockSpinner = findViewById(R.id.blockSpinner);
        fieldtxtt = findViewById(R.id.fieldtxtt);
        fieldtxtl = findViewById(R.id.fieldtxtl);
        fieldtxtp = findViewById(R.id.fieldtxtp);
        fieldtxtj = findViewById(R.id.fieldtxtj);
        btn = findViewById(R.id.btn);
        result = findViewById(R.id.result);


        //declaring spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_item, blocks);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blockSpinner.setAdapter(adapter);
        blockSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_LONG).show();
                switch (position) {
                    case 0:
                        fieldtxtj.setVisibility(View.VISIBLE);
                        fieldtxtp.setVisibility(View.GONE);
                        fieldtxtl.setVisibility(View.GONE);
                        fieldtxtt.setVisibility(View.GONE);
                        break;
                    case 1:
                        fieldtxtj.setVisibility(View.VISIBLE);
                        fieldtxtp.setVisibility(View.GONE);
                        fieldtxtl.setVisibility(View.GONE);
                        fieldtxtt.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        fieldtxtj.setVisibility(View.GONE);
                        fieldtxtp.setVisibility(View.VISIBLE);
                        fieldtxtl.setVisibility(View.VISIBLE);
                        fieldtxtt.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {/* none */}
        });


        //CALC
        fieldtxtj = (EditText) findViewById(R.id.fieldtxtj);
        fieldtxtl = (EditText) findViewById(R.id.fieldtxtl);
        fieldtxtp = (EditText) findViewById(R.id.fieldtxtp);
        fieldtxtt = (EditText) findViewById(R.id.fieldtxtt);
        btn = (Button) findViewById(R.id.btn);
        result = (TextView) findViewById(R.id.result);

        //BALOK
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (blockSpinner.getSelectedItem().toString().equals(blocks[0])) {
                    if (fieldtxtj.getText().toString().isEmpty()) {
                        fieldtxtj.setError("Isi Field jari-jari");
                    } else {
                        int j = Integer.valueOf(fieldtxtj.getText().toString());

                        double hasil = Math.pow(j, 3) * Math.PI * 4 / 3;
                        result.setText(String.valueOf(hasil));
                    }
                } else if (blockSpinner.getSelectedItem().toString().equals(blocks[1])) {
                    if (fieldtxtj.getText().toString().isEmpty() && fieldtxtt.getText().toString().isEmpty()) {
                        fieldtxtj.setError("Isi Field jari-jari");
                        fieldtxtt.setError("Isi Field tinggi");
                    } else if (fieldtxtj.getText().toString().isEmpty()) {
                        fieldtxtj.setError("Isi Field jari-jari");
                    } else if (fieldtxtt.getText().toString().isEmpty()) {
                        fieldtxtt.getText().toString().isEmpty();
                    } else {
                        int j = Integer.valueOf(fieldtxtj.getText().toString());
                        int t = Integer.valueOf(fieldtxtt.getText().toString());

                        double hasil = (Math.pow(j, 2) * Math.PI * t * 1 / 3);
                        result.setText(String.valueOf(hasil));
                    }
                } else if (blockSpinner.getSelectedItem().toString().equals(blocks[2])) {
                    if (fieldtxtp.getText().toString().isEmpty() && fieldtxtl.getText().toString().isEmpty() && fieldtxtt.getText().toString().isEmpty()) {
                        fieldtxtp.setError("Isi Field Panjang");
                        fieldtxtl.setError("Isi Field Lebar");
                        fieldtxtt.setError("Isi Field tinggi");
                    } else if (fieldtxtp.getText().toString().isEmpty() && fieldtxtl.getText().toString().isEmpty()) {
                        fieldtxtp.setError("Isi Field Panjang");
                        fieldtxtl.setError("Isi Field Lebar");
                    } else if (fieldtxtl.getText().toString().isEmpty() && fieldtxtt.getText().toString().isEmpty()) {
                        fieldtxtl.setError("Isi Field Lebar");
                        fieldtxtt.setError("Isi Field tinggi");
                    } else if (fieldtxtp.getText().toString().isEmpty() && fieldtxtt.getText().toString().isEmpty()) {
                        fieldtxtp.setError("Isi Field Panjang");
                        fieldtxtt.setError("Isi Field tinggi");
                    } else if (fieldtxtp.getText().toString().isEmpty()) {
                        fieldtxtp.setError("Isi Field Panjang");
                    } else if (fieldtxtl.getText().toString().isEmpty()) {
                        fieldtxtl.setError("Isi Field Lebar");
                    } else if (fieldtxtt.getText().toString().isEmpty()) {
                        fieldtxtt.setError("Isi Field tinggi");
                    } else {
                        int p = Integer.valueOf(fieldtxtp.getText().toString());
                        int l = Integer.valueOf(fieldtxtl.getText().toString());
                        int t = Integer.valueOf(fieldtxtt.getText().toString());

                        int hasil = p * l * t;
                        result.setText(String.valueOf(hasil));
                    }
                }


            }
        });
    }
}