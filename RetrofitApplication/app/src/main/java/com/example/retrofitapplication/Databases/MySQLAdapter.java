package com.example.retrofitapplication.Databases;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.example.retrofitapplication.Adapters.MainAdapter;
import com.example.retrofitapplication.Classes.MainRequest;
import com.example.retrofitapplication.Classes.MainResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MySQLAdapter {

    private Context context;
    private APIPakcages apiPakcages;
    List<MainRequest> mainRequestList = new ArrayList<>();

    public MySQLAdapter(Context context, Retrofit retrofit) {
        this.context = context;
        apiPakcages = retrofit.create(APIPakcages.class);
    }

    public void getData(final MainAdapter adapter) {
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");
        dialog.show();

        Call<MainResponse> mainResponseCall = apiPakcages.MAIN_RESPONSE_CALL();
        mainResponseCall.enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                dialog.dismiss();
                mainRequestList = response.body().getMahasiswa();

                adapter.clear();
                adapter.addNewData(mainRequestList);
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}
