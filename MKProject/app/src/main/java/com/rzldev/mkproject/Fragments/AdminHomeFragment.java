package com.rzldev.mkproject.Fragments;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.rzldev.mkproject.R;

public class AdminHomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_home, container, false);

        BottomNavigationView bottomnav = view.findViewById(R.id.nav_bar_top_admin);
        bottomnav.setOnNavigationItemSelectedListener(navListener);

        getFragmentManager().beginTransaction().replace(R.id.fragment_container2_admin,new AdminPlayingFragment()).commit();

        return view;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener(){

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.playing_nav_admin:
                            selectedFragment = new AdminPlayingFragment();
                            break;
                        case R.id.most_viewed_nav_admin:
                            selectedFragment = new AdminMostViewedFragment();
                            break;
                    }

                    getFragmentManager().beginTransaction().replace(R.id.fragment_container2_admin,
                            selectedFragment).commit();

                    return true;
                }
            };

}
