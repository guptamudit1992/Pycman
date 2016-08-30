package com.example.parsler.pycman.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.parsler.pycman.Commons.BuildProperties;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = BuildProperties.DATABASE_VERSION;
    private static final String DATABASE_NAME = BuildProperties.DATABASE_NAME;
    private static final String TABLE_NAME = BuildProperties.TABLE_NAME;
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + BuildProperties.TABLE_NAME + " (" +
            BuildProperties.KEY_PICKUP_ID + " INTEGER PRIMARY KEY UNIQUE," +
            BuildProperties.KEY_IS_ACTIVE + " BOOLEAN," +
            BuildProperties.KEY_IS_READ  + " BOOLEAN," +
            BuildProperties.KEY_PICKUP_IS_COMPLETED + " BOOLEAN," +
            BuildProperties.KEY_DROP_IS_COMPLETED + " BOOLEAN," +
            BuildProperties.KEY_PAYMENT_IS_COMPLETED + " BOOLEAN)";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

