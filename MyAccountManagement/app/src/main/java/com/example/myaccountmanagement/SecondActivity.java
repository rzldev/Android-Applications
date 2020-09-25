package com.example.myaccountmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    ImageView plus;

    MyDBAdapter myDBAdapter;

    SecondAdapter adapter;
    ArrayList<Account> accounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initializing();
        getData();
        setAdapter();
        myClick();

    }

    private void myClick() {
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, DetailAccountActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setAdapter() {
        adapter = new SecondAdapter(this, accounts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SecondActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void getData() {
        String data = myDBAdapter.getData();
        String[] sampleData = data.split("\n");

        for (int x = 0; x < sampleData.length; x++) {
            Account a = new Account();

            String[] abc = sampleData[x].split(" ");
            a.setId(abc[0]);
            a.setNama(abc[1]);
            a.setEmail(abc[2]);
            a.setPassword(abc[3]);

            accounts.add(a);

        }
    }

    private void initializing() {
        toolbar = findViewById(R.id. toolbar);
        recyclerView = findViewById(R.id.recycler);
        plus = findViewById(R.id.btnInsert);

        myDBAdapter = new MyDBAdapter(this);
        accounts = new ArrayList<>();

    }
}
