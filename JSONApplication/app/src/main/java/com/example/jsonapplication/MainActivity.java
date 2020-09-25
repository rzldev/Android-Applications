package com.example.jsonapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.43.78:80/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    API api = retrofit.create(API.class);

    RecyclerView recyclerView;
    ImageButton btnAdd;

    MainAdapter adapter;
    List<MainRequest> mainRequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializing();
        getData();
        myAdapter();
        myClick();
    }

    private void myAdapter() {
        adapter = new MainAdapter(this, mainRequests);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initializing() {
        recyclerView = findViewById(R.id.mRecycler);
        btnAdd = findViewById(R.id.btnAdd);

        mainRequests = new ArrayList<>();
    }

    private void getData() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");
        dialog.show();

        Call<MainResponse> mainResponseCall = api.MAIN_RESPONSE_CALL();
        mainResponseCall.enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if (response.isSuccessful()) {
                    mainRequests = response.body().getAndroidapi();
                    for (int x = 0; x < mainRequests.size(); x++) {
//                        Toast.makeText(MainActivity.this, String.valueOf(mainRequests.get(x).getId()), Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();

                    myAdapter();

                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, String.valueOf(t.getMessage()), Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
    }

    private void insertData() {
        final View popupView = LayoutInflater.from(this).inflate(R.layout.popup_insert, null);
        final PopupWindow popupWindow = new PopupWindow(popupView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);


        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;

        popupWindow.setHeight(height - 200);

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.showAtLocation(new RelativeLayout(this), Gravity.CENTER, 0, 0);

        final EditText getItemName;
        final Button insert;
        getItemName = popupView.findViewById(R.id.itemName);
        insert = popupView.findViewById(R.id.btnInsert);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getItemName.getText().toString().isEmpty()) {
                    getItemName.setError("Kosong");
                    getItemName.requestFocus();
                    return;
                }

                InsertData i = new InsertData(MainActivity.this);
                i.insertIntoDB(api, getItemName.getText().toString());
                popupWindow.dismiss();
                finish();
                startActivity(getIntent());
            }
        });

    }

    private void myClick() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
    }
}
