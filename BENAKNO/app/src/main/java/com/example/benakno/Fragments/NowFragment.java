package com.example.benakno.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.benakno.Adapters.OrderAdapter;
import com.example.benakno.DB.MyDBAdapter;
import com.example.benakno.Classes.Order;
import com.example.benakno.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowFragment extends Fragment {

    RecyclerView recyclerView;
    TextView kosong;

    OrderAdapter adapter;
    ArrayList<Order> orders;

    MyDBAdapter myDBAdapter;
    String id, type, date, status, merk, quantity, description, building, address, user, worker;

    public NowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_now, container, false);

        initializing(view);
        setAdapter();
        getData();
        setContent();

        return view;
    }

    private void setContent() {
        if (orders.size() > 0) {
            kosong.setVisibility(View.GONE);
        }

    }

    private void getData() {
        SharedPreferences sp = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String name = sp.getString("name", "");

        String data = myDBAdapter.getOrder(name);

        if (!data.equals("")) {
            String[] sampleData = data.split("\n");

            if (sampleData.length > 0) {
                for (int x = 0; x < sampleData.length; x++) {

                    Log.d("asdasd", sampleData[x]);

                    String[] singleData = sampleData[x].split(" - ");
                    id = singleData[0];
                    type = singleData[1];
                    date = singleData[2];
                    status = singleData[3];
                    merk = singleData[4];
                    quantity = singleData[5];
                    description = singleData[6];
                    building = singleData[7];
                    address = singleData[8];
                    user = singleData[9];
                    worker = singleData[10];

                    if (status.equals("unfinished")) {
                        Order o = new Order();
                        o.setId(id);
                        o.setType(type);
                        o.setDate(date);
                        o.setStatus(status);
                        o.setMerk(merk);
                        o.setQuantity(quantity);
                        o.setDescription(description);
                        o.setBuilding(building);
                        o.setAddress(address);
                        o.setUser(user);
                        o.setWorker(worker);
                        orders.add(o);

                    }
                }

            }

        }


    }

    private void initializing(View view) {
        recyclerView = view.findViewById(R.id.orderRecycler);
        kosong = view.findViewById(R.id.kosong1);

        orders = new ArrayList<>();
        myDBAdapter = new MyDBAdapter(getActivity());

    }

    private void setAdapter() {
        adapter = new OrderAdapter(getActivity(), orders);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

}
