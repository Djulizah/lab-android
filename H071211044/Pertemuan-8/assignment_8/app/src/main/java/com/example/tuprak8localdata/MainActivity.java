package com.example.tuprak8localdata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    AdapterNotes adapterNotes;
    private SearchView svSearch;
    private TextView tv_first;
    private ProgressBar progressBar;
    ExecutorService executor;
    Handler handler;
    private ArrayList<Notes> note;
    private NotesHelper notesHelper;
    private CardView cvAddd;
    Notes ininotes;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv_notes);
        cvAddd = findViewById(R.id.cv_add);
        tv_first = findViewById(R.id.tv_first);
        svSearch = findViewById(R.id.sv_search);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        svSearch.setFocusable(true);
        svSearch.setIconified(false);
        svSearch.requestFocus();
        adapterNotes = new AdapterNotes();

        notesHelper = NotesHelper.getInstance(MainActivity.this);
        notesHelper.open();

        rv.setAdapter(adapterNotes);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        cvAddd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        if (rv != null) {
            tv_first.setVisibility(View.GONE);
            cvAddd.setVisibility(View.VISIBLE);
        }
        new LoadStudentsAsync(this, notess -> {
            if (notess.size() > 0) {
                note = notess;
            } else {
                note = null;
            }
            if (note != null) {
                showCurrentUser(note);
            } else {
                showCurrentUser(new ArrayList<>());
                Toast.makeText(this, "kosong?", Toast.LENGTH_SHORT).show();
            }

        }).execute();

        executor = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());

        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


    private void showCurrentUser(ArrayList<Notes> note) {
        adapterNotes.setData(note);

        if (note.size() > 0) {
            tv_first.setVisibility(View.GONE);
        } else {
            tv_first.setVisibility(View.VISIBLE);
        }
    }

private static class LoadStudentsAsync {
    private final WeakReference<Context> weakContext;
    private final WeakReference<LoadStudentsCallback> weakCallback;
    LoadStudentsAsync(Context context, LoadStudentsCallback callback) {
        weakContext = new WeakReference<>(context);
        weakCallback = new WeakReference<>(callback);
    }
    void execute() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            Context context = weakContext.get();
            NotesHelper notesHelper = NotesHelper.getInstance(context);
            notesHelper.open();
            ArrayList<Notes> notesList = MappingHelper.mapCursorToArrayList(notesHelper.getAllNotes());
            notesHelper.close();
            handler.post(() -> weakCallback.get().postExecute(notesList));
        });
    }
}
interface LoadStudentsCallback {
    void postExecute(ArrayList<Notes> notess);
}

    private void searchData(String searchText) {
        if (!TextUtils.isEmpty(searchText)) {
            progressBar.setVisibility(View.VISIBLE);

            executor.execute(() -> {
                ArrayList<Notes> searchResults = notesHelper.searchNotes(searchText);

                handler.post(() -> {
                    adapterNotes.setData(searchResults);
                    progressBar.setVisibility(View.GONE);
                });
            });
        } else {
            // Jika teks pencarian kosong, tampilkan semua data
            showCurrentUser(note);
        }
    }
}

