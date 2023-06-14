package com.example.tugas8;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Task.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_TASK =
            String.format(
                    "CREATE TABLE %s"
                            + " (%s INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + " %s TEXT NO NULL,"
                            + " %s TEXT NO NULL)",
                    DatabaseContract.TABLE_NAME,
                    DatabaseContract.TaskColumns._ID,
                    DatabaseContract.TaskColumns.TITLE,
                    DatabaseContract.TaskColumns.DESC
            );

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_TASK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addTask(String title, String desc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DatabaseContract.TaskColumns.TITLE, title);
        cv.put(DatabaseContract.TaskColumns.DESC, desc);
        db.insert(DatabaseContract.TABLE_NAME, null, cv);
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + DatabaseContract.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateTask(String id, String title, String desc){
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseContract.TaskColumns.TITLE, title);
            contentValues.put(DatabaseContract.TaskColumns.DESC, desc);
            SQLiteDatabase db = this.getWritableDatabase();
            db.update(DatabaseContract.TABLE_NAME, contentValues, DatabaseContract.TaskColumns._ID + " = ?", new String[]{id});
        }catch (Exception e){
            System.out.println(e.toString());
        }
//        ContentValues cv = new ContentValues();
//
//        cv.put(DatabaseContract.TaskColumns.TITLE, title);
//        cv.put(DatabaseContract.TaskColumns.DESC, desc);
//
//        db.update(DatabaseContract.TABLE_NAME, cv, DatabaseContract.TaskColumns._ID + " = ?", new String[]{id});
    }

    void deleteTask(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DatabaseContract.TABLE_NAME, DatabaseContract.TaskColumns._ID + " = ?", new String[]{id});
    }

    Cursor searchData(String data){
        String query = "SELECT * FROM " + DatabaseContract.TABLE_NAME + " WHERE " + DatabaseContract.TaskColumns.TITLE
                + " LIKE ?";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, new String[]{data + "%"});
        }
        return cursor;
    }

}
