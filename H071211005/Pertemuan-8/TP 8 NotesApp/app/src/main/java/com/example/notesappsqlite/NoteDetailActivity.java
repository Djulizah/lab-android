package com.example.notesappsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.notesappsqlite.data.NoteHelper;
import com.example.notesappsqlite.data.db.DatabaseContract;
import com.example.notesappsqlite.data.model.Note;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class NoteDetailActivity extends AppCompatActivity {
    // Constants
    public static final String EXTRA_NOTE = "extra_note";
    public static final String EXTRA_POSITION = "extra_position";
    public static final int RESULT_ADD = 101;
    public static final int RESULT_UPDATE = 201;
    public static final int RESULT_DELETE = 301;

    // Attributes
    private Note note;
    private NoteHelper noteHelper;
    private TextInputEditText etTitle, etDescription;
    private Button btnSubmit, btnDelete;
    private int position;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        // Views
        etTitle = findViewById(R.id.et_title);
        etDescription = findViewById(R.id.et_description);
        btnSubmit = findViewById(R.id.btn_submit);
        btnDelete = findViewById(R.id.btn_delete);

        // Get instance of NoteHelper class
        noteHelper = NoteHelper.getInstance(getApplicationContext());
        // Open the database
        noteHelper.open();

        // Get note that has been sent (can be null)
        note = getIntent().getParcelableExtra(EXTRA_NOTE);

        // If note is available, set isEdit to true otherwise create new note instance
        if (note != null) {
            isEdit = true;
            position = getIntent().getIntExtra(EXTRA_POSITION, 0);
        } else {
            note = new Note();
        }

        // Change view depending on isEdit value
        changeViewDetail();

        // Button click listeners
        btnSubmit.setOnClickListener(view -> submit());
        btnDelete.setOnClickListener(view -> delete());
    }

    private void changeViewDetail() {
        String actionBarTitle;
        String buttonTitle;

        if (isEdit) {
            actionBarTitle = "Note Detail";
            buttonTitle = "Update";

            etTitle.setText(note.getTitle());
            etDescription.setText(note.getDescription());

            btnDelete.setVisibility(View.VISIBLE);
        } else {
            actionBarTitle = "Add Note";
            buttonTitle = "Save";
        }

        btnSubmit.setText(buttonTitle);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(actionBarTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void submit() {
        String title = Objects.requireNonNull(etTitle.getText()).toString().trim();
        String description = Objects.requireNonNull(etDescription.getText()).toString().trim();

        if (TextUtils.isEmpty(title)) {
            etTitle.setError("Field cannot be empty");
            return;
        }

        note.setTitle(title);
        note.setDescription(description);

        // Create intent to put result
        Intent intent = new Intent();
        intent.putExtra(EXTRA_NOTE, note);
        intent.putExtra(EXTRA_POSITION, position);

        // Set content values
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.NoteColumns.TITLE, title);
        values.put(DatabaseContract.NoteColumns.DESCRIPTION, description);

        // To put date in ContentValues we need to check if isEdit is true/false
        String date;

        if (isEdit) {
            // Set date value
            date = getString(R.string.edited_at, getCurrentDate());

            // Set date note
            note.setDate(date);

            // Put date value to ContentValues
            values.put(DatabaseContract.NoteColumns.DATE, date);

            // Update data and return result as long type
            long result = noteHelper.update(String.valueOf(note.getId()), values);

            if (result > 0) {
                setResult(RESULT_UPDATE, intent);
                finish();
            } else {
                Toast.makeText(this, "Failed to update data", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Set date value
            date = getString(R.string.created_at, getCurrentDate());

            // Set date note
            note.setDate(date);

            // Put date value to ContentValues
            values.put(DatabaseContract.NoteColumns.DATE, date);

            // Create data and return result as long type
            long result = noteHelper.create(values);

            if (result > 0) {
                note.setId((int) result);
                setResult(RESULT_ADD, intent);
                finish();
            } else {
                Toast.makeText(this, "Failed to create data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void delete() {
        long result = noteHelper.delete(String.valueOf(note.getId()));

        if (result > 0) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_POSITION, position);

            setResult(RESULT_DELETE, intent);
            finish();
        } else {
            Toast.makeText(this, "Failed to delete data", Toast.LENGTH_SHORT).show();
        }
    }
    private String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
        return dateFormat.format(new Date());
    }
}