package com.example.benakno.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.Toast;

import com.example.benakno.DB.MyDBAdapter;
import com.example.benakno.R;

public class FakeMapsActivity extends AppCompatActivity {

    EditText address;
    Button btnOrder;
    TextClock textClock;

    String type, date, status, merk, quantity, description, building, user;

    MyDBAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_maps);

        initalizing();
        myCLick();

    }

    private void myCLick() {
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputData();

            }
        });
    }

    private void myToast() {
        Toast.makeText(this, "Tipe : " + type, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Date : " + date, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Status : " + status, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Merk : " + merk, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Qty : " + quantity, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Desc : " + description, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Building : " + building, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Address : " + address.getText().toString(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "User : " + user, Toast.LENGTH_SHORT).show();

    }

    private void myData() {
        Intent i = getIntent();

        SharedPreferences sp = getSharedPreferences("UserData", MODE_PRIVATE);

        type = i.getStringExtra("tipe");
        date = textClock.getText().toString();
        status = "unfinished";
        merk = i.getStringExtra("merk");
        quantity = i.getStringExtra("quantity");
        description = i.getStringExtra("desc");
        building = i.getStringExtra("building");
        user = sp.getString("name", "");
    }

    private void inputData() {
        myData();

        long id = adapter.insertOrder(type, date, status, merk, quantity, description, building, address.getText().toString(), user);

        if (id <= 0) {
            Toast.makeText(this, "Gagal melakukan order", Toast.LENGTH_SHORT).show();

        } else {
            Intent intent = new Intent(FakeMapsActivity.this, FinishOrderActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("id", String.valueOf(id));
            intent.putExtra("a", "finish");
            Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();

        }
    }

    private void initalizing() {
        address = findViewById(R.id.inputLokasi);
        btnOrder = findViewById(R.id.btnOrder);
        textClock = findViewById(R.id.dateTime);

        adapter = new MyDBAdapter(this);
    }
}
