package com.example.b_gorlapangan.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.b_gorlapangan.Fragments.ChatFragment;
import com.example.b_gorlapangan.Fragments.LapanganFragment;
import com.example.b_gorlapangan.Fragments.OrderFragment;
import com.example.b_gorlapangan.Fragments.ProfileFragment;
import com.example.b_gorlapangan.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializing();
        myBottomNav();
    }

    private void myBottomNav() {
        loadFragment(new LapanganFragment());
        bottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment fragment;

                    switch (menuItem.getItemId()) {
                        case R.id.bnLapangan:
                            fragment = new LapanganFragment();
                            loadFragment(fragment);
                            return true;

                        case R.id.bnChat:
                            fragment = new ChatFragment();
                            loadFragment(fragment);
                            return true;

                        case R.id.bnOrder:
                            fragment = new OrderFragment();
                            loadFragment(fragment);
                            return true;

                        case R.id.bnProfile:
                            fragment = new ProfileFragment();
                            loadFragment(fragment);
                            return true;

                    }

                    return false;
                }
            };

    private void initializing() {
        frameLayout = findViewById(R.id.mContainer);
        bottomNav = findViewById(R.id.mNavbar);
    }
}
