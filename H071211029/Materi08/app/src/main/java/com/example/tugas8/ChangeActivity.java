package com.example.tugas8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ChangeActivity extends AppCompatActivity {
    EditText et_title, et_desc;
    Button btn_update, btn_delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        et_title = findViewById(R.id.et_title);
        et_desc = findViewById(R.id.et_desc);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);

        String id = getIntent().getStringExtra("id");
        String title = getIntent().getStringExtra("title");
        String desc = getIntent().getStringExtra("desc");

        et_title.setText(title);
        et_desc.setText(desc);

        btn_update.setOnClickListener(v -> {
            DatabaseHelper databaseHelper = new DatabaseHelper(ChangeActivity.this);
            databaseHelper.updateTask(id, et_title.getText().toString(), et_desc.getText().toString());
            Intent intent = new Intent(ChangeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        btn_delete.setOnClickListener(v -> {
            DatabaseHelper databaseHelper = new DatabaseHelper(ChangeActivity.this);
            databaseHelper.deleteTask(id);
            Intent intent = new Intent(ChangeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}