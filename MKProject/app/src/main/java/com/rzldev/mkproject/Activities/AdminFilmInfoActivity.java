package com.rzldev.mkproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.rzldev.mkproject.R;

import butterknife.ButterKnife;

public class AdminFilmInfoActivity extends AppCompatActivity {

    String id_judul, total_tiket, total_pendapatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_film_info);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        id_judul = intent.getStringExtra("id_judul");
        total_tiket = intent.getStringExtra("total_tiket");
        total_pendapatan = intent.getStringExtra("total_pendapatan");
        Log.d("asd", total_tiket);

        TextView judulinfo = (TextView) findViewById(R.id.judul_film_info);
        judulinfo.setText(id_judul);
        TextView totaltiketinfo = (TextView) findViewById(R.id.tiket_terjual_admin);
        totaltiketinfo.setText(total_tiket);
        TextView totalpendapataninfo = (TextView) findViewById(R.id.pendapatan_total_admin);
        totalpendapataninfo.setText(total_pendapatan);
    }

}
