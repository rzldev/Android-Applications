package com.example.retrofitapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.retrofitapplication.Adapters.BookmarkAdapter;
import com.example.retrofitapplication.Classes.DaoSession;
import com.example.retrofitapplication.Classes.Mahasiswa;
import com.example.retrofitapplication.Databases.DAODbHelper;
import com.example.retrofitapplication.Databases.DaoAdapter;
import com.example.retrofitapplication.R;

import java.util.ArrayList;
import java.util.List;

public class BookmarkActivity extends AppCompatActivity {

    List<Mahasiswa> mahasiswaList;
    RecyclerView recyclerView;
    BookmarkAdapter adapter;
    DaoSession daoSession;
    DaoAdapter dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        initializing();
        myAdapter();
        getData();
    }

    private void getData() {
        dao = new DaoAdapter(BookmarkActivity.this);
        dao.getMahasiswa(adapter);
    }

    private void myAdapter() {
        adapter = new BookmarkAdapter(BookmarkActivity.this, mahasiswaList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BookmarkActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        adapter.notifyDataSetChanged();
    }

    private void initializing() {
        recyclerView = findViewById(R.id.recycler);

        mahasiswaList = new ArrayList<>();
    }
}
