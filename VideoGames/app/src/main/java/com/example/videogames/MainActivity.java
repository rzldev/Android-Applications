package com.example.videogames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Toolbar toolbar;

    ArrayList<Game> games;
    MainAdapter gameAdapter;

    String[] gametitleArray;
    String[] gameDescArray;
    String[] gameDateArray;
    TypedArray gameImageArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializing();
        myToolbar();
        prepareData();
        setAdapter();
    }

    private void initializing() {
        recyclerView = findViewById(R.id.rvGames);
        toolbar = findViewById(R.id.toolbar);
        games = new ArrayList<>();
    }

    private void myToolbar() {
        setSupportActionBar(toolbar);
    }

    private void prepareData() {
        gametitleArray = getResources().getStringArray(R.array.gameTitles);
        gameDateArray = getResources().getStringArray(R.array.gameRealeseDate);
        gameDescArray = getResources().getStringArray(R.array.gameDescription);
        gameImageArray = getResources().obtainTypedArray(R.array.gameImage);

        for (int x = 0; x < gametitleArray.length; x++) {
            Game game = new Game();
            game.setTitle(gametitleArray[x]);
            game.setDate(gameDateArray[x]);
            game.setImage(Integer.parseInt(String.valueOf(gameImageArray.getResourceId(x, -1))));
            game.setDescription(gameDescArray[x]);
            String[] year = gameDateArray[x].split(", ");
            game.setYear(Integer.parseInt(year[1]));
            games.add(game);
        }
    }

    private void setAdapter() {
        gameAdapter = new MainAdapter(this, games);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(gameAdapter);
        gameAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void aboutMeActivity(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
        startActivity(intent);
    }
}
