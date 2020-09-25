package com.example.recyclerviewadvanced;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText inputNama, inputLama, inputKenangan;
    Button simpan;
    RecyclerView recyclerView;

    MainAdapter adapter;
    ArrayList<Mantan> mantans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializing();
        setMyAdapter();

        simpan.setOnClickListener(this);
    }

    private void setMyAdapter() {
        adapter = new MainAdapter(this, mantans);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void insertData() {
        Mantan m = new Mantan();

        m.setNama(inputNama.getText().toString());
        m.setLama(Integer.parseInt(inputLama.getText().toString()));
        m.setKenangan(inputKenangan.getText().toString());

        mantans.add(m);
        adapter.notifyDataSetChanged();

    }

    private void initializing() {
        inputNama = findViewById(R.id.inputNama);
        inputLama = findViewById(R.id.inputLama);
        inputKenangan = findViewById(R.id.inputKenangan);
        simpan = findViewById(R.id.btnSimpan);
        recyclerView = findViewById(R.id.reMantan);

        mantans = new ArrayList<>();
    }

    @Override
    public void onClick(View view) {
        if (inputNama.getText().toString().isEmpty()) {
            inputNama.setError("Masukan Nama");
            inputNama.requestFocus();
            return;
        }
        if (inputLama.getText().toString().isEmpty()) {
            inputLama.setError("Masukan Lama");
            inputLama.requestFocus();
            return;
        }
        if (inputKenangan.getText().toString().isEmpty()) {
            inputKenangan.setError("Masukan Kenangan");
            inputKenangan.requestFocus();
            return;
        }

        insertData();
    }
}
