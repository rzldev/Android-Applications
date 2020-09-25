package com.rzldev.mkproject.Fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.rzldev.mkproject.R;

@SuppressLint("ValidFragment")
public class HomeFragment extends Fragment {

    String username;

    @SuppressLint("ValidFragment")
    public HomeFragment(String username) {
        // Required empty public constructor
        this.username = username;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        BottomNavigationView bottomnav = view.findViewById(R.id.nav_bar_top);
        bottomnav.setOnNavigationItemSelectedListener(navListener);

        getFragmentManager().beginTransaction().replace(R.id.fragment_container2,new PlayingFragment()).commit();

        return view;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener(){

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.playing_nav:
                            selectedFragment = new PlayingFragment();
                            break;
                        case R.id.most_viewed_nav:
                            selectedFragment = new MostViewedFragment();
                            break;
                    }

                    getFragmentManager().beginTransaction().replace(R.id.fragment_container2,
                            selectedFragment).commit();

                    return true;
                }
            };

}
