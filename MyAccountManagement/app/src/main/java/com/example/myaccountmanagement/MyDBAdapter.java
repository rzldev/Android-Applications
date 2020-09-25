package com.example.myaccountmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MyDBAdapter {
    MyDBHelper myDBHelper;

    public MyDBAdapter(Context context) {
        myDBHelper = new MyDBHelper(context);
    }

    public long insertData(String Name, String Email, String Pass) {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDBHelper.NAME, Name);
        contentValues.put(myDBHelper.EMAIL, Email);
        contentValues.put(myDBHelper.PASSWORD, Pass);
        long id = db.insert(myDBHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public int loginSession(String email, String pass) {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        String[] columns = {myDBHelper.UID, myDBHelper.NAME, myDBHelper.EMAIL, myDBHelper.PASSWORD};
        String[] args = {email, pass};
        Cursor cursor = db.query(myDBHelper.TABLE_NAME, columns, myDBHelper.EMAIL + " = ? AND " + myDBHelper.PASSWORD + " = ?", args, null, null, null);
        int count = cursor.getCount();

        return count;
    }

    public String getData() {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        String[] columns = {myDBHelper.UID, myDBHelper.NAME, myDBHelper.EMAIL, myDBHelper.PASSWORD};
        Cursor cursor = db.query(myDBHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();

        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDBHelper.UID));
            String name = cursor.getString(cursor.getColumnIndex(myDBHelper.NAME));
            String email = cursor.getString(cursor.getColumnIndex(myDBHelper.EMAIL));
            String password = cursor.getString(cursor.getColumnIndex(myDBHelper.PASSWORD));
            buffer.append(cid + " " + name + " " + email + " " + password + "\n");
        }

        return buffer.toString();
    }

    public int updateData(String oldName, String oldEmail, String newName, String newEmail, String newPass) {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDBHelper.NAME, newName);
        contentValues.put(myDBHelper.EMAIL, newEmail);
        contentValues.put(myDBHelper.PASSWORD, newPass);
        String[] whereArgs = {oldName, oldEmail};
        int count = db.update(myDBHelper.TABLE_NAME,
                contentValues,
                myDBHelper.NAME + " = ? AND " + myDBHelper.EMAIL + " = ?",
                whereArgs);

        return count;
    }

    public int deleteData (String name, String email) {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        String[] whereArgs = {name, email};
        int count = db.delete(myDBHelper.TABLE_NAME,
                myDBHelper.NAME + " = ? AND " + myDBHelper.EMAIL + " = ?",
                whereArgs);

        return count;
    }


}
