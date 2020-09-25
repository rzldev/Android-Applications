package com.rzldev.mkproject.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rzldev.mkproject.API.ApiPackage;
import com.rzldev.mkproject.Adapter.AdminHistoryAdapter;
import com.rzldev.mkproject.Adapter.HistoryAdapter;
import com.rzldev.mkproject.R;
import com.rzldev.mkproject.Responds.AdminHistoryRespond;
import com.rzldev.mkproject.Responds.HistoryRespond;
import com.rzldev.mkproject.Results.AdminHistoryResult;
import com.rzldev.mkproject.Results.HistoryResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminHistoryFragment extends Fragment {

    @BindView(R.id.rehistory_admin)
    RecyclerView recyclerView;

    private List<AdminHistoryResult> results = new ArrayList<>();
    private AdminHistoryAdapter adminHistoryAdapter;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://habibrohman046.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    ApiPackage apiPackage= retrofit.create(ApiPackage.class);

    public AdminHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_history, container, false);

        ButterKnife.bind(this,view);

        adminHistoryAdapter = new AdminHistoryAdapter(getContext(), results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adminHistoryAdapter);

        loaddata();

        return view;
    }

    public void loaddata(){

        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading ...");
        dialog.setCancelable(false);
        dialog.show();


        Call<AdminHistoryRespond> call = apiPackage.adminhistoryrespond();

        call.enqueue(new Callback<AdminHistoryRespond>() {
            @Override
            public void onResponse(Call<AdminHistoryRespond> call, Response<AdminHistoryRespond> response) {

                Log.d("Responsebody",response.body().getResult().toString());

                if (response.isSuccessful()) {
                    dialog.hide();
                    results = response.body().getResult();
                    Log.d("Responsebody",response.body().getResult().toString());
                    adminHistoryAdapter = new AdminHistoryAdapter(getContext(), results);
                    recyclerView.setAdapter(adminHistoryAdapter);

                } else {
                    Toast.makeText(getContext(), "Try it", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<AdminHistoryRespond> call, Throwable t) {
                dialog.hide();
            }
        });
    }


}
