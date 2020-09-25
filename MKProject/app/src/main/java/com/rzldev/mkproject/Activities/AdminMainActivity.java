package com.rzldev.mkproject.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.rzldev.mkproject.Fragments.AdminHistoryFragment;
import com.rzldev.mkproject.Fragments.AdminHomeFragment;
import com.rzldev.mkproject.Fragments.AdminPofileFragment;
import com.rzldev.mkproject.Fragments.HistoryFragment;
import com.rzldev.mkproject.Fragments.HomeFragment;
import com.rzldev.mkproject.Fragments.ProfileFragment;
import com.rzldev.mkproject.R;

public class AdminMainActivity extends AppCompatActivity {

    String username;
    String saldo;
    String telp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        BottomNavigationView bottomnav = findViewById(R.id.nav_bar_admin);
        bottomnav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_admin,
                new AdminHomeFragment()).commit();

//        Intent intent = getIntent();
//        username = intent.getStringExtra("username");
//        saldo = intent.getStringExtra("saldo");
//        telp = intent.getStringExtra("telp");

        SharedPreferences sharedPreferences = getSharedPreferences("usersaldo", MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");
        saldo = sharedPreferences.getString("saldo", "");
        telp = sharedPreferences.getString("telp", "");

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener(){

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.home_nav_admin:
                            selectedFragment = new AdminHomeFragment();
                            break;
                        case R.id.history_nav_admin:
                            selectedFragment = new AdminHistoryFragment();
                            break;
                        case R.id.profile_nav_admin:
                            selectedFragment = new AdminPofileFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_admin,
                            selectedFragment).commit();

                    return true;
                }
            };
}
