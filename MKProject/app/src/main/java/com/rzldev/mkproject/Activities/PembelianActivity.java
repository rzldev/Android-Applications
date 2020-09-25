package com.rzldev.mkproject.Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rzldev.mkproject.API.ApiPackage;
import com.rzldev.mkproject.R;
import com.rzldev.mkproject.Responds.InsertUpdateDeleteRespond;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PembelianActivity extends AppCompatActivity {

    @BindView(R.id.jumlahtiket)
    EditText jumlah_tiket;

    @BindView(R.id.jadwalspinner)
    Spinner jadwal_spinner;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://habibrohman046.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    ApiPackage apiPackage= retrofit.create(ApiPackage.class);

    String username;
    int saldo;
    String telp;
    int saldoint;
    String judul;
    int tiket_dibeli;
    int total_harga;
    String waktu;
    int harga;
    int biaya_admin;
    int sisasaldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembelian);
        ButterKnife.bind(this);

        Intent intent = getIntent();/*
        username = intent.getStringExtra("username");
        saldo = intent.getStringExtra("saldo");*/
        judul = intent.getStringExtra("judul");
        harga = Integer.parseInt(intent.getStringExtra("harga"));
        SharedPreferences sharedPreferences = getSharedPreferences("usersaldo", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", "test");
        saldo = Integer.parseInt(sharedPreferences.getString("saldo", "test"));
        telp = sharedPreferences.getString("telp", "test");

        TextView test_username = (TextView) findViewById(R.id.testusername);
        test_username.setText("username : " +username);
        TextView test_saldo = (TextView) findViewById(R.id.testsaldo);
        test_saldo.setText("saldo : " +saldo);
        TextView test_judul = (TextView) findViewById(R.id.testjudul);
        test_judul.setText("judul : " +judul);
        TextView test_tiket = (TextView) findViewById(R.id.testtiket);
        test_tiket.setText("tiket : " +tiket_dibeli);
        TextView test_harga = (TextView) findViewById(R.id.testharga);
        test_harga.setText("harga : " +harga);
        TextView test_waktu = (TextView) findViewById(R.id.testwaktu);
        test_waktu.setText("waktu : " +waktu);
    }

    private void saveData() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading ...");
        dialog.setCancelable(false);
        dialog.show();
/*
        saldoint = Integer.parseInt(saldo);*/




        Call<InsertUpdateDeleteRespond> call = apiPackage.insertpembelianrespond(username, judul, tiket_dibeli,
                total_harga, waktu);

        call.enqueue(new Callback<InsertUpdateDeleteRespond>() {
            @Override
            public void onResponse(Call<InsertUpdateDeleteRespond> call, Response<InsertUpdateDeleteRespond> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(PembelianActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    sisasaldo = saldo - total_harga;

                    SharedPreferences sharedPreferences = getSharedPreferences("usersaldo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username);
                    editor.putString("saldo", String.valueOf(sisasaldo));
                    editor.apply();

                    Intent intent = new Intent(PembelianActivity.this, MainActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("saldo", String.valueOf(sisasaldo));
                    intent.putExtra("telp", telp);

                    startActivity(intent);
                } else {
                    dialog.hide();
                    Toast.makeText(PembelianActivity.this, "Try it", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InsertUpdateDeleteRespond> call, Throwable t) {
                dialog.hide();
                Toast.makeText(PembelianActivity.this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });

        Call<InsertUpdateDeleteRespond> call2 = apiPackage.updatesaldorespond(username, sisasaldo);

        call2.enqueue(new Callback<InsertUpdateDeleteRespond>() {
            @Override
            public void onResponse(Call<InsertUpdateDeleteRespond> call2, Response<InsertUpdateDeleteRespond> response) {
                if (response.isSuccessful()) {
                    dialog.hide();
                    Toast.makeText(PembelianActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PembelianActivity.this, MainActivity.class);
/*                    intent.putExtra("username", id_username);
                    intent.putExtra("saldo", topupstring);
                    intent.putExtra("telp", telp);*/
                    startActivity(intent);
                } else {
                    dialog.hide();
                    Toast.makeText(PembelianActivity.this, "Try it", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InsertUpdateDeleteRespond> call, Throwable t) {
                dialog.hide();
                Toast.makeText(PembelianActivity.this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btnbelitiket)
    public void belitiket() {
        tiket_dibeli = Integer.parseInt(jumlah_tiket.getText().toString());
        biaya_admin = ((tiket_dibeli * harga) / 10);
        total_harga = ((tiket_dibeli * harga) + biaya_admin);
        sisasaldo = saldo - total_harga;
        waktu = jadwal_spinner.getSelectedItem().toString();

        Toast.makeText(PembelianActivity.this, String.valueOf(biaya_admin), Toast.LENGTH_SHORT).show();

        if(jadwal_spinner.getSelectedItem().equals("Pilih Jadwal")){
            TextView errorText = (TextView)jadwal_spinner.getSelectedView();
            errorText.setError("");
            errorText.setText("Pilih jadwal yang tersedia");
        } else if (jumlah_tiket.getText() == null) {
            jumlah_tiket.setError("Masukan jumlah tiket");
        } else {
            if (total_harga > saldo) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Saldo Tidak cukup");
                alertDialogBuilder
                        .setMessage("Maaf Saldo Anda Tidak Cukup")
                        .setCancelable(false)
                        .setNegativeButton("Ok",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }else if (total_harga <= saldo) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

                // set title dialog
                alertDialogBuilder.setTitle("Cek Pembelian?");

                // set pesan dari dialog
                alertDialogBuilder
                        .setMessage("Yakin ingin membeli film ?")
                        .setCancelable(false)
                        .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                saveData();
                            }
                        })
                        .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();

                alertDialog.show();
            } else {
                Toast.makeText(PembelianActivity.this, "ERROR!!!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
