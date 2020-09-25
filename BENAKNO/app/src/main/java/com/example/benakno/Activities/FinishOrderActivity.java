package com.example.benakno.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.benakno.DB.MyDBAdapter;
import com.example.benakno.R;

public class FinishOrderActivity extends AppCompatActivity {

    TextView nama, service;
    Button ok;

    MyDBAdapter myDBAdapter;
    String user, id, a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_order);

        initializing();
        showData();
        disableButton();
        myClick();

    }

    private void myClick() {
        ok.setText("Selesai");

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(FinishOrderActivity.this)
                        .setTitle("Delete")
                        .setMessage("Apakah anda yakin?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                int update = myDBAdapter.updateOrder(user, id);
                                Intent intent = new Intent(FinishOrderActivity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .show();

            }
        });
    }

    private void disableButton() {
        a = getIntent().getStringExtra("a");

        if (a.equals("finish")) {
            ok.setVisibility(View.GONE);

        } else {
            ok.setVisibility(View.VISIBLE);

        }
    }

    private void showData() {
        SharedPreferences sp = getSharedPreferences("UserData", MODE_PRIVATE);
        user = sp.getString("name", "");

        Intent gi = getIntent();
        id = gi.getStringExtra("id");

        String data = myDBAdapter.getSelectedOrder(user, id);
        String[] sampleData = data.split(" - ");

        String type = sampleData[1];
        String worker = sampleData[10];

        nama.setText(worker);
        if (type.equals("ac")) {
            service.setText("Service AC");

        } else if (type.equals("ledeng")) {
            service.setText("Service Ledeng");

        } else {
            service.setText("");

        }

    }

    private void initializing() {
        nama = findViewById(R.id.nama);
        service = findViewById(R.id.service);
        ok = findViewById(R.id.btnOK);

        myDBAdapter = new MyDBAdapter(this);

    }
}
