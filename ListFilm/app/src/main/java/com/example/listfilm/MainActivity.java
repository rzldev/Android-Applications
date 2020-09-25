package com.example.listfilm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    private Adapter mAdapter;

    private String[] movieTitle, movieYear, movieSynopsis, movieDirector;
    private TypedArray movieImage;

    private ArrayList<Movie> movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();

        listView = findViewById(R.id.listView);
        mAdapter = new MovieAdapter(this, movie);
        listView.setAdapter((ListAdapter) mAdapter);
    }

    private void loadData() {
        movieTitle = getResources().getStringArray(R.array.MovieTitle);
        movieYear = getResources().getStringArray(R.array.MovieYear);
        movieSynopsis = getResources().getStringArray(R.array.MovieSynopsis);
        movieDirector = getResources().getStringArray(R.array.MovieDirector);
        movieImage = getResources().obtainTypedArray(R.array.MovieImage);

        movie = new ArrayList<>();

        for (int x = 0; x < movieTitle.length; x++) {
            Movie m = new Movie();
            m.setMovieTitle(movieTitle[x]);
            m.setMovieYear(Integer.parseInt(movieYear[x]));
            m.setMovieSynopsis(movieSynopsis[x]);
            m.setMovieImage(movieImage.getResourceId(x, -1));
            m.setMovieDirector(movieDirector[x]);
            movie.add(m);
        }
    }

}
