package com.rzldev.mkproject.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rzldev.mkproject.API.ApiPackage;
import com.rzldev.mkproject.R;
import com.rzldev.mkproject.Responds.InsertUpdateDeleteRespond;

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

public class AdminFilmInsertActivity extends AppCompatActivity {

    @BindView(R.id.insert_judulET)
            EditText insertjudul;

    @BindView(R.id.insert_sinopsisET)
            EditText insertsinopsis;

    @BindView(R.id.insert_hargaET)
            EditText insertharga;

    @BindView(R.id.insert_durasiET)
            EditText insertdurasi;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://habibrohman046.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    ApiPackage apiPackage= retrofit.create(ApiPackage.class);

    String judul;
    String sinopsis;
    int harga;
    int durasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_film_insert);
        ButterKnife.bind(this);
    }

    private void saveData() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading ...");
        dialog.setCancelable(false);
        dialog.show();

        judul = insertjudul.getText().toString();
        sinopsis = insertsinopsis.getText().toString();
        harga = Integer.parseInt(insertharga.getText().toString());
        durasi = Integer.parseInt(insertdurasi.getText().toString());

        Call<InsertUpdateDeleteRespond> call = apiPackage.insertfilmrespond(judul, sinopsis, harga, durasi);

        call.enqueue(new Callback<InsertUpdateDeleteRespond>() {
            @Override
            public void onResponse(Call<InsertUpdateDeleteRespond> call, Response<InsertUpdateDeleteRespond> response) {
                if (response.isSuccessful()) {
                    dialog.hide();
                    Toast.makeText(AdminFilmInsertActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdminFilmInsertActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    dialog.hide();
                    Toast.makeText(AdminFilmInsertActivity.this, "Try it", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InsertUpdateDeleteRespond> call, Throwable t) {
                dialog.hide();
                Toast.makeText(AdminFilmInsertActivity.this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.insert_film)
    public void insertfilm() {
        if(insertjudul.getText().toString() == null){
            insertjudul.setError("Masukan judul film");
        }else if (insertsinopsis.getText().toString() == null){
            insertsinopsis.setError("Masukan sinopsis film");
        }else if (insertharga.getText().toString() == null){
            insertharga.setError("Masukan harga film");
        } else if (insertdurasi.getText().toString() == null) {
            insertdurasi.setError("Masukan durasi film");
        } else {
            saveData();
        }
    }
}
