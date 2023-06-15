package com.example.notesappsqlite.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format(
                "CREATE TABLE %s" +
                        " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " %s TEXT NOT NULL," +
                        " %s TEXT NOT NULL," +
                        " %s TEXT NOT NULL)",
                DatabaseContract.TABLE_NAME,
                DatabaseContract.NoteColumns._ID,
                DatabaseContract.NoteColumns.TITLE,
                DatabaseContract.NoteColumns.DESCRIPTION,
                DatabaseContract.NoteColumns.DATE
        );

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + DatabaseContract.TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }
}
