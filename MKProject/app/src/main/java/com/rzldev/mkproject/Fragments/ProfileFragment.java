package com.rzldev.mkproject.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rzldev.mkproject.API.ApiPackage;
import com.rzldev.mkproject.Activities.FilmActivity;
import com.rzldev.mkproject.Activities.PembelianActivity;
import com.rzldev.mkproject.Activities.TopUpActivity;
import com.rzldev.mkproject.Adapter.HistoryAdapter;
import com.rzldev.mkproject.R;
import com.rzldev.mkproject.Responds.HistoryRespond;
import com.rzldev.mkproject.Responds.ProfileRespond;
import com.rzldev.mkproject.Results.HistoryResult;
import com.rzldev.mkproject.Results.ProfileResult;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProfileFragment extends Fragment {

    public ProfileFragment() {

    }

    String id_username;
    String saldo;
    String telp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ButterKnife.bind(this,view);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("usersaldo", Context.MODE_PRIVATE);
        id_username = sharedPreferences.getString("username", "");
        saldo = sharedPreferences.getString("saldo", "");
        telp = sharedPreferences.getString("telp", telp);

        TextView namaprofile = (TextView) view.findViewById(R.id.namaprofile);
        namaprofile.setText(id_username);
        TextView tlpprofile = (TextView) view.findViewById(R.id.tlpprofile);
        tlpprofile.setText(telp);
        TextView saldoprofile = (TextView) view.findViewById(R.id.saldoprofile);
        saldoprofile.setText(saldo);

        return view;
    }

    @OnClick(R.id.topup)
    public void topup(){
        Intent intent = new Intent(getActivity(), TopUpActivity.class);
        intent.putExtra("id_username", id_username);
        intent.putExtra("saldo", saldo);
        intent.putExtra("telp", telp);
        startActivity(intent);

    }

}
