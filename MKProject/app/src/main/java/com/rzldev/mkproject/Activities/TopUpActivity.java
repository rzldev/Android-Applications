package com.rzldev.mkproject.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rzldev.mkproject.API.ApiPackage;
import com.rzldev.mkproject.Fragments.ProfileFragment;
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

public class TopUpActivity extends AppCompatActivity {

    @BindView(R.id.topupET)
    EditText inputtopup;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://habibrohman046.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    ApiPackage apiPackage= retrofit.create(ApiPackage.class);

    String id_username;
    String saldo;
    String telp;

    String inputsaldo;
    int inputsaldoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        id_username = intent.getStringExtra("id_username");
        saldo = intent.getStringExtra("saldo");
        telp = intent.getStringExtra("telp");
        Log.d("id_usename", id_username);

        TextView username = (TextView) findViewById(R.id.username_topup);
        username.setText(id_username);
    }

    private void saveData() {

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading ...");
        dialog.setCancelable(false);
        dialog.show();

        Log.d("qwe1234",saldo);
        inputsaldo = inputtopup.getText().toString();
        inputsaldoint = Integer.parseInt(inputsaldo);
        final int topup = (inputsaldoint+Integer.parseInt(saldo));
        final String topupstring = Integer.toString(topup);

        Log.d("asdasd", String.valueOf(inputsaldo));

        Call<InsertUpdateDeleteRespond> call = apiPackage.updatesaldorespond(id_username, topup);

        call.enqueue(new Callback<InsertUpdateDeleteRespond>() {
            @Override
            public void onResponse(Call<InsertUpdateDeleteRespond> call, Response<InsertUpdateDeleteRespond> response) {
                if (response.isSuccessful()) {
                    dialog.hide();
                    Toast.makeText(TopUpActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TopUpActivity.this, MainActivity.class);
/*                    intent.putExtra("username", id_username);
                    intent.putExtra("saldo", topupstring);
                    intent.putExtra("telp", telp);*/
                    startActivity(intent);

                    SharedPreferences sharedPreferences = getSharedPreferences("usersaldo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", id_username);
                    editor.putString("saldo", String.valueOf(topup));
                    editor.apply();
                } else {
                    dialog.hide();
                    Toast.makeText(TopUpActivity.this, "Try it", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InsertUpdateDeleteRespond> call, Throwable t) {
                dialog.hide();
                Toast.makeText(TopUpActivity.this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btn_topup)
    public void topupsaldo() {
        if(inputtopup.getText().toString().length() == 0){
            inputtopup.setError("Masukkan Saldo");
        } else {
            saveData();
        }
    }
}
