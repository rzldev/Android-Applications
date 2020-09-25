package com.example.mlakumlaku.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mlakumlaku.Classes.Wisata;
import com.example.mlakumlaku.Databases.APIPackages;
import com.example.mlakumlaku.R;
import com.example.mlakumlaku.Requests.WisataRequest;
import com.example.mlakumlaku.Responses.WisataResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventActivity extends AppCompatActivity {

    ImageView image;
    TextView title;
    FloatingActionButton maps;
    Button createEvent;
    String id;
    List<WisataRequest> wisataRequestList;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.110/android/uas/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    APIPackages apiPackages = retrofit.create(APIPackages.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        myIntent();
        initialize();
        showData();
        myClick();
    }

    private void myClick() {
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EventActivity.this, MapsActivity.class);
                i.putExtra("id", id);
                startActivity(i);
            }
        });
    }

    private void showData() {
        final ProgressDialog dialog = new ProgressDialog(EventActivity.this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");
        dialog.show();

        Call<WisataResponse> wisataResponseCall = apiPackages.SINGLE_WISATA_RESPONSE_CALL(Integer.parseInt(id));
        wisataResponseCall.enqueue(new Callback<WisataResponse>() {
            @Override
            public void onResponse(Call<WisataResponse> call, Response<WisataResponse> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    wisataRequestList = response.body().getWisata();
                    WisataRequest wr = wisataRequestList.get(0);
                    Glide.with(EventActivity.this).load(wr.getImage()).into(image);
                    title.setText(wr.getName());
                }
            }

            @Override
            public void onFailure(Call<WisataResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(EventActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initialize() {
        image = findViewById(R.id.image);
        title = findViewById(R.id.title);
        maps = findViewById(R.id.maps);
        createEvent = findViewById(R.id.btnBuatEvent);
    }

    private void myIntent() {
        Intent gi = getIntent();
        id = gi.getStringExtra("id");
    }
}
