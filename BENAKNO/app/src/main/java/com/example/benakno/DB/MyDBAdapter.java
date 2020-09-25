package com.example.benakno.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyDBAdapter {
    private MyDBHelper myDBHelper;

    public MyDBAdapter(Context context) {
        myDBHelper = new MyDBHelper(context);
    }

    public long insertData(String Name, String Pass, String Email) {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDBHelper.NAME, Name);
        contentValues.put(myDBHelper.EMAIL, Email);
        contentValues.put(myDBHelper.PASSWORD, Pass);
        long id = db.insert(myDBHelper.TABLE_USER, null, contentValues);
        return id;
    }

    public String getData() {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        String[] columns = {myDBHelper.UID, myDBHelper.NAME, myDBHelper.PASSWORD, myDBHelper.EMAIL};
        Cursor cursor = db.query(myDBHelper.TABLE_USER, columns, null, null, null, null, null);
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

    public long insertOrder(String type, String date, String status, String merk, String quantity,
                            String description, String building, String address, String user) {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDBHelper.ORDER_TYPE, type);
        contentValues.put(myDBHelper.ORDER_DATE, date);
        contentValues.put(myDBHelper.ORDER_STATUS, status);
        contentValues.put(myDBHelper.MERK, merk);
        contentValues.put(myDBHelper.QUANTITY, quantity);
        contentValues.put(myDBHelper.DESCRIPTION, description);
        contentValues.put(myDBHelper.BUILDING, building);
        contentValues.put(myDBHelper.ADDRESS, address);
        contentValues.put(myDBHelper.BY_USER, user);
        contentValues.put(myDBHelper.WORKER, "Haris Sholeh");
        long id = db.insert(myDBHelper.TABLE_ORDER, null, contentValues);

        return id;

    }

    public String getOrder(String user) {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        String[] columns = {
                myDBHelper.ORDER_UID,
                myDBHelper.ORDER_TYPE,
                myDBHelper.ORDER_DATE,
                myDBHelper.ORDER_STATUS,
                myDBHelper.MERK,
                myDBHelper.QUANTITY,
                myDBHelper.DESCRIPTION,
                myDBHelper.BUILDING,
                myDBHelper.ADDRESS,
                myDBHelper.BY_USER,
                myDBHelper.WORKER,
        };
        String[] args = {
                user
        };
        Cursor cursor = db.query(myDBHelper.TABLE_ORDER, columns, myDBHelper.BY_USER + " = ?", args, null, null, null);
        StringBuffer buffer = new StringBuffer();

        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDBHelper.ORDER_UID));
            String type = cursor.getString(cursor.getColumnIndex(myDBHelper.ORDER_TYPE));
            String date = cursor.getString(cursor.getColumnIndex(myDBHelper.ORDER_DATE));
            String status = cursor.getString(cursor.getColumnIndex(myDBHelper.ORDER_STATUS));
            String merk = cursor.getString(cursor.getColumnIndex(myDBHelper.MERK));
            String quantity = cursor.getString(cursor.getColumnIndex(myDBHelper.QUANTITY));
            String description = cursor.getString(cursor.getColumnIndex(myDBHelper.DESCRIPTION));
            String building = cursor.getString(cursor.getColumnIndex(myDBHelper.BUILDING));
            String address = cursor.getString(cursor.getColumnIndex(myDBHelper.ADDRESS));
            String byUser = cursor.getString(cursor.getColumnIndex(myDBHelper.BY_USER));
            String worker = cursor.getString(cursor.getColumnIndex(myDBHelper.WORKER));
            buffer.append(cid + " - " + type + " - " + date + " - " + status + " - " + merk + " - " + quantity + " - " +
                    description + " - " + building + " - " + address + " - " + byUser + " - " + worker + "\n");
        }

        return buffer.toString();
    }

    public String getSelectedOrder(String user, String id) {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        String[] columns = {
                myDBHelper.ORDER_UID,
                myDBHelper.ORDER_TYPE,
                myDBHelper.ORDER_DATE,
                myDBHelper.ORDER_STATUS,
                myDBHelper.MERK,
                myDBHelper.QUANTITY,
                myDBHelper.DESCRIPTION,
                myDBHelper.BUILDING,
                myDBHelper.ADDRESS,
                myDBHelper.BY_USER,
                myDBHelper.WORKER,
        };
        String[] args = {
                user, id
        };
        Cursor cursor = db.query(myDBHelper.TABLE_ORDER, columns,
                myDBHelper.BY_USER + " = ? AND " + myDBHelper.ORDER_UID + " = ?", args,
                null, null, null);
        StringBuffer buffer = new StringBuffer();

        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDBHelper.ORDER_UID));
            String type = cursor.getString(cursor.getColumnIndex(myDBHelper.ORDER_TYPE));
            String date = cursor.getString(cursor.getColumnIndex(myDBHelper.ORDER_DATE));
            String status = cursor.getString(cursor.getColumnIndex(myDBHelper.ORDER_STATUS));
            String merk = cursor.getString(cursor.getColumnIndex(myDBHelper.MERK));
            String quantity = cursor.getString(cursor.getColumnIndex(myDBHelper.QUANTITY));
            String description = cursor.getString(cursor.getColumnIndex(myDBHelper.DESCRIPTION));
            String building = cursor.getString(cursor.getColumnIndex(myDBHelper.BUILDING));
            String address = cursor.getString(cursor.getColumnIndex(myDBHelper.ADDRESS));
            String byUser = cursor.getString(cursor.getColumnIndex(myDBHelper.BY_USER));
            String worker = cursor.getString(cursor.getColumnIndex(myDBHelper.WORKER));
            buffer.append(cid + " - " + type + " - " + date + " - " + status + " - " + merk + " - " + quantity + " - " +
                    description + " - " + building + " - " + address + " - " + byUser + " - " + worker);
        }

        return buffer.toString();
    }

    public int updateOrder(String user, String id) {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDBHelper.ORDER_STATUS, "finished");
        String[] whereArgs = {user, id};
        int count = db.update(myDBHelper.TABLE_ORDER,
                contentValues,
                myDBHelper.BY_USER + " = ? AND " + myDBHelper.ORDER_UID + " = ?",
                whereArgs);

        return count;

    }

}
