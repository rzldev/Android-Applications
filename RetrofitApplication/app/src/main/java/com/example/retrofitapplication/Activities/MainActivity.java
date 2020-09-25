package com.example.retrofitapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.retrofitapplication.Databases.MySQLAdapter;
import com.example.retrofitapplication.Adapters.MainAdapter;
import com.example.retrofitapplication.Classes.MainRequest;
import com.example.retrofitapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.43.205/android/android/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    List<MainRequest> mainRequestList;
    RecyclerView recyclerView;
    FloatingActionButton bookmark;
    MainAdapter adapter;
    MySQLAdapter a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializing();
        myAdapter();
        myClick();
        prepareData();
    }

    private void myClick() {
        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookmarkActivity.class);
                startActivity(intent);
            }
        });
    }

    private void prepareData() {
        a.getData(adapter);
    }

    private void initializing() {
        recyclerView = findViewById(R.id.mainRecycler);
        bookmark = findViewById(R.id.bookmark);

        mainRequestList = new ArrayList<>();
        a = new MySQLAdapter(MainActivity.this, retrofit);
    }

    private void myAdapter() {
        adapter = new MainAdapter(this, mainRequestList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        adapter.clear();
        a.getData(adapter);
    }
}
