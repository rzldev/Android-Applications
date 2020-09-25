/*
package com.rzldev.mkproject.item;

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
import com.rzldev.mkproject.Adapter.ItemFilmAdapter;
import com.rzldev.mkproject.R;
import com.rzldev.mkproject.Responds.FilmRespond;
import com.rzldev.mkproject.Results.FilmResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class item_film extends Fragment {

    @BindView(R.id.resin)
    RecyclerView recyclerView;

    private List<FilmResult> results = new ArrayList<>();
    private ItemFilmAdapter itemFilmAdapter;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://habibrohman046.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    ApiPackage apiPackage= retrofit.create(ApiPackage.class);

    public item_film() {
        // Required empty public constructor
    }

    String id_film;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.item_film, container, false);

        ButterKnife.bind(this,view);

        Intent intent = getActivity().getIntent();
        id_film = intent.getStringExtra("id_film");
        Log.d("asdqwe",id_film);

        itemFilmAdapter = new ItemFilmAdapter(getContext(), results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(itemFilmAdapter);

        loaddata();

        return view;
    }

    public void loaddata(){

        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading ...");
        dialog.setCancelable(false);
        dialog.show();


        Call<FilmRespond> call = apiPackage.showsinopsis(id_film);

        call.enqueue(new Callback<FilmRespond>() {
            @Override
            public void onResponse(Call<FilmRespond> call, Response<FilmRespond> response) {

                Log.d("Responsebody",response.body().getResult().toString());

                if (response.isSuccessful()) {
                    dialog.hide();
                    results = response.body().getResult();
                    Log.d("Responsebody",response.body().getResult().toString());
                    itemFilmAdapter = new ItemFilmAdapter(getContext(), results);
                    recyclerView.setAdapter(itemFilmAdapter);

                } else {
                    Toast.makeText(getContext(), "Try it", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<FilmRespond> call, Throwable t) {
                dialog.hide();
            }
        });
    }

}
*/
