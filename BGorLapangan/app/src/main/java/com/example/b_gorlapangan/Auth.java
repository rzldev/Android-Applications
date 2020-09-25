package com.example.b_gorlapangan;

import android.content.Context;
import android.content.SharedPreferences;

public class Auth {

    private Context context;

    public Auth(Context context) {
        this.context = context;
    }

    public void saveAuth(String id) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Auth", Context.MODE_PRIVATE).edit();
        editor.putString("id", id);
        editor.apply();
    }

    public boolean checkAuth() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Auth", context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "");
        if (id.equals("")) {
            return false;

        } else {
            return true;

        }
    }

    public void clearAuth() {
        SharedPreferences.Editor editor = context.getSharedPreferences("Auth", Context.MODE_PRIVATE).edit();
        editor.putString("id", "");
        editor.apply();
    }
}
