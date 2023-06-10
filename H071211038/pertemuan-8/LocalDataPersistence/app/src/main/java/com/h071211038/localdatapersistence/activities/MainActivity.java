package com.h071211038.localdatapersistence.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.h071211038.localdatapersistence.db.MappingHelper;
import com.h071211038.localdatapersistence.adapters.NoteAdapter;
import com.h071211038.localdatapersistence.R;
import com.h071211038.localdatapersistence.db.NoteHelper;
import com.h071211038.localdatapersistence.entities.Note;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private NoteAdapter noteAdapter;
    private LoadNotesAsync loadNotesAsync;

    private final ActivityResultLauncher<Intent> resultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getData() != null) {
                    switch (result.getResultCode()) {
                        case FormActivity.RESULT_ADD:
                            loadNotesAsync.execute();
                            Toast.makeText(this, "Note added successfully", Toast.LENGTH_SHORT).show();
                            break;
                        case FormActivity.RESULT_UPDATE:
                            loadNotesAsync.execute();
                            Toast.makeText(this, "Note updated successfully", Toast.LENGTH_SHORT).show();
                            break;
                        case FormActivity.RESULT_DELETE:
                            loadNotesAsync.execute();
                            Toast.makeText(this, "Note deleted successfully", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton btnAdd = findViewById(R.id.add_button);
        EditText editText = findViewById(R.id.et_search);
        TextView textView = findViewById(R.id.text_view);
        LinearLayout linearLayout = findViewById(R.id.linear_layout);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                noteAdapter.searchNotes(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        btnAdd.setOnClickListener(view -> {
            Intent intent = new Intent(this, FormActivity.class);
            resultLauncher.launch(intent);
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        noteAdapter = new NoteAdapter( MainActivity.this, resultLauncher);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(noteAdapter);

        loadNotesAsync = new LoadNotesAsync(this, notes -> {
            if (notes.size() > 0) {
                linearLayout.setVisibility(View.VISIBLE);
                textView.setVisibility(View.GONE);

                String searchKeyword = editText.getText().toString();

                if (searchKeyword.isEmpty()) {
                    noteAdapter.setNotes(notes);
                } else {
                    noteAdapter.searchNotes(searchKeyword);
                }

            } else {
                linearLayout.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
            }
        });

        loadNotesAsync.execute();
    }

    private static class LoadNotesAsync {

        private final WeakReference<Context> weakContext;
        private final LoadNotesCallback callback;

        private LoadNotesAsync(Context context, LoadNotesCallback callback) {
            weakContext = new WeakReference<>(context);
            this.callback = callback;
        }

        void execute() {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executor.execute(() -> {
                Context context = weakContext.get();
                if (context != null) {
                    NoteHelper noteHelper = NoteHelper.getInstance(context);
                    noteHelper.open();

                    Cursor notesCursor = noteHelper.queryAll();
                    ArrayList<Note> notes = MappingHelper.mapCursorToArrayList(notesCursor);
                    noteHelper.close();

                    handler.post(() -> callback.postExecute(notes));
                }
            });
        }
    }

    interface LoadNotesCallback {
        void postExecute(ArrayList<Note> notes);
    }
}
