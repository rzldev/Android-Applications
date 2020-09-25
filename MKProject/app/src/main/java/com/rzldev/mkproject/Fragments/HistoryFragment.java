package com.rzldev.mkproject.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rzldev.mkproject.API.ApiPackage;
import com.rzldev.mkproject.Adapter.HistoryAdapter;
import com.rzldev.mkproject.Adapter.PlayingAdapter;
import com.rzldev.mkproject.R;
import com.rzldev.mkproject.Responds.HistoryRespond;
import com.rzldev.mkproject.Responds.PlayingRespond;
import com.rzldev.mkproject.Results.HistoryResult;
import com.rzldev.mkproject.Results.PlayingResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryFragment extends Fragment {

    @BindView(R.id.rehistory)
    RecyclerView recyclerView;

    private List<HistoryResult> results = new ArrayList<>();
    private HistoryAdapter historyAdapter;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://habibrohman046.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    ApiPackage apiPackage= retrofit.create(ApiPackage.class);

    public HistoryFragment() {
        // Required empty public constructor
    }

    String username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        ButterKnife.bind(this,view);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("usersaldo", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");

        historyAdapter = new HistoryAdapter(getContext(), results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(historyAdapter);

        loaddata();

        return view;
    }

    public void loaddata(){

        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading ...");
        dialog.setCancelable(false);
        dialog.show();


        Call<HistoryRespond> call = apiPackage.historyrespond(username);

        call.enqueue(new Callback<HistoryRespond>() {
            @Override
            public void onResponse(Call<HistoryRespond> call, Response<HistoryRespond> response) {

                Log.d("Responsebody",response.body().getResult().toString());

                if (response.isSuccessful()) {
                    dialog.hide();
                    results = response.body().getResult();
                    Log.d("Responsebody",response.body().getResult().toString());
                    historyAdapter = new HistoryAdapter(getContext(), results);
                    recyclerView.setAdapter(historyAdapter);

                } else {
                    Toast.makeText(getContext(), "Try it", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<HistoryRespond> call, Throwable t) {
                dialog.hide();
            }
        });
    }
}
