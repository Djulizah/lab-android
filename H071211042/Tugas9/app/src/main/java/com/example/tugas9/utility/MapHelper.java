package com.example.tugas9.utility;

import android.database.Cursor;

import com.example.tugas9.database.DbContract;
import com.example.tugas9.models.Note;

import java.util.ArrayList;

public class MapHelper {
    public static ArrayList<Note> mapCursorToArrayList(Cursor cursor) {
        ArrayList<Note> notesList = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DbContract.NoteColumns._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DbContract.NoteColumns.TITLE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(DbContract.NoteColumns.DESCRIPTION));
            String createdAt = cursor.getString(cursor.getColumnIndexOrThrow(DbContract.NoteColumns.CREATED_AT));
            int isEdited = cursor.getInt(cursor.getColumnIndexOrThrow(DbContract.NoteColumns.IS_EDITED));
            notesList.add(new Note(
                    id,
                    title,
                    description,
                    createdAt,
                    isEdited
            ));
        }
        return notesList;
    }
}
