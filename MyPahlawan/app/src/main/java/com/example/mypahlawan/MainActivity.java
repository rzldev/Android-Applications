package com.example.mypahlawan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView pahlawanListview;

    private Adapter pahlawanAdapter;
    private String[] pahlawanNames;
    private String[] pahlawanDescriptions;
    private TypedArray pahlawanPhotos;
    private ArrayList<Pahlawan> pahlawan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareItem();
        AddItem();

        pahlawanListview = findViewById(R.id.pahlawanListview);
        pahlawanAdapter = new PahlawanAdapter(this, pahlawan);
        pahlawanListview.setAdapter((ListAdapter) pahlawanAdapter);

    }

    private void prepareItem() {
        pahlawanNames = getResources().getStringArray(R.array.pahlawanName);
        pahlawanDescriptions = getResources().getStringArray(R.array.pahlawanDescription);
        pahlawanPhotos = getResources().obtainTypedArray(R.array.pahlawanImage);
    }

    private void AddItem() {
        pahlawan = new ArrayList<>();
        for (int x = 0; x < pahlawanNames.length; x++) {
            Pahlawan mPahlawan = new Pahlawan();
            mPahlawan.setName(pahlawanNames[x]);
            mPahlawan.setDescription(pahlawanDescriptions[x]);
            mPahlawan.setPhoto(pahlawanPhotos.getResourceId(x, -1));
            pahlawan.add(mPahlawan);
        }
    }

}
