package com.example.vploaia.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static com.example.vploaia.todoapp.DatabaseHelper.TASKS_TABLE_NAME;

/**
 * Created by vploaia on 2/14/2017.
 */

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase db;

    public DBManager(Context c){
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }



    public void insertTask (String name, String description){

        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.DATABASE_NAME, name);
        contentValue.put(DatabaseHelper.TASKS_COLUMN_DESCRIPTION, description);
        db.insert(TASKS_TABLE_NAME, null, contentValue);

    }

   public Cursor fetch() {
       String[] columns = new String[] {DatabaseHelper.TASKS_COLUMN_ID, DatabaseHelper.TASKS_COLUMN_NAME, DatabaseHelper.TASKS_COLUMN_DESCRIPTION};
       Cursor cursor = db.query(DatabaseHelper.TASKS_TABLE_NAME, columns, null, null, null, null, null);

       if(cursor != null) {
           cursor.moveToFirst();
       }
       return cursor;
   }

    public int update(int id, String name, String description) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.TASKS_COLUMN_NAME, name);
        contentValues.put(DatabaseHelper.TASKS_COLUMN_DESCRIPTION, description);
        int i = db.update(DatabaseHelper.TASKS_TABLE_NAME, contentValues, DatabaseHelper.TASKS_COLUMN_ID + " = " + id, null);
        return i;

    }

    public void delete(int id){
        db.delete(DatabaseHelper.TASKS_TABLE_NAME, DatabaseHelper.TASKS_COLUMN_ID + "=" + id, null);
    }
}
