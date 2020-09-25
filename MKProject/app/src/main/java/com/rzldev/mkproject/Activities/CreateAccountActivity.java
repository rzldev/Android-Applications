package com.rzldev.mkproject.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
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

public class CreateAccountActivity extends AppCompatActivity {

    @BindView(R.id.user1)
    EditText user;

    @BindView(R.id.pass1)
    AutoCompleteTextView pass;

    @BindView(R.id.pass_conf1)
    AutoCompleteTextView passconf;

    @BindView(R.id.telp1)
    EditText telpon;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://habibrohman046.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    ApiPackage apiPackage= retrofit.create(ApiPackage.class);

    String username;
    String password;
    String Status;
    int saldo;
    String telp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);

        Status = "u";
        saldo = 0;
    }

    private void saveData() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading ...");
        dialog.setCancelable(false);
        dialog.show();

        username = user.getText().toString();
        password = passconf.getText().toString();
        telp = telpon.getText().toString();

        Call<InsertUpdateDeleteRespond> call = apiPackage.insertakunrespond(username, password, Status, saldo, telp);

        call.enqueue(new Callback<InsertUpdateDeleteRespond>() {
            @Override
            public void onResponse(Call<InsertUpdateDeleteRespond> call, Response<InsertUpdateDeleteRespond> response) {
                if (response.isSuccessful()) {
                    dialog.hide();
                    Toast.makeText(CreateAccountActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    dialog.hide();
                    Toast.makeText(CreateAccountActivity.this, "Try it", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InsertUpdateDeleteRespond> call, Throwable t) {
                dialog.hide();
                Toast.makeText(CreateAccountActivity.this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.daftarbaru)
    public void daftarakun() {
        if (user.getText().toString() == null) {
            user.setError("Masukan username");
        } else if (pass.getText().toString() == null) {
            pass.setError("Masukan Password");
        } else if (passconf.getText().toString() == null) {
            passconf.setError("Masukan Confirmation Password");
        } else if (telpon.getText().toString() == null) {
            telpon.setError("Masukan Nomor Telepon");
        } else if(passconf.getText().toString() == pass.getText().toString()){
            passconf.setError("Ketikan uang saldo");
        } else {
            saveData();
        }
    }

}
