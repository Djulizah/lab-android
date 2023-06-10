package com.h071211038.localdatapersistence.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "Note.db";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_STUDENT =
            String.format(
                    "CREATE TABLE %s"
                            + " (%s INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + " %s TEXT NOT NULL,"
                            + " %s TEXT NOT NULL,"
                            + " %s DATETIME DEFAULT CURRENT_TIMESTAMP,"
                            + " %s DATETIME DEFAULT CURRENT_TIMESTAMP)",
                    DatabaseContract.TABLE_NAME,
                    DatabaseContract.NoteColumns._ID,
                    DatabaseContract.NoteColumns.TITLE,
                    DatabaseContract.NoteColumns.DESCRIPTION,
                    DatabaseContract.NoteColumns.CREATED_AT,
                    DatabaseContract.NoteColumns.EDITED_AT
            );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }
}
