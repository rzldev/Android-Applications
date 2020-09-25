package com.example.myaccountmanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.method.Touch;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Dataku.db";
    public static final String TABLE_NAME = "table_user";
    public static final int DATABASE_VERSION = 2;
    public static final String UID = "_id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "Password";
    public static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " VARCHAR(255) , " + EMAIL + " VARCHAR(255) , " + PASSWORD + " VARCHAR(255))";
    public static final String DROP_TABLE_USER = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private Context context;

    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(CREATE_TABLE_USER);
        } catch (Exception e) {

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            sqLiteDatabase.execSQL(DROP_TABLE_USER);
            onCreate(sqLiteDatabase);
        } catch (Exception e) {

        }

    }
}
