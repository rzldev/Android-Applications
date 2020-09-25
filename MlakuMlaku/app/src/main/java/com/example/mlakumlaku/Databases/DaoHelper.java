package com.example.mlakumlaku.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.mlakumlaku.Classes.DaoMaster;
import com.example.mlakumlaku.Classes.DaoSession;

public class DaoHelper {
    public static DaoSession getInstance(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "db_user", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }
}
