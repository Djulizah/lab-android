package com.example.tugas8;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_NAME = "task";
    public static final class TaskColumns implements BaseColumns {
        public static String TITLE = "title";
        public static String DESC = "desc";
    }
}
