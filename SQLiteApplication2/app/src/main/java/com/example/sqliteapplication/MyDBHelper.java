package com.example.sqliteapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.nio.charset.Charset;
import java.util.Random;

public class MyDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Barang.db";
    public static final String TABLE_NAME = "Barang";
    public static final int DATABASE_VERSION = 2;
    public static final String UID = "_id";
    public static final String NAMA_BARANG = "NamaBarang";
    public static final String HARGA_BARANG = "HargaBarang";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + UID + " VARCHAR(7) PRIMARY KEY AUTOINCREMENT, "
            + NAMA_BARANG + " VARCHAR(100), " + HARGA_BARANG + " INTEGER))";
    public static final String DROP_TABLE = "DROP TABLE IF EXIST " +TABLE_NAME;

    private Context context;

    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(CREATE_TABLE);
        } catch (Exception e) {
            Toast.makeText(context, String.valueOf(e), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
        } catch (Exception e) {
            Toast.makeText(context, String.valueOf(e), Toast.LENGTH_SHORT).show();
        }
    }
}
