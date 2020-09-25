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
import android.widget.Adapter;
import android.widget.Toast;

import com.rzldev.mkproject.API.ApiPackage;
import com.rzldev.mkproject.Adapter.PlayingAdapter;
import com.rzldev.mkproject.Adapter.ProfileAdapter;
import com.rzldev.mkproject.R;
import com.rzldev.mkproject.Responds.PlayingRespond;
import com.rzldev.mkproject.Responds.ProfileRespond;
import com.rzldev.mkproject.Results.PlayingResult;
import com.rzldev.mkproject.Results.ProfileResult;

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
public class PlayingFragment extends Fragment {

    @BindView(R.id.Recycle)
    RecyclerView recyclerView;

    private List<PlayingResult> results = new ArrayList<>();
    private PlayingAdapter playingAdapter;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://habibrohman046.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    ApiPackage apiPackage= retrofit.create(ApiPackage.class);

    String id_username, saldo, telp;

    public PlayingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_playing, container, false);

        ButterKnife.bind(this,view);

        playingAdapter = new PlayingAdapter(getContext(), results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(playingAdapter);

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

        Call<PlayingRespond> call = apiPackage.playingrespond();

        call.enqueue(new Callback<PlayingRespond>() {
            @Override
            public void onResponse(Call<PlayingRespond> call, Response<PlayingRespond> response) {

                if (response.isSuccessful()) {
                    dialog.hide();
                    results = response.body().getResult();
                    playingAdapter = new PlayingAdapter(getContext(), results);
                    recyclerView.setAdapter(playingAdapter);

                } else {
                    Toast.makeText(getContext(), "Try it", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PlayingRespond> call, Throwable t) {
                dialog.hide();
            }
        });
    }
}
