package com.example.sqliteapplication;

import android.content.Context;
import android.widget.Toast;

public class Info {

    public static void Show(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
