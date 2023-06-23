package com.example.assignment_8_1;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    ImageButton imgBtnAdd;
    SearchView searchView;
    NoteModel note;
    NoteAdapter adapter;
    int position;
    private final ActivityResultLauncher<Intent> resultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getData() != null) {
                            int resultCode = result.getResultCode();
                            Intent data = result.getData();

                            switch (resultCode) {
                                case FormActivity.RESULT_ADD:
                                    note = data.getParcelableExtra(FormActivity.EXTRA_NOTE);
                                    adapter.addNote(note);
                                    Toast.makeText(MainActivity.this, "Student added successfully",
                                            Toast.LENGTH_SHORT).show();
                                    break;
                                case FormActivity.RESULT_UPDATE:
                                    note = data.getParcelableExtra(FormActivity.EXTRA_NOTE);
                                    System.out.println("cakkkkk");
//                                    int pos = data.getIntExtra("EXTRA_POSITION",0);
                                    adapter.updateNote(note, position);
                                    Toast.makeText(MainActivity.this, "Student updated successfully",
                                            Toast.LENGTH_SHORT).show();
                                    break;
                                case FormActivity.RESULT_DELETE:
                                    note = data.getParcelableExtra(FormActivity.EXTRA_NOTE);
                                    adapter.deleteNote(position);
                                    note = null;
                                    Toast.makeText(MainActivity.this, "Student deleted successfully",
                                            Toast.LENGTH_SHORT).show();
                                    break;
                            }

                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        RecyclerView rvNotes = findViewById(R.id.rv_notes);
        rvNotes.setHasFixedSize(true);
        rvNotes.setLayoutManager(new LinearLayoutManager(this));

        adapter = new NoteAdapter(MainActivity.this, DataSource.noteModelArrayList);
        adapter.setClickListener(new NoteAdapter.ClickListener() {
            @Override
            public void onItemClicked(NoteModel noteModel, int pos) {
                position = pos;
//                System.out.println(pos);
                Intent toForm = new Intent(MainActivity.this, FormActivity.class);
                if (noteModel != null) {
                    toForm.putExtra(FormActivity.EXTRA_NOTE, noteModel);
//                    toForm.putExtra("EXTRA_POSITION", pos);
                }
                resultLauncher.launch(toForm);
            }
        });
        rvNotes.setAdapter(adapter);

        imgBtnAdd = findViewById(R.id.img_btn_add);
        imgBtnAdd.setOnClickListener(view -> {
            Intent toForm = new Intent(MainActivity.this, FormActivity.class);

            resultLauncher.launch(toForm);
        });

        new LoadStudentsAsync(this, note -> {
            if (note.size() > 0) {
                adapter.setItem(note);
            } else {
                adapter.setItem(new ArrayList<>());
            }
        }).execute();
    }

    private static class LoadStudentsAsync {

        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadNotesCallBack> weakCallback;

        private LoadStudentsAsync(Context context, LoadNotesCallBack callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        void execute() {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executor.execute(() -> {
                Context context = weakContext.get();
                NoteHelper studentHelper = NoteHelper.getInstance(context);
                studentHelper.open();

                Cursor studentsCursor = studentHelper.queryAll();
                ArrayList<NoteModel> notes = MappingHelper.mapCursorToArrayList(studentsCursor);
                studentHelper.close();

                handler.post(() -> weakCallback.get().postExecute(notes));
            });
        }
    }

    interface LoadNotesCallBack {
        void postExecute(ArrayList<NoteModel> notes);
    }

}