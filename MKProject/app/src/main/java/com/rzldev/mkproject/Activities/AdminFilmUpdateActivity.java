package com.rzldev.mkproject.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.rzldev.mkproject.API.ApiPackage;
import com.rzldev.mkproject.R;
import com.rzldev.mkproject.Responds.InsertUpdateDeleteRespond;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminFilmUpdateActivity extends AppCompatActivity {
    @BindView(R.id.update_judulET)
    EditText updatejudul;

    @BindView(R.id.update_sinopsisET)
    EditText updatesinopsis;

    @BindView(R.id.update_hargaET)
    EditText updateharga;

    @BindView(R.id.update_durasiET)
    EditText updatedurasi;

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
        setContentView(R.layout.activity_admin_film_update);
        ButterKnife.bind(this);
    }

    private void saveData() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading ...");
        dialog.setCancelable(false);
        dialog.show();

        judul = updatejudul.getText().toString();
        sinopsis = updatesinopsis.getText().toString();
        harga = Integer.parseInt(updateharga.getText().toString());
        durasi = Integer.parseInt(updatedurasi.getText().toString());

        Call<InsertUpdateDeleteRespond> call = apiPackage.insertfilmrespond(judul, sinopsis, harga, durasi);

        call.enqueue(new Callback<InsertUpdateDeleteRespond>() {
            @Override
            public void onResponse(Call<InsertUpdateDeleteRespond> call, Response<InsertUpdateDeleteRespond> response) {
                if (response.isSuccessful()) {
                    dialog.hide();
                    Toast.makeText(AdminFilmUpdateActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdminFilmUpdateActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    dialog.hide();
                    Toast.makeText(AdminFilmUpdateActivity.this, "Try it", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InsertUpdateDeleteRespond> call, Throwable t) {
                dialog.hide();
                Toast.makeText(AdminFilmUpdateActivity.this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.update_film_button)
    public void updatefilm() {
        if(updatejudul.getText().toString() == null){
            updatejudul.setError("Masukan judul film");
        }else if (updatesinopsis.getText().toString() == null){
            updatesinopsis.setError("Masukan sinopsis film");
        }else if (updateharga.getText().toString() == null){
            updateharga.setError("Masukan harga film");
        } else if (updatedurasi.getText().toString() == null) {
            updatedurasi.setError("Masukan durasi film");
        } else {
            saveData();
        }
    }
}
