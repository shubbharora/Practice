package com.example.shubbh.practice.database;

/**
 * Created by Shubbh on 19/10/15.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String productname, String gst, String ppu, String qty, String total) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.COL_22, productname);
        contentValue.put(DatabaseHelper.COL_23, gst);
        contentValue.put(DatabaseHelper.COL_24, ppu);
        contentValue.put(DatabaseHelper.COL_25, qty);
        contentValue.put(DatabaseHelper.COL_26, total);

        database.insert(DatabaseHelper.TABLE_NAME2, null, contentValue);
    }

    public Cursor fetch() {
        String sortOrder =
                DatabaseHelper.COL_22 + " ASC"; //DESC
        String[] columns = new String[]{DatabaseHelper.COL_22, DatabaseHelper.COL_23,DatabaseHelper.COL_24,
                DatabaseHelper.COL_25, DatabaseHelper.COL_26};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME2, columns, null, null, null, null, sortOrder);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(String productname, String gst, String ppu, String qty) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_22, productname);
        contentValues.put(DatabaseHelper.COL_23, gst);
        contentValues.put(DatabaseHelper.COL_24, ppu);
        contentValues.put(DatabaseHelper.COL_25, qty);

        int i = database.update(DatabaseHelper.TABLE_NAME2, contentValues, DatabaseHelper.COL_22 + "=?",
                new String[]{String.valueOf(productname)});
        return i;
    }

    public void delete(long itemname) {

        String selection = DatabaseHelper.COL_22 + "=?";

        String[] selectionArgs = {String.valueOf(itemname)};
        database.delete(DatabaseHelper.TABLE_NAME2, selection, selectionArgs);
    }
}