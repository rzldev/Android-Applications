package com.example.greendaoapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DbHandler {
    public static DaoSession getInstance(Context mContext) {
        DaoMaster.DevOpenHelper dbHelper = new DaoMaster.DevOpenHelper(mContext,"db_penjualan",null);
        SQLiteDatabase db_penjualan = dbHelper.getWritableDatabase();

        DaoMaster dbMaster = new DaoMaster(db_penjualan);
        return dbMaster.newSession();
    }
}
