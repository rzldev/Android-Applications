package com.example.jamku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.BottomNav)
    BottomNavigationView bottomnav;

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        toolbar = getSupportActionBar();

        toolbar.setTitle("Time");
        loadFragment(new TimeFragment());

        bottomnav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment;

            switch (menuItem.getItemId()) {
                case R.id.time_menu :
                    toolbar.setTitle(menuItem.getTitle());
                    fragment = new TimeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.timer_menu :
                    toolbar.setTitle(menuItem.getTitle());
                    fragment = new TimerFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.stopwatch_menu :
                    toolbar.setTitle(menuItem.getTitle());
                    fragment = new StopwatchFragment();
                    loadFragment(fragment);
                    return true;
            }

            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
