package com.example.simplecashierapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText monitor;
    private Button kembali;
    private int xEsTeh, xEsJeruk, xNasiPecel, xNasiRawon;
    private int pEsTeh, pEsJeruk, pNasiPecel, pNasiRawon, pTotal;
    private String monitorText, esTehText, esJerukText, nasiPecelText, nasiRawonText, totalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        monitor = findViewById(R.id.monitor);
        kembali = findViewById(R.id.kembali);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        xEsTeh = bundle.getInt("teh");
        xEsJeruk = bundle.getInt("jeruk");
        xNasiPecel = bundle.getInt("pecel");
        xNasiRawon = bundle.getInt("rawon");

        pEsTeh = 3000;
        pEsJeruk = 4000;
        pNasiPecel = 10000;
        pNasiRawon = 12000;
        pTotal = (xEsTeh * pEsTeh) + (xEsJeruk * pEsJeruk) + (xNasiPecel * pNasiPecel) + (xNasiRawon * pNasiRawon);

        monitorText = "KANTIN KEJUJURAN\nNOTA PENJUALAN\n09 OKTOBER 2019 12:00\n\n";

        if (xEsTeh > 0) {
            esTehText = String.valueOf(xEsTeh) + " Es Teh\t\t\t\t" + String.valueOf(xEsTeh) + " x " + String.valueOf(pEsTeh) + "\t\t" + String.valueOf(xEsTeh * pEsTeh) + "\n";
            monitorText = monitorText + esTehText;
        }
        if (xEsJeruk > 0) {
            esJerukText = String.valueOf(xEsJeruk) + " Es Jeruk\t\t\t" + String.valueOf(xEsJeruk) + " x " + String.valueOf(pEsJeruk) + "\t\t" + String.valueOf(xEsJeruk * pEsJeruk) + "\n";
            monitorText = monitorText + esJerukText;
        }
        if (xNasiPecel > 0) {
            nasiPecelText = String.valueOf(xNasiPecel) + " Nasi Pecel\t\t" + String.valueOf(xNasiPecel) + " x " + String.valueOf(pNasiPecel) + "\t\t" + String.valueOf(xNasiPecel * pNasiPecel) + "\n";
            monitorText = monitorText + nasiPecelText;
        }
        if (xNasiRawon > 0) {
            nasiRawonText = String.valueOf(xNasiRawon) + " Nasi Rawon\t" + String.valueOf(xNasiRawon) + " x " + String.valueOf(pNasiRawon) + "\t\t" + String.valueOf(xNasiRawon * pNasiRawon) + "\n";
            monitorText = monitorText + nasiRawonText;
        }

        totalText = "\t\t\t\t\t\t\t\t\t\t\t\tTotal\t" + String.valueOf(pTotal);
        monitorText = monitorText + totalText;
        monitor.setText(monitorText);

        kembali.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.kembali :
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
