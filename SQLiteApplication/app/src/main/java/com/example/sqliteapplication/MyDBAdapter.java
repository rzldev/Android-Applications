package com.example.sqliteapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyDBAdapter {
    private MyDBHelper myDBHelper;

    public MyDBAdapter(Context context) {
        myDBHelper = new MyDBHelper(context);
    }

    public long insertData(String Name, String Pass) {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDBHelper.NAME, Name);
        contentValues.put(myDBHelper.PASSWORD, Pass);
        long id = db.insert(myDBHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public String getData() {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        String[] columns = {myDBHelper.UID, myDBHelper.NAME, myDBHelper.PASSWORD};
        Cursor cursor = db.query(myDBHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();

        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDBHelper.NAME));
            String name = cursor.getString(cursor.getColumnIndex(myDBHelper.NAME));
            String password = cursor.getString(cursor.getColumnIndex(myDBHelper.NAME));
            buffer.append(cid + " " + name + " " + password + "\n");
        }

        return buffer.toString();
    }

    public int delete (String uname) {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        String[] whereArgs = {uname};
        int count = db.delete(myDBHelper.TABLE_NAME, myDBHelper.NAME + " = ?", whereArgs);

        return count;
    }

    public int updateName(String oldName, String newName) {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDBHelper.NAME, oldName);
        String[] whereArgs = {oldName};
        int count = db.update(myDBHelper.TABLE_NAME, contentValues, myDBHelper.NAME + " = ?", whereArgs);

        return count;
    }
}
