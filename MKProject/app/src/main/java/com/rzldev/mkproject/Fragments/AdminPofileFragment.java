package com.rzldev.mkproject.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rzldev.mkproject.R;

import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminPofileFragment extends Fragment {

    public AdminPofileFragment() {
        // Required empty public constructor
    }

    String id_username;
    String saldo;
    String telp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_pofile, container, false);

        ButterKnife.bind(this,view);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("usersaldo", Context.MODE_PRIVATE);
        id_username = sharedPreferences.getString("username", "");
        saldo = sharedPreferences.getString("saldo", "");
        telp = sharedPreferences.getString("telp", telp);

        TextView namaprofileadmin = (TextView) view.findViewById(R.id.namaprofile_admin);
        namaprofileadmin.setText(id_username);
        TextView tlpprofileadmin = (TextView) view.findViewById(R.id.tlpprofile_admin);
        tlpprofileadmin.setText(telp);
        TextView saldoprofileadmin = (TextView) view.findViewById(R.id.saldoprofile_admin);
        saldoprofileadmin.setText(saldo);

        return view;
    }

}
