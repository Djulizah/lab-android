package com.example.tuprak8localdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {

    public static final String EXTRA_STUDENT = "extra_student";
    public static final int RESULT_ADD = 101;
    public static final int RESULT_UPDATE = 201;
    public static final int RESULT_DELETE = 301;
    private NotesHelper notesHelper;
    private Notes notes;
    private boolean isEdit = false;
    EditText et_title, et_desc;
    Button btn_save, btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        et_title = findViewById(R.id.et_title);
        et_desc = findViewById(R.id.et_desc);
        btn_save = findViewById(R.id.btn_save);
        btn_delete = findViewById(R.id.btn_delete);

        // Menginisialisasi NotesHelper dan membuka koneksi database
        notesHelper = NotesHelper.getInstance(getApplicationContext());
        notesHelper.open();
        // Mendapatkan data Notes yang dikirim melalui Intent
        notes = getIntent().getParcelableExtra(EXTRA_STUDENT);

        // Mengecek apakah mode edit atau tambah data
        if (notes != null) {
            isEdit = true;
        } else {
            notes = new Notes();
        }

        String actionBarTitle;
        String buttonTitle;

        // Menentukan judul dan teks tombol berdasarkan mode edit atau tambah data
        if (isEdit) {
            actionBarTitle = "Change";
            buttonTitle = "Update";
            if (notes != null) {
                et_title.setText(notes.getTitle());
                et_desc.setText(notes.getDescription());
            }
            btn_delete.setVisibility(View.VISIBLE);
        } else {
            actionBarTitle = "Add";
            buttonTitle = "Save";
        }

        btn_save.setText(buttonTitle);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(actionBarTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        btn_save.setOnClickListener(view -> save());
        btn_delete.setOnClickListener(view -> delete());
    }

    private void save() {
        String title = et_title.getText().toString().trim();
        String description = et_desc.getText().toString().trim();
//
        if (title.isEmpty()) {
            et_title.setError("Please fill this field");
            return;
        }
        if (description.isEmpty()) {
            et_desc.setError("Please fill this field");
            return;
        }

        notes.setTitle(title);
        notes.setDescription( description);

        Date currentDate = new Date();
        String pattern = "MM/dd/yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern, Locale.getDefault());
        String createdAt = "CreatedAt " + df.format(currentDate);

        // Membuat Intent untuk mengirim data kembali ke Activity sebelumnya
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        intent.putExtra(EXTRA_STUDENT, notes);
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.NotesColumns.TITLE, title);
        values.put(DatabaseContract.NotesColumns.DESCRIPTION, description);

        if (isEdit) {// Jika dalam mode edit, update data di database
            String editAt = "EditedAt " + df.format(currentDate);
            values.put(DatabaseContract.NotesColumns.CREATEDAT, editAt);
            long result = notesHelper.update(String.valueOf(notes.getId()), values);
            if (result > 0) {
                setResult(RESULT_UPDATE, intent);
                finish();
                startActivity(intent);
            } else {
                Toast.makeText(this, "Failed to update data", Toast.LENGTH_SHORT).show();
            }
        } else {// Jika dalam mode tambah data, tambahkan data baru ke database
            values.put(DatabaseContract.NotesColumns.CREATEDAT, createdAt);
            long result = notesHelper.insert(values);
            if (result > 0) {
                notes.setId((int) result);
                setResult(RESULT_ADD, intent);
                finish();
                startActivity(intent);
            } else {
                Toast.makeText(this, "Failed to add data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void delete() {
        long result = notesHelper.deleteById(String.valueOf(notes.getId()));
        if (result > 0) {
            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
            setResult(RESULT_DELETE, intent);
            finish();
            startActivity(intent);
        } else {
            Toast.makeText(this, "Failed to delete data", Toast.LENGTH_SHORT).show();
        }
    }
}