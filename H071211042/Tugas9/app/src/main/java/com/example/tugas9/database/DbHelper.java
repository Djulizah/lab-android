package com.example.tugas9.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DbHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "Note.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_NOTE =
            String.format(
                    "CREATE TABLE %s"
                            + " (%s INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + " %s TEXT NOT NULL,"
                            + " %s TEXT NOT NULL,"
                            + " %s TEXT NOT NULL,"
                            + " %s INTEGER DEFAULT 0)",
                    DbContract.TABLE_NAME,
                    DbContract.NoteColumns._ID,
                    DbContract.NoteColumns.TITLE,
                    DbContract.NoteColumns.DESCRIPTION,
                    DbContract.NoteColumns.CREATED_AT,
                    DbContract.NoteColumns.IS_EDITED
            );

    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DbContract.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}
