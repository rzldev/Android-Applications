package com.rzldev.mkproject.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.rzldev.mkproject.API.ApiPackage;
import com.rzldev.mkproject.R;
import com.rzldev.mkproject.Responds.LoginRespond;
import com.rzldev.mkproject.Results.LoginResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.user1)
    EditText user;

    @BindView(R.id.pass1)
    EditText pass;

    String telp;
    String saldo;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://habibrohman046.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    ApiPackage apiPackage = retrofit.create(ApiPackage.class);

    private List<LoginResult> loginResults = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    private void loginUser() {

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading ...");
        dialog.setCancelable(false);
        dialog.show();

        Call<LoginRespond> call = apiPackage.loginRespond(user.getText().toString(), pass.getText().toString());

        call.enqueue(new Callback<LoginRespond>() {
            @Override
            public void onResponse(Call<LoginRespond> call, Response<LoginRespond> response) {
                if (response.body().getValue().equals(1)) {
;
                    if(response.body().getStatus().equals("a")){
                        dialog.hide();
                        telp = response.body().getTelp().toString();
                        saldo = response.body().getSaldo().toString();

                        Intent intent = new Intent(LoginActivity.this, AdminMainActivity.class);
//                        intent.putExtra("username", user.getText().toString());
//                        intent.putExtra("saldo", saldo);
//                        intent.putExtra("telp", telp);
                        startActivity(intent);

                        SharedPreferences sharedPreferences = getSharedPreferences("usersaldo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", user.getText().toString());
                        editor.putString("saldo", saldo);
                        editor.putString("telp", telp);
                        editor.apply();
                        finish();

                    } else if(response.body().getStatus().equals("u")){
                        telp = response.body().getTelp().toString();
                        saldo = response.body().getSaldo().toString();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                        intent.putExtra("username", user.getText().toString());
//                        intent.putExtra("saldo", saldo);
//                        intent.putExtra("telp", telp);
                        startActivity(intent);

                        SharedPreferences sharedPreferences = getSharedPreferences("usersaldo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", user.getText().toString());
                        editor.putString("saldo", saldo);
                        editor.putString("telp", telp);
                        editor.apply();
                        finish();
                    }

                } else {
                    dialog.hide();
                    Toast.makeText(LoginActivity.this, "Username/Password Salah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginRespond> call, Throwable t) {
                dialog.hide();
                Toast.makeText(LoginActivity.this, "Try it", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.masuk)
    public void login(){
        if(user.getText().toString().length() == 0){
            user.setError("Masukkan Username");
        } else if (pass.getText().toString().length() == 0) {
            pass.setError("Masukkan Password");
        } else {
            loginUser();
        }
    }

    @OnClick(R.id.daftar)
    public void daftar(){
        Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
        startActivity(intent);
    }

}
