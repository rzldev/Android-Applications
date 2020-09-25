package com.example.myaccountmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class DetailAccountActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText nama, email, password;
    Button btnInsert, btnUpdate, btnDelete;
    Toolbar toolbar;

    String intentId, intentNama, intentEmail, intentPass;

    MyDBAdapter myDBAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_account);
        
        initializing();
        myToolbar();
        myIntent();
        myClick();

    }

    private void myToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");

    }

    private void myClick() {
        btnInsert.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    private void myIntent() {
        Intent myIntent = getIntent();
        intentId = myIntent.getStringExtra("id");
        intentNama = myIntent.getStringExtra("nama");
        intentEmail = myIntent.getStringExtra("email");
        intentPass = myIntent.getStringExtra("password");

        if (intentId == null) {
            btnUpdate.setVisibility(View.GONE);
            btnDelete.setVisibility(View.GONE);

        } else if (intentId != null) {
            nama.setText(intentNama);
            email.setText(intentEmail);
            password.setText(intentPass);
            btnInsert.setVisibility(View.GONE);

        }
    }

    private void initializing() {
        nama = findViewById(R.id.inputNama);
        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        toolbar = findViewById(R.id.toolbar);

        myDBAdapter = new MyDBAdapter(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnInsert :
                rule();
                long insert = myDBAdapter.insertData(nama.getText().toString(), email.getText().toString(), password.getText().toString());

                if (insert <= 0) {
                    Toast.makeText(this, "Gagal Tambah Data", Toast.LENGTH_SHORT).show();

                } else {
                    Intent insertIntent = new Intent(DetailAccountActivity.this, SecondActivity.class);
                    insertIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(insertIntent);
                    finish();

                }

                break;

            case R.id.btnUpdate :
                rule();
                long update = myDBAdapter.updateData(intentNama, intentEmail, nama.getText().toString(), email.getText().toString(), password.getText().toString());

                if (update <= 0) {
                    Toast.makeText(this, "Gagal Update Data", Toast.LENGTH_SHORT).show();

                } else {
                    Intent updateIntent = new Intent(DetailAccountActivity.this, SecondActivity.class);
                    updateIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(updateIntent);
                    finish();

                }

                break;

            case R.id.btnDelete :
                rule();
                long delete = myDBAdapter.deleteData(nama.getText().toString(), email.getText().toString());

                if (delete <= 0) {
                    Toast.makeText(this, "Gagal Update Data", Toast.LENGTH_SHORT).show();

                } else {
                    Intent deleteIntent = new Intent(DetailAccountActivity.this, SecondActivity.class);
                    deleteIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(deleteIntent);
                    finish();

                }

                break;

        }
    }

    private void rule() {
        if (nama.getText().toString().isEmpty()) {
            nama.setError("Masukan Nama");
            nama.requestFocus();
            return;

        }
        if (email.getText().toString().isEmpty()) {
            email.setError("Masukan Email");
            email.requestFocus();
            return;

        }
        if (password.getText().toString().isEmpty()) {
            password.setError("Masukan Password");
            password.requestFocus();
            return;

        }
    }
}
