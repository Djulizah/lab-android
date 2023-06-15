package com.example.notesappsqlite.data.db;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static final String TABLE_NAME = "notes";
    public static final class NoteColumns implements BaseColumns {
        public static final String TITLE = "title";

        public static final String DESCRIPTION = "description";

        public static final String DATE = "date";
    }
}
