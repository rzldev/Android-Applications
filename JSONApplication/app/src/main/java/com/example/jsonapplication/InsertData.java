package com.example.jsonapplication;

import android.content.Context;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertData {

    private Context context;

    public InsertData(Context context) {
        this.context = context;
    }

    public void insertIntoDB(API api, String itemName) {
        Call<InsertResponse> insertResponseCall = api.INSERT_RESPONSE_CALL(itemName);
        insertResponseCall.enqueue(new Callback<InsertResponse>() {
            @Override
            public void onResponse(Call<InsertResponse> call, Response<InsertResponse> response) {
                if (response.isSuccessful()) {
                    Integer message = response.body().getSuccess();
                    Toast.makeText(context, String.valueOf(message), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InsertResponse> call, Throwable t) {

            }
        });
    }

}
