package com.example.mlakumlaku.Activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mlakumlaku.Fragments.EventFragment;
import com.example.mlakumlaku.Fragments.HomeFragment;
import com.example.mlakumlaku.Fragments.ProfileFragment;
import com.example.mlakumlaku.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initializing();
        setBottomNav();
    }

    private void initializing() {
        bottomNavigationView = findViewById(R.id.mainBottomNav);
    }

    private void setBottomNav() {
        loadFragment(new HomeFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment fragment;

                    switch (menuItem.getItemId()) {
                        case R.id.bnHome :
                            fragment = new HomeFragment();
                            loadFragment(fragment);
                            return true;
                        case R.id.bnEvent :
                            fragment = new EventFragment();
                            loadFragment(fragment);
                            return true;
                        case R.id.bnProfile :
                            fragment = new ProfileFragment();
                            loadFragment(fragment);
                            return true;
                    }

                    return false;
                }
            };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
