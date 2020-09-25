package com.example.greendaoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TambahActivity extends AppCompatActivity {

    private EditText et_nama, et_harga;
    private Button btn_simpan;

    private DaoSession dbSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        //Set Title Action Bar
        getSupportActionBar().setTitle("Insert Data Barang");
        dbSession = DbHandler.getInstance(TambahActivity.this);
        et_nama = findViewById(R.id.et_nama);
        et_harga = findViewById(R.id.et_harga);
        btn_simpan = findViewById(R.id.btn_simpan);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = et_nama.getText().toString();
                String harga = et_harga.getText().toString();
                if(nama.isEmpty() && harga.isEmpty()){
                    Toast.makeText(TambahActivity.this, "Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }else{
                    barang b = new barang();
                    b.setNamaBarang(nama);
                    b.setHargaBarang(Integer.parseInt(harga));
                    dbSession.getBarangDao().insert(b);
                    Toast.makeText(TambahActivity.this, "Berhasil Menambah Data Barang", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(TambahActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}
