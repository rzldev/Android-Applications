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

    public long insertData(String namaBarang, int hargaBarang) {
        SQLiteDatabase sqLiteDatabase = myDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDBHelper.NAMA_BARANG, namaBarang);
        contentValues.put(myDBHelper.HARGA_BARANG, hargaBarang);
        long id = sqLiteDatabase.insert(myDBHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public String getData() {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        String[] columns = {myDBHelper.UID, myDBHelper.NAMA_BARANG, myDBHelper.HARGA_BARANG};
        Cursor cursor = db.query(myDBHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();

        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDBHelper.NAMA_BARANG));
            String name = cursor.getString(cursor.getColumnIndex(myDBHelper.NAMA_BARANG));
            String password = cursor.getString(cursor.getColumnIndex(myDBHelper.NAMA_BARANG));
            buffer.append(cid + " " + name + " " + password + "\n");
        }

        return buffer.toString();
    }

    public int delete (String namaBarang) {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        String[] whereArgs = {namaBarang};
        int count = db.delete(myDBHelper.TABLE_NAME, myDBHelper.NAMA_BARANG + " = ?", whereArgs);

        return count;
    }

    public int updateName(String barangLama, String namaBarang, int hargaBarang) {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDBHelper.NAMA_BARANG, barangLama);
        contentValues.put(myDBHelper.HARGA_BARANG, hargaBarang);
        String[] whereArgs = {barangLama};
        int count = db.update(myDBHelper.TABLE_NAME, contentValues, myDBHelper.NAMA_BARANG + " = ?", whereArgs);

        return count;
    }


}
