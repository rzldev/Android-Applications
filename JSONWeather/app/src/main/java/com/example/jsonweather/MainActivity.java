package com.example.jsonweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    MainAdapter adapter;
    ArrayList<Main> mainList;
    List<Weather> weatherList;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializing();
        getData();
        myAdapter();
    }

    private void myAdapter() {
        adapter = new MainAdapter(this, mainList, weatherList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        adapter.notifyDataSetChanged();
    }

    private void initializing() {
        recyclerView = findViewById(R.id.mRecycler);
        mainList = new ArrayList<>();
        weatherList = new ArrayList<>();
    }

    private void getData() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");
        dialog.show();

        MyAPI api = retrofit.create(MyAPI.class);
        Call<MainRespond> mainRespondCall = api.MAIN_RESPOND_CALL();
        mainRespondCall.enqueue(new Callback<MainRespond>() {
            @Override
            public void onResponse(Call<MainRespond> call, Response<MainRespond> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    setData(response);
                    myAdapter();
                    weatherList = response.body().getWeather();
                } else {
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this, String.valueOf(response.body().getId()), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<MainRespond> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, String.valueOf(t.getMessage()), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setData(Response<MainRespond> response) {
        weatherList = response.body().getWeather();

        Main m = new Main();
        m.setFeelsLike(response.body().getMain().getFeelsLike());
        m.setHumidity(response.body().getMain().getHumidity());
        m.setPressure(response.body().getMain().getPressure());
        m.setTemp(response.body().getMain().getTemp());
        m.setTempMax(response.body().getMain().getTempMax());
        m.setTempMin(response.body().getMain().getTempMin());

        mainList.add(m);
    }
}
