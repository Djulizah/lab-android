package com.example.tugas9.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteHelper {
    private static final String DATABASE_TABLE = DbContract.TABLE_NAME;

    private static DbHelper dbHelper;

    private static SQLiteDatabase database;

    private static volatile NoteHelper INSTANCE;

    private NoteHelper(Context context) {
        dbHelper = new DbHelper(context);
    }

    public static NoteHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NoteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
        if (database.isOpen()) {
            database.close();
        }
    }

    public Cursor queryAll() {
        return database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                DbContract.NoteColumns._ID + " ASC"
        );
    }


    public Cursor querySearch(String query) {
        return database.query(
                DATABASE_TABLE,
                null,
                DbContract.NoteColumns.TITLE + " LIKE ?",
                new String[]{"%" + query + "%"},
                null,
                null,
                DbContract.NoteColumns._ID + " ASC"
        );
    }

    public Cursor queryById(String id) {
        return database.query(
                DATABASE_TABLE,
                null,
                DbContract.NoteColumns._ID + " = ?",
                new String[]{id},
                null,
                null,
                null,
                null
        );
    }

    public long insert(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }

    public int update(String id, ContentValues values) {
        return database.update(DATABASE_TABLE, values, DbContract.NoteColumns._ID + " = ?", new String[]{id});
    }

    public int delete(String id) {
        return database.delete(DATABASE_TABLE, DbContract.NoteColumns._ID + " = ?", new String[]{id});
    }
}
