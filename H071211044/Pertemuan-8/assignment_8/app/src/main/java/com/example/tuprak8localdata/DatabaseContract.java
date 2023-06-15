package com.example.tuprak8localdata;

import android.provider.BaseColumns;

public class DatabaseContract {

    public static String TABLE_NAME = "catatan";
    public static final class NotesColumns implements BaseColumns {

        public static String TITLE = "title";
        public static String CREATEDAT = "createdAt";
        public static String DESCRIPTION = "description";
    }
}
