package com.example.benakno.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Dataku.db";
    public static final int DATABASE_VERSION = 2;

    public static final String TABLE_USER = "table_user";
    public static final String UID = "_id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "Password";
    public static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " VARCHAR(255) , " + EMAIL + " VARCHAR(255) , " + PASSWORD + " VARCHAR(255))";
    public static final String DROP_TABLE_USER = "DROP TABLE IF EXISTS " + TABLE_USER;

    public static final String TABLE_ORDER = "table_order";     // Table
    public static final String ORDER_UID = "_id";               // ID
    public static final String ORDER_TYPE = "order_type";       // AC or Ledeng
    public static final String ORDER_DATE = "order_date";       // Order date
    public static final String ORDER_STATUS = "order_status";   // Order status
    public static final String MERK = "merk";                   // Product merk
    public static final String QUANTITY = "quantity";           // Product quantity
    public static final String DESCRIPTION = "description";     // PK AC
    public static final String BUILDING = "BUILDING";           // Building
    public static final String ADDRESS = "address";             // Address
    public static final String BY_USER = "by_user";             // User that order the service
    public static final String WORKER = "worker";               // Worker
    public static final String CREATE_TABLE_ORDER = "CREATE TABLE " + TABLE_ORDER + " (" +
            ORDER_UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ORDER_TYPE + " VARCHAR(100), " +
            ORDER_DATE + " VARCHAR(100), " +
            ORDER_STATUS + " VARCHAR(100), " +
            MERK + " VARCHAR(100), " +
            QUANTITY + " VARCHAR(100), " +
            DESCRIPTION + " VARCHAR(100), " +
            BUILDING + " VARCHAR(100), " +
            ADDRESS + " VARCHAR(100), " +
            BY_USER + " VARCHAR(100), " +
            WORKER + " VARHCAR(100))";
    public static final String DROP_TAABLE_ORDER = "DROP TABLE IF EXISTS " + TABLE_ORDER;


    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(CREATE_TABLE_USER);
            sqLiteDatabase.execSQL(CREATE_TABLE_ORDER);
        } catch (Exception e) {

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            sqLiteDatabase.execSQL(DROP_TABLE_USER);
            sqLiteDatabase.execSQL(DROP_TAABLE_ORDER);
            onCreate(sqLiteDatabase);
        } catch (Exception e) {

        }
    }

}
