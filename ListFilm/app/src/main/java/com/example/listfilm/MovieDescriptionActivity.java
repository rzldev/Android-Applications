package com.example.listfilm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MovieDescriptionActivity extends AppCompatActivity {

    TextView movieTitle, movieYear, movieDirector, movieSynopsis;
    ImageView movieImage;

    String title, year, director, synopsis;
    byte[] image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_description);

        movieTitle = findViewById(R.id.movieTitle);
        movieYear = findViewById(R.id.movieYear);
        movieDirector = findViewById(R.id.movieDirector);
        movieSynopsis = findViewById(R.id.movieSynopsis);
        movieImage = findViewById(R.id.movieImage);

        Intent gIntent = getIntent();
        title = gIntent.getStringExtra("title");
        year = gIntent.getStringExtra("year");
        director = gIntent.getStringExtra("director");
        synopsis = gIntent.getStringExtra("synopsis");
        image = gIntent.getByteArrayExtra("image");

        movieTitle.setText(title);
        movieYear.setText(year);
        movieDirector.setText("Director : " + director);
        movieSynopsis.setText(synopsis);

        Bitmap bm = BitmapFactory.decodeByteArray(image, 0, image.length);
        movieImage.setImageBitmap(bm);
    }
}
