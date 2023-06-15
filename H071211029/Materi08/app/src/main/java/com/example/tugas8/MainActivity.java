package com.example.tugas8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    FloatingActionButton btn_add;
    DatabaseHelper databaseHelper;
    ArrayList<Task> tasks;
    AdapterHome adapter;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv_home);
        btn_add = findViewById(R.id.btn_add);
        searchView = findViewById(R.id.searchView);

        btn_add.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        });

        databaseHelper = new DatabaseHelper(MainActivity.this);

        tasks = new ArrayList<>();



        adapter = new AdapterHome(MainActivity.this, tasks);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        storeData();
        if (tasks.size() > 0) {
            searchView.setVisibility(View.VISIBLE);
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchData(newText);
                System.out.println(newText);
                return true;
            }
        });

    }
    void storeData(){
        Cursor cursor = databaseHelper.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            tasks.clear();
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.TaskColumns._ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TaskColumns.TITLE));
                String desc = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TaskColumns.DESC));
                tasks.add(new Task(id, title, desc));
            }
//            adapter.addAll(tasks);
            adapter = new AdapterHome(MainActivity.this, tasks);
            rv.setAdapter(adapter);
        }
    }

    void searchData(String q){
        Cursor cursor = databaseHelper.searchData(q.trim());
        System.out.println(cursor.getCount());
        if (q.isEmpty()){
            storeData();
            return;
        }
        if (cursor.getCount() == 0) {
            tasks.clear();
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            tasks.clear();
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.TaskColumns._ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TaskColumns.TITLE));
                String desc = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TaskColumns.DESC));
                tasks.add(new Task(id, title, desc));
            }
//            System.out.println(tasks.size());
//            adapter.addAll(tasks);

        }
        adapter = new AdapterHome(MainActivity.this, tasks);
        rv.setAdapter(adapter);
    }
}