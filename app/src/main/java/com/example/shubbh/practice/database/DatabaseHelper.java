package com.example.shubbh.practice.database;

/**
 * Created by Shubbh on 19/10/15.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME2 = "Invoice";

    public static final String COL_21 = "ID3";
    public static final String COL_22 = "Product_Name";
    public static final String COL_23 = "Gst";
    public static final String COL_24 = "Price_Per_Unit";
    public static final String COL_25 = "Quantity";
    public static final String COL_26 = "Total";

    // Database Information
    static final String DB_NAME = "JOURNALDEV_COUNTRIES.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME2 + " (ID3 INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Product_Name TEXT NOT NULL UNIQUE, Gst INTEGER NOT NULL, Price_Per_Unit INTEGER NOT NULL, " +
            "Quantity INTEGER NOT NULL, Total INTEGER NOT NULL)";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }
}
