package com.rzldev.mkproject.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.rzldev.mkproject.Fragments.HistoryFragment;
import com.rzldev.mkproject.Fragments.HomeFragment;
import com.rzldev.mkproject.Fragments.ProfileFragment;
import com.rzldev.mkproject.R;

public class MainActivity extends AppCompatActivity {

    String username;
    String saldo;
    String telp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomnav = findViewById(R.id.nav_bar);
        bottomnav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment(username)).commit();


        SharedPreferences sharedPreferences = getSharedPreferences("usersaldo", MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");
        saldo = sharedPreferences.getString("saldo", "");
        telp = sharedPreferences.getString("telp", "");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.home_nav:
                            selectedFragment = new HomeFragment(username);
                            break;
                        case R.id.history_nav:
                            selectedFragment = new HistoryFragment();
                            break;
                        case R.id.profile_nav:
                            selectedFragment = new ProfileFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    public void usersaldo(View view){
    }
}