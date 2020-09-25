package com.example.personalaccounting;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ListView mListView;
    Adapter mAdapter;
    ArrayList<Accounting> accountings;

    TextClock tanggal;
    TextView uraian, nominal;
    Button add, report;
    Spinner jenis;

    int transaksi, debit, kredit, saldo;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Initializing();
        SetAdapter();

        add.setOnClickListener(this);
        report.setOnClickListener(this);

    }

    private void Initializing() {
        mListView = findViewById(R.id.mListView);
        tanggal = findViewById(R.id.dateTime);
        uraian = findViewById(R.id.inputUraian);
        nominal = findViewById(R.id.inputNominal);
        add = findViewById(R.id.btnAdd);
        report = findViewById(R.id.btnReport);
        jenis = findViewById(R.id.inputJenis);

    }

    private void InputData() {
        Accounting acc = new Accounting();
        acc.setTanggal(tanggal.getText().toString());
        acc.setJenis(jenis.getSelectedItem().toString());
        acc.setUraian(uraian.getText().toString());
        acc.setNominal(Integer.parseInt(nominal.getText().toString()));
        accountings.add(acc);

    }

    private void SetAdapter() {
        accountings = new ArrayList<>();
        mAdapter = new MainAdapter(this, accountings);
        mListView.setAdapter((ListAdapter) mAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.btnAdd) :
                if (uraian.getText().toString().isEmpty()) {
                    uraian.setError("Masukan Uraian");
                    uraian.requestFocus();
                    return;
                }
                if (nominal.getText().toString().isEmpty()) {
                    nominal.setError("Masukan Nominal");
                    nominal.requestFocus();
                    return;
                }

                InputData();

                uraian.setText("");
                nominal.setText("");

                break;

            case (R.id.btnReport) :
                transaksi = 0;
                debit = 0;
                kredit = 0;
                saldo = 0;

                for (int x = 0; x < accountings.size(); x++) {
                    Accounting a = accountings.get(x);
                    transaksi = accountings.size();
                    if (a.getJenis().equals("Debit")) {
                        debit += a.getNominal();
                    } else if (a.getJenis().equals("Kredit")) {
                        kredit += a.getNominal();
                    }
                    saldo = debit - kredit;
                }

                Intent intent = new Intent(MainActivity.this, ReportActivity.class);
                intent.putExtra("transaksi", transaksi);
                intent.putExtra("debit", debit);
                intent.putExtra("kredit", kredit);
                intent.putExtra("saldo", saldo);
                startActivity(intent);
                break;
        }
    }
}
