package com.example.notesappsqlite.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.notesappsqlite.data.db.DatabaseContract;
import com.example.notesappsqlite.data.db.DatabaseHelper;

public class NoteHelper {
    private static final String DATABASE_TABLE = DatabaseContract.TABLE_NAME;

    private static DatabaseHelper databaseHelper;
    private static SQLiteDatabase database;
    private static volatile NoteHelper INSTANCE;

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

    private NoteHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    // Open database
    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    // Close database
    public void close() {
        databaseHelper.close();
        if (database.isOpen()) database.close();
    }

    // Create data
    public long create(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }
    public Cursor readAll() {
        return database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                DatabaseContract.NoteColumns._ID + " ASC"
        );
    }
    public int update(String id, ContentValues values) {
        return database.update(
                DATABASE_TABLE,
                values,
                DatabaseContract.NoteColumns._ID + " = ?",
                new String[]{id}
        );
    }

    public int delete(String id) {
        return database.delete(
                DATABASE_TABLE,
                DatabaseContract.NoteColumns._ID + " = " + id,
                null
        );
    }

    public Cursor searchByTitle(String title) {
        String query = String.format(
                "SELECT * FROM %s WHERE %s LIKE ?",
                DATABASE_TABLE,
                DatabaseContract.NoteColumns.TITLE
        );

        return database.rawQuery(query, new String[]{title + "%"});
    }
}
