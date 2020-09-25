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

import com.example.benakno.Adapters.HistoryAdapter;
import com.example.benakno.Adapters.OrderAdapter;
import com.example.benakno.Classes.Order;
import com.example.benakno.DB.MyDBAdapter;
import com.example.benakno.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    RecyclerView recyclerView;
    TextView kosong;

    HistoryAdapter adapter;
    ArrayList<Order> orders;

    MyDBAdapter myDBAdapter;
    String id, type, date, status, merk, quantity, description, building, address, user, worker;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        initializing(view);
        getData();
        setAdapter();
        setContent();

        return view;
    }

    private void setAdapter() {
        adapter = new HistoryAdapter(getActivity(), orders);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void initializing(View view) {
        recyclerView = view.findViewById(R.id.historyRecycler);
        kosong = view.findViewById(R.id.kosong2);

        myDBAdapter = new MyDBAdapter(getActivity());
        orders = new ArrayList<>();
    }

    private void getData() {
        SharedPreferences sp = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String name = sp.getString("name", "");

        String data = myDBAdapter.getOrder(name);


        if (!data.equals("")) {
            String[] sampleData = data.split("\n");

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

                if (status.equals("finished")) {
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

    private void setContent() {
        if (orders.size() > 0) {
            kosong.setVisibility(View.GONE);
        }

    }

}
