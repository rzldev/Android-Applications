package com.example.personalaccounting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReportActivity extends AppCompatActivity {

    TextView totalTransaksi, totalDebit, totalKredit, saldoAkhir;
    int transaksi, debit, kredit, saldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        initializing();
        myIntent();
        mySetText();
    }

    private void myIntent() {
        Intent i = getIntent();
        transaksi = i.getIntExtra("transaksi", 0);
        debit = i.getIntExtra("debit", 0);
        kredit = i.getIntExtra("kredit", 0);
        saldo = i.getIntExtra("saldo", 0);
    }

    private void mySetText() {
        totalTransaksi.setText("\t" + String.valueOf(transaksi));
        totalKredit.setText("\t" + String.valueOf(kredit));
        totalDebit.setText("\t" + String.valueOf(debit));
        saldoAkhir.setText("\t" + String.valueOf(saldo));
    }

    private void initializing() {
        totalTransaksi = findViewById(R.id.totalTransaksi);
        totalDebit = findViewById(R.id.totalDebit);
        totalKredit = findViewById(R.id.totalKredit);
        saldoAkhir = findViewById(R.id.totalSaldo);
    }
}
