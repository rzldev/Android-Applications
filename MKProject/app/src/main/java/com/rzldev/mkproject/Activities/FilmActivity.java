package com.rzldev.mkproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.rzldev.mkproject.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilmActivity extends AppCompatActivity  {

    String id_username, saldo;
    String id_judul, durasi, harga, sinopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        id_username = intent.getStringExtra("username");
        saldo = intent.getStringExtra("saldo");
        id_judul = intent.getStringExtra("id_judul");
        durasi = intent.getStringExtra("durasi");
        harga = intent.getStringExtra("harga");
        sinopsis = intent.getStringExtra("sinopsis");
        Log.d("asd", id_judul);

        TextView judulfilm = (TextView) findViewById(R.id.judul);
        judulfilm.setText(id_judul);
        TextView durasifilm = (TextView) findViewById(R.id.durasi);
        durasifilm.setText(durasi);
        TextView hargafilm = (TextView) findViewById(R.id.harga);
        hargafilm.setText(harga);
        TextView sinopsisfilm = (TextView) findViewById(R.id.sin);
        sinopsisfilm.setText(sinopsis);
    }

    @OnClick(R.id.beli)
    public void beli(){

        Intent intent = new Intent(FilmActivity.this, PembelianActivity.class);
        intent.putExtra("username", id_username);
        intent.putExtra("saldo", saldo);
        intent.putExtra("judul", id_judul);
        intent.putExtra("harga", harga);
        startActivity(intent);

    }

}
