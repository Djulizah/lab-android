package com.example.tugas8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {
    EditText et_title, et_desc;
    Button btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        et_title = findViewById(R.id.et_title);
        et_desc= findViewById(R.id.et_desc);
        btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(v -> {
            DatabaseHelper databaseHelper = new DatabaseHelper(AddActivity.this);
            databaseHelper.addTask(et_title.getText().toString().trim(), et_desc.getText().toString().trim());
            Intent intent = new Intent(AddActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}