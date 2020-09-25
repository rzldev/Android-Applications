package com.example.benakno.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.benakno.Activities.OrderActivity;
import com.example.benakno.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    CardView cv1, cv2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initializing(view);

        myClick();

        return view;
    }

    private void myClick() {
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("tipe", "ac");
                startActivity(intent);
            }
        });

        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("tipe", "ledeng");
                startActivity(intent);
            }
        });
    }


    private void initializing(View view) {
        cv1 = view.findViewById(R.id.cvServisAC);
        cv2 = view.findViewById(R.id.cvServisLedeng);
    }

}
