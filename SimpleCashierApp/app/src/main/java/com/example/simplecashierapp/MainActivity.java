package com.example.simplecashierapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText monitor;
    private Button btnEsTeh, btnEsJeruk, btnNasiPecel, btnNasiRawon, btnBayar, btnBatal;
    private int xEsTeh, xEsJeruk, xNasiPecel, xNasiRawon;
    private int pEsTeh, pEsJeruk, pNasiPecel, pNasiRawon, pTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monitor = findViewById(R.id.monitor);
        btnEsTeh = findViewById(R.id.esTeh);
        btnEsJeruk = findViewById(R.id.esJeruk);
        btnNasiPecel = findViewById(R.id.nasiPecel);
        btnNasiRawon = findViewById(R.id.nasiRawon);
        btnBayar = findViewById(R.id.bayar);
        btnBatal = findViewById(R.id.batal);

        monitor.setVerticalScrollBarEnabled(false);
        monitor.setHorizontalScrollBarEnabled(false);

        xEsTeh = 0;
        xEsJeruk = 0;
        xNasiPecel = 0;
        xNasiRawon = 0;

        pEsTeh = 3000;
        pEsJeruk = 4000;
        pNasiPecel = 10000;
        pNasiRawon = 12000;
        pTotal = 0;

        monitor.setText(null);


        btnEsTeh.setOnClickListener(this);
        btnEsJeruk.setOnClickListener(this);
        btnNasiPecel.setOnClickListener(this);
        btnNasiRawon.setOnClickListener(this);
        btnBayar.setOnClickListener(this);
        btnBatal.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.esTeh :
                xEsTeh += 1;
                btnEsTeh.setText("Es Teh(" + String.valueOf(xEsTeh) + ")");
                pTotal += pEsTeh;
                monitor.setText(String.valueOf(pTotal));
                break;

            case R.id.esJeruk :
                xEsJeruk += 1;
                btnEsJeruk.setText("Es Jeruk(" + String.valueOf(xEsJeruk) + ")");
                pTotal += pEsJeruk;
                monitor.setText(String.valueOf(pTotal));
                break;

            case R.id.nasiPecel :
                xNasiPecel += 1;
                btnNasiPecel.setText("Nasi Pecel(" + String.valueOf(xNasiPecel) + ")");
                pTotal += pNasiPecel;
                monitor.setText(String.valueOf(pTotal));
                break;

            case R.id.nasiRawon :
                xNasiRawon += 1;
                btnNasiRawon.setText("Nasi Rawon(" + String.valueOf(xNasiRawon) + ")");
                pTotal += pNasiRawon;
                monitor.setText(String.valueOf(pTotal));
                break;

            case R.id.bayar :
                Bundle bundle = new Bundle();
                bundle.putInt("teh", xEsTeh);
                bundle.putInt("jeruk", xEsJeruk);
                bundle.putInt("pecel", xNasiPecel);
                bundle.putInt("rawon", xNasiRawon);

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                finish();
                break;

            case R.id.batal :
                xEsTeh = 0;
                xEsJeruk = 0;
                xNasiPecel = 0;
                xNasiRawon = 0;
                pTotal = 0;
                monitor.setText(null);
                btnEsTeh.setText("Es Teh");
                btnEsJeruk.setText("Es Jeruk");
                btnNasiPecel.setText("Nasi Pecel");
                btnNasiRawon.setText("Nasi Rawon");
                break;

        }

    }
}
