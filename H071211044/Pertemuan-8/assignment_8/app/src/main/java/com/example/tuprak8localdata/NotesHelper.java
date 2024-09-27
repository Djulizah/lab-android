package com.example.tuprak8localdata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class NotesHelper {

    private static final String DATABASE_TABLE = DatabaseContract.TABLE_NAME;
    private static DatabaseHelper databaseHelper;
    private static SQLiteDatabase database;
    private static volatile NotesHelper INSTANCE;

    public NotesHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static NotesHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NotesHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
        if (database.isOpen()) {
            database.close();
        }
    }

    public Cursor getAllNotes() {
        ArrayList<Notes> notesList = new ArrayList<>();
        Cursor cursor = database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                DatabaseContract.NotesColumns._ID + " DESC"
        );
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            Notes notes = getNotesFromCursor(cursor);
//            notesList.add(notes);
//            cursor.moveToNext();
//        }
//        cursor.close();
        return cursor;
    }

    private Notes getNotesFromCursor(Cursor cursor) {
        Notes notes = new Notes();
        notes.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.NotesColumns._ID)));
        notes.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NotesColumns.TITLE)));
        notes.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NotesColumns.DESCRIPTION)));
        notes.setCreatedAt(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NotesColumns.CREATEDAT)));
        return notes;
    }

    public Cursor queryById(String id) {
        return database.query(
                DATABASE_TABLE,
                null,
                DatabaseContract.NotesColumns._ID + " = ?",
                new String[]{id},
                null,
                null,
                null,
                null
        );
    }
    public static long insert(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }

    public static int update(String id, ContentValues values) {
        return database.update(DATABASE_TABLE, values, DatabaseContract.NotesColumns._ID
                + " = ?", new String[]{id});
    }

    public int deleteById(String id) {
        return database.delete(DATABASE_TABLE, DatabaseContract.NotesColumns._ID + " = "
                + id, null);
    }
    public ArrayList<Notes> searchNotes(String searchText) {
        ArrayList<Notes> searchResults = new ArrayList<>();

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DatabaseContract.TABLE_NAME + " WHERE " + DatabaseContract.NotesColumns.TITLE + " LIKE '" + searchText + "%'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Notes notes = getNotesFromCursor(cursor);
                searchResults.add(notes);
            }
        }

        if (cursor != null) {
            cursor.close();
        }

        return searchResults;
    }
}
