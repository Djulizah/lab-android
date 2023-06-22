package com.example.assignment_8_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class FormActivity extends AppCompatActivity {
    public static final String EXTRA_NOTE = "extra_note";
    public static final int RESULT_ADD = 101;
    public static final int RESULT_UPDATE = 201;
    public static final int RESULT_DELETE = 301;

    EditText etTitle, etDesc;
    Button btnSubmit, btnDelete;
    NoteModel note;
    NoteHelper noteHelper;
    boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        etTitle = findViewById(R.id.et_title);
        etDesc = findViewById(R.id.et_description);
        btnDelete = findViewById(R.id.btn_delete);
        btnSubmit = findViewById(R.id.btn_submit);

        noteHelper = NoteHelper.getInstance(getApplicationContext());
        noteHelper.open();

        note = getIntent().getParcelableExtra(EXTRA_NOTE);
        if (note != null) {
            isEdit = true;
        } else {
            note = new NoteModel();
        }

        String actionBarTitle;
        String buttonTitle;
        if (isEdit) {
            actionBarTitle = "Change";
            buttonTitle = "Update";
            if (note != null) {
                etTitle.setText(note.getTitle());
                etDesc.setText(note.getDescription());
            }
            btnDelete.setVisibility(View.VISIBLE);
        } else {
            actionBarTitle = "Add";
            buttonTitle = "Save";
        }

        btnSubmit.setText(buttonTitle);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(actionBarTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        btnSubmit.setOnClickListener(view -> save());
        btnDelete.setOnClickListener(view -> delete());
    }

    private void delete() {
        long result = noteHelper.deleteById(String.valueOf(note.getId()));
        if (result > 0) {
            Intent intent = new Intent();
//            intent.putExtra("EXTRA_POSITION", intent.getIntExtra("EXTRA_POSITION",0));
            setResult(RESULT_DELETE, intent);
            finish();
        } else {
            Toast.makeText(this, "Failed to delete data", Toast.LENGTH_SHORT).show();
        }
    }

    private void save() {
        String title = etTitle.getText().toString().trim();
        String description = etDesc.getText().toString().trim();
        String createdAt = "placeholder";
        if (title.isEmpty()) {
            etTitle.setError("Please fill this field");
            return;
        }
        if (description.isEmpty()) {
            etDesc.setError("Please fill this field");
            return;
        }

        note.setTitle(title);
        note.setDescription(description);
        note.setCreatedAt(createdAt);

        Intent intent = new Intent();
        intent.putExtra(EXTRA_NOTE, note);

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.NoteColumns.TITLE, title);
        values.put(DatabaseContract.NoteColumns.DESCRIPTION, description);
        values.put(DatabaseContract.NoteColumns.CREATED_AT, createdAt);
        if (isEdit) {
            long result = noteHelper.update(String.valueOf(note.getId()), values);
            if (result > 0) {
//                intent.putExtra("EXTRA_POSITION", intent.getIntExtra("EXTRA_POSITION",0));
                setResult(RESULT_UPDATE, intent);
                finish();
            } else {
                Toast.makeText(this, "Failed to update data", Toast.LENGTH_SHORT).show();
            }
        } else {
            long result = noteHelper.insert(values);
            if (result > 0) {
                note.setId((int) result);
                setResult(RESULT_ADD, intent);
                finish();
            } else {
                Toast.makeText(this, "Failed to add data", Toast.LENGTH_SHORT).show();
            }
        }
    }
}