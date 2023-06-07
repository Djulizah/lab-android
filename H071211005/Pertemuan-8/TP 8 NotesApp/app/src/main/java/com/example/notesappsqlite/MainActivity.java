package com.example.notesappsqlite;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notesappsqlite.data.NoteHelper;
import com.example.notesappsqlite.data.model.Note;
import com.example.notesappsqlite.helper.MapHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> resultLauncher;
    private NoteAdapter adapter;
    private ProgressBar progressBar;
    private RecyclerView rvNotes;
    private TextView tvAlert;
    private TextInputLayout tfSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set result launcher
        resultLauncher = setResultLauncher();

        // Adapter instance
        adapter = new NoteAdapter();
        adapter.setOnItemClickListener((note, position) -> {
            Intent toDetail = new Intent(MainActivity.this, NoteDetailActivity.class);
            toDetail.putExtra(NoteDetailActivity.EXTRA_NOTE, note);
            toDetail.putExtra(NoteDetailActivity.EXTRA_POSITION, position);
            resultLauncher.launch(toDetail);
        });

        // Another view
        rvNotes = findViewById(R.id.rv_notes);
        tvAlert = findViewById(R.id.tv_alert);
        progressBar = findViewById(R.id.progress_bar);
        tfSearch = findViewById(R.id.tf_search);

        // Rv settings
        rvNotes.setLayoutManager(new LinearLayoutManager(this));
        rvNotes.setAdapter(adapter);

        // Fab
        FloatingActionButton fabAdd = findViewById(R.id.fab_add);

        // Fab on click
        fabAdd.setOnClickListener(view -> {
            Intent toCreate = new Intent(MainActivity.this, NoteDetailActivity.class);
            resultLauncher.launch(toCreate);
        });

        // Load notes from db and set it to adapter
        loadNotes();

        // Searching
        searchListener();
    }
    private ActivityResultLauncher<Intent> setResultLauncher() {
        return registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getData() != null) {
                        if (result.getResultCode() == NoteDetailActivity.RESULT_ADD) {
                            Note note = result.getData().getParcelableExtra(NoteDetailActivity.EXTRA_NOTE);

                            adapter.addItem(note);

                            Toast.makeText(this, "Item create successfully", Toast.LENGTH_SHORT).show();
                        } else if (result.getResultCode() == NoteDetailActivity.RESULT_UPDATE) {
                            Note note = result.getData().getParcelableExtra(NoteDetailActivity.EXTRA_NOTE);
                            int position = result.getData().getIntExtra(NoteDetailActivity.EXTRA_POSITION, 0);

                            adapter.updateItem(note, position);

                            Toast.makeText(this, "Item edited successfully", Toast.LENGTH_SHORT).show();
                        } else if (result.getResultCode() == NoteDetailActivity.RESULT_DELETE) {
                            int position = result.getData().getIntExtra(NoteDetailActivity.EXTRA_POSITION, 0);

                            adapter.removeItem(position);

                            Toast.makeText(this, "Item deleted successfully", Toast.LENGTH_SHORT).show();
                        }

                        alert(adapter.getNotes().isEmpty());
                    }
                }
        );
    }

    private void searchListener() {
        TextInputEditText etSearch = findViewById(R.id.et_search);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // no op
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchNotes(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // no op
            }
        });
    }

    private void loading(boolean value) {
        if (value) {
            progressBar.setVisibility(View.VISIBLE);
            rvNotes.setVisibility(View.INVISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            rvNotes.setVisibility(View.VISIBLE);
        }
    }

    private void alert(boolean value, String message) {
        if (value) {
            tfSearch.setEnabled(message.equals(getString(R.string.empty_result_alert)));
            tvAlert.setText(message);
            tvAlert.setVisibility(View.VISIBLE);
        } else {
            tvAlert.setVisibility(View.INVISIBLE);
            tfSearch.setEnabled(true);
        }
    }

    private void alert(boolean value) {
        alert(value, getString(R.string.empty_notes_alert));
    }

    private void searchNotes(String query) {
        new LoadNotesAsync(this, new LoadNotesCallback() {
            @Override
            public void preExecute() {
                loading(true);
            }

            @Override
            public ArrayList<Note> onExecute(NoteHelper noteHelper) {
                if (TextUtils.isEmpty(query)) {
                    return MapHelper.mapCursorToArrayList(noteHelper.searchByTitle(""));
                } else {
                    return MapHelper.mapCursorToArrayList(noteHelper.searchByTitle(query));
                }
            }

            @Override
            public void postExecute(ArrayList<Note> notes) {
                loading(false);

                if (notes.size() > 0) {
                    adapter.setNotes(notes);
                    alert(false);
                } else {
                    adapter.setNotes(new ArrayList<>());
                    alert(true, getString(R.string.empty_result_alert));
                }
            }
        }).execute();
    }

    private void loadNotes() {
        new LoadNotesAsync(this, new LoadNotesCallback() {
            @Override
            public void preExecute() {
                loading(true);
            }

            @Override
            public ArrayList<Note> onExecute(NoteHelper noteHelper) {
                return MapHelper.mapCursorToArrayList(noteHelper.readAll());
            }

            @Override
            public void postExecute(ArrayList<Note> notes) {
                loading(false);

                if (notes.size() > 0) {
                    adapter.setNotes(notes);
                    alert(false);
                } else {
                    adapter.setNotes(new ArrayList<>());
                    alert(true);
                }
            }
        }).execute();
    }

    private static class LoadNotesAsync {
        private final Context context;
        private final LoadNotesCallback callback;

        private LoadNotesAsync(Context context, LoadNotesCallback callback) {
            this.context = context;
            this.callback = callback;
        }

        void execute() {
            callback.preExecute();

            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executor.execute(() -> {
                // Get instance and open notes database
                NoteHelper noteHelper = NoteHelper.getInstance(context);
                noteHelper.open();

                // Map cursor to ArrayList notes
                ArrayList<Note> notes = callback.onExecute(noteHelper);

                // Close database
                noteHelper.close();

                // Execute post process
                handler.post(() -> callback.postExecute(notes));
            });
        }
    }

    interface LoadNotesCallback {
        void preExecute();

        ArrayList<Note> onExecute(NoteHelper noteHelper);

        void postExecute(ArrayList<Note> notes);
    }
}