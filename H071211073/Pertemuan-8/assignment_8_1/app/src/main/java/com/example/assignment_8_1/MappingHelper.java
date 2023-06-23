package com.example.assignment_8_1;

import android.database.Cursor;

import java.util.ArrayList;

public class MappingHelper {
    public static ArrayList<NoteModel> mapCursorToArrayList(Cursor cursor) {
        ArrayList<NoteModel> noteModelArrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.TITLE));
            String createdAt = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.CREATED_AT));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.DESCRIPTION));
            noteModelArrayList.add(new NoteModel(id, title, createdAt, description));
        }
        return noteModelArrayList;
    }
}
