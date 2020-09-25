package com.example.videogames;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class GameDetailActivity extends AppCompatActivity {

    ImageView gameImage;
    TextView gameTitle, gameDate, gameDesc;
    Toolbar toolbar;

    String[] gameTitleArray;
    TypedArray gameImageArray;
    String getGameTitle, getGameDate, getGameDesc;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        initializing();
        getData();
        showData();
        myActionBar();

    }

    private void myActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initializing() {
        gameImage = findViewById(R.id.gameImage);
        gameTitle = findViewById(R.id.gameTitle);
        gameDate = findViewById(R.id.gameDate);
        gameDesc = findViewById(R.id.gameDesc);
        toolbar = findViewById(R.id.toolbar);

        gameTitleArray = getResources().getStringArray(R.array.gameTitles);
        gameImageArray = getResources().obtainTypedArray(R.array.gameImage);
    }

    private void getData() {
        Intent intent = getIntent();
        Bundle getBundle = intent.getBundleExtra("bundle");
        getGameTitle = getBundle.getString("gameTitle");
        getGameDate = getBundle.getString("gameDate");
        getGameDesc = getBundle.getString("gameDesc");
    }

    private void showData() {
        gameTitle.setText(getGameTitle);
        gameDate.setText(getGameDate);
        gameDesc.setText(getGameDesc);

        int position = 0;
        for (int x = 0; x < gameTitleArray.length; x++) {
            if (gameTitleArray[x].equals(getGameTitle)) {
                position = x;
                break;
            }
        }

        gameImage.setImageResource(Integer.parseInt(String.valueOf(gameImageArray.getResourceId(position, -1))));
    }

}
