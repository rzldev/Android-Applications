package com.example.retrofitapplication.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.retrofitapplication.Classes.DaoMaster;
import com.example.retrofitapplication.Classes.DaoSession;

public class DAODbHelper {
    public static DaoSession getInstance(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "db_mahasiswa", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }
}
