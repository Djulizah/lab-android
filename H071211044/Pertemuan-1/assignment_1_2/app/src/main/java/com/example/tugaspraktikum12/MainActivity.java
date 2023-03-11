package com.example.tugaspraktikum12;

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

    TextView jari, panjang, lebar, tinggi,hasilll;
    EditText editjari, editpanjang, editlebar, edittinggi;
    Button button;
    LinearLayout jarjariiatas, panjangatas, lebaratas, tinggiatas;
    Spinner spinner;

    ArrayList<String> bangunruang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        panjang = findViewById(R.id.panjang);
        lebar = findViewById(R.id.lebar);
        tinggi = findViewById(R.id.tinggi);
        jari = findViewById(R.id.jari);
        spinner = findViewById(R.id.spinner);
        editjari = findViewById(R.id.editjari);
        editpanjang = findViewById(R.id.editpanjang);
        editlebar = findViewById(R.id.editlebar);
        edittinggi = findViewById(R.id.edittinggi);
        jarjariiatas = findViewById(R.id.jarijariatas);
        panjangatas = findViewById(R.id.panjangatas);
        lebaratas = findViewById(R.id.lebaratas);
        tinggiatas = findViewById(R.id.tinggiatas);
        hasilll = findViewById(R.id.hasilll);
        button = findViewById(R.id.button);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spin, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        button.setOnClickListener(this);

    }
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        editjari.setText(null);
                        jarjariiatas.setVisibility(View.VISIBLE);
                        panjangatas.setVisibility(View.GONE);
                        lebaratas.setVisibility(View.GONE);
                        tinggiatas.setVisibility(View.GONE);
                        break;

                    case 1:
                        editjari.setText(null);
                        jarjariiatas.setVisibility(View.VISIBLE);
                        panjangatas.setVisibility(View.GONE);
                        lebaratas.setVisibility(View.GONE);
                        tinggiatas.setVisibility(View.VISIBLE);
                        break;

                    case 2:
                        edittinggi.setText(null);
                        jarjariiatas.setVisibility(View.GONE);
                        panjangatas.setVisibility(View.VISIBLE);
                        lebaratas.setVisibility(View.VISIBLE);
                        tinggiatas.setVisibility(View.VISIBLE);
                        break;
                }
            }
    private double calcVolume(Long radius){
        double Hasilll = 4.00 / 3.00 * 3.14 * Math.pow(radius,3);
        return Hasilll;
    }

    private double calcVolume(Long radius,Long height){
        double Hasilll = 1.00 / 3.00 * Math.pow(radius,2) * height * 3.14;
        return Hasilll;
    }

                    private double calcVolume(Long width,Long height, Long length){
                        double Hasilll = width * height * length;
                        return Hasilll;
                    }
                    @Override
                    public void onClick(View view) {
                        String jari = editjari.getText().toString();
                        String panjang = editpanjang.getText().toString();
                        String lebar = editlebar.getText().toString();
                        String tinggi = edittinggi.getText().toString();
                        int spin = spinner.getSelectedItemPosition();

                    if (spin == 0) {
                        try {
                            if (jari.isEmpty()) {
                                editjari.setError("Jangan dikosongkan!");
                            } else {
                                Long jarii = Long.valueOf(editjari.getText().toString());
                                double Hasilll = calcVolume(jarii);
                                hasilll.setText((String.format("%.2f", Hasilll)));

                            }
                        } catch (NumberFormatException ex) {
                            Toast.makeText(MainActivity.this, "inputan terlalu besar", Toast.LENGTH_SHORT).show();
                        }
                    } else if (spin == 1) {
                        try {
                            boolean isEmpty = false;
                            if (tinggi.isEmpty()) {
                                edittinggi.setError("Jangan dikosongkan!");
                                isEmpty = true;
                            }
                            if (jari.isEmpty()) {
                                editjari.setError("Jangan dikosongkan!");
                                isEmpty = true;
                            }
                            if (!isEmpty) {
                                Long jarii = Long.valueOf(editjari.getText().toString());
                                Long tinggii = Long.valueOf(edittinggi.getText().toString());

                                double Hasilll = calcVolume(jarii,tinggii);
                                hasilll.setText(String.format("%.2f", Hasilll));
                            }
                        } catch (NumberFormatException ex) {
                            Toast.makeText(MainActivity.this, "Inputan terlalu besar", Toast.LENGTH_SHORT).show();
                        }
                    } else if (spin == 2) {
                        try {
                            boolean isEmpty = false;
                            if (panjang.isEmpty()) {
                                editpanjang.setError("Jangan dikosongkan!");
                                isEmpty = true;
                            }
                            if (tinggi.isEmpty()) {
                                editpanjang.setError("Jangan dikosongkan!");
                                isEmpty = true;
                            }
                            if (lebar.isEmpty()) {
                                editlebar.setError("Jangan dikosongkan!");
                                isEmpty = true;
                            }
                            if (!isEmpty) {
                                Long Panjang = Long.valueOf(editpanjang.getText().toString());
                                Long Lebar = Long.valueOf(editlebar.getText().toString());
                                Long Tinggi = Long.valueOf(edittinggi.getText().toString());

                                double Hasilll = calcVolume(Lebar,Tinggi,Panjang);
                                hasilll.setText(String.valueOf(Hasilll));
                                edittinggi.clearFocus();
                            }
                        } catch (NumberFormatException ex) {
                            Toast.makeText(MainActivity.this, "inputan terlalu besar", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView){
    }
}
