package com.rzldev.mkproject.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rzldev.mkproject.API.ApiPackage;
import com.rzldev.mkproject.Adapter.MostViewedAdapter;
import com.rzldev.mkproject.Adapter.PlayingAdapter;
import com.rzldev.mkproject.R;
import com.rzldev.mkproject.Responds.MostViewedRespond;
import com.rzldev.mkproject.Responds.PlayingRespond;
import com.rzldev.mkproject.Results.MostViewedResult;
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

public class MostViewedFragment extends Fragment {


    @BindView(R.id.rems)
    RecyclerView recyclerView;

    private List<MostViewedResult> results = new ArrayList<>();
    private MostViewedAdapter mostViewedAdapter;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://habibrohman046.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    ApiPackage apiPackage= retrofit.create(ApiPackage.class);

    String id_username, saldo, telp;


    public MostViewedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_most_viewed, container, false);

        ButterKnife.bind(this,view);

        mostViewedAdapter = new MostViewedAdapter(getContext(), results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mostViewedAdapter);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("usersaldo", Context.MODE_PRIVATE);
        id_username = sharedPreferences.getString("username", "");
        saldo = sharedPreferences.getString("saldo", "");
        telp = sharedPreferences.getString("telp", telp);

        loaddata();

        return view;
    }

    public void loaddata(){

        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading ...");
        dialog.setCancelable(false);
        dialog.show();


        Call<MostViewedRespond> call = apiPackage.mostviewedrespond();

        call.enqueue(new Callback<MostViewedRespond>() {
            @Override
            public void onResponse(Call<MostViewedRespond> call, Response<MostViewedRespond> response) {

                if (response.isSuccessful()) {
                    dialog.hide();
                    results = response.body().getResult();
                    mostViewedAdapter = new MostViewedAdapter(getContext(), results);
                    recyclerView.setAdapter(mostViewedAdapter);

                } else {
                    Toast.makeText(getContext(), "Try it", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MostViewedRespond> call, Throwable t) {
                dialog.hide();
            }
        });
    }

}
