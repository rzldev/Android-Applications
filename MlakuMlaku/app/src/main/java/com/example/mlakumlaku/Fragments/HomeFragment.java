package com.example.mlakumlaku.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mlakumlaku.Adapters.HomeAdapter;
import com.example.mlakumlaku.Databases.APIPackages;
import com.example.mlakumlaku.R;
import com.example.mlakumlaku.Requests.WisataRequest;
import com.example.mlakumlaku.Responses.WisataResponse;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    SliderView sliderView;
    RecyclerView recyclerView;
    HomeAdapter adapter;
    List<WisataRequest> wisataRequestList;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.110/android/uas/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    APIPackages apiPackages = retrofit.create(APIPackages.class);

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        initialize(v);
        setAdapter();
        getData();

        return v;
    }

    private void getData() {
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");
        dialog.show();

        Call<WisataResponse> wisataResponseCall = apiPackages.WISATA_RESPONSE_CALL();
        wisataResponseCall.enqueue(new Callback<WisataResponse>() {
            @Override
            public void onResponse(Call<WisataResponse> call, Response<WisataResponse> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    wisataRequestList = response.body().getWisata();
                    adapter.addNewData(wisataRequestList);
                }
            }

            @Override
            public void onFailure(Call<WisataResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setAdapter() {
        adapter = new HomeAdapter(getActivity(), wisataRequestList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void initialize(View v) {
        recyclerView = v.findViewById(R.id.hRecycler);

        wisataRequestList = new ArrayList<>();
    }

}
