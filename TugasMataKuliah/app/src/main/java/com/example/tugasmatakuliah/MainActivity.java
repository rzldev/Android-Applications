package com.example.tugasmatakuliah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ArrayList<String>> mk1 = new ArrayList<ArrayList<String>>();

    RecyclerView recyclerView;
    ArrayList<MataKuliah> listMatKul = new ArrayList<>();
    MataKuliahAdapter MatKulAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        MatKulAdapter = new MataKuliahAdapter(MainActivity.this, listMatKul);

        prepareData();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(MatKulAdapter);
        MatKulAdapter.notifyDataSetChanged();
    }

    private void prepareData() {
        MataKuliah mk = new MataKuliah();
        mk.setGambar(R.drawable.kitty);
        mk.setMatakuliah("Kualitas Data");
        mk.setSks(10);
        mk.setTugas(80);
        mk.setUts(70);
        mk.setUas(60);
        listMatKul.add(mk);

        mk = new MataKuliah();
        mk.setGambar(R.drawable.kitty);
        mk.setMatakuliah("Integrasi Data");
        mk.setSks(20);
        mk.setTugas(82);
        mk.setUts(72);
        mk.setUas(62);
        listMatKul.add(mk);

        mk = new MataKuliah();
        mk.setGambar(R.drawable.kitty);
        mk.setMatakuliah("Kecerdasan Bisnis");
        mk.setSks(30);
        mk.setTugas(84);
        mk.setUts(74);
        mk.setUas(64);
        listMatKul.add(mk);

        mk = new MataKuliah();
        mk.setGambar(R.drawable.kitty);
        mk.setMatakuliah("Struktur Data");
        mk.setSks(40);
        mk.setTugas(86);
        mk.setUts(76);
        mk.setUas(66);
        listMatKul.add(mk);
    }
}
