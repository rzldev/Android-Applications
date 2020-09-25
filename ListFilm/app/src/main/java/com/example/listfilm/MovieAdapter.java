package com.example.listfilm;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {

    private Context context;
    private ArrayList listMovie = new ArrayList();

    public MovieAdapter(Context context, ArrayList listMovie) {
        this.context = context;
        this.listMovie = listMovie;
    }

    @Override
    public int getCount() {
        return listMovie.size();
    }

    @Override
    public Object getItem(int i) {
        return listMovie.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_card_film, viewGroup, false);
        }

        ViewHolder viewHolder = new ViewHolder(view);
        Movie m = (Movie) getItem(i);
        viewHolder.bind(m);

        return view;
    }

    private class ViewHolder implements View.OnClickListener {
        private TextView title, year;
        private ImageView image;
        private String synopsis, director;

        public ViewHolder(View view) {
            title = view.findViewById(R.id.itemTitle);
            year = view.findViewById(R.id.itemYear);
            image = view.findViewById(R.id.itemImage);

            view.setOnClickListener(this);
        }

        public void bind(Movie m) {
            title.setText(m.getMovieTitle());
            year.setText(String.valueOf(m.getMovieYear()));
            image.setImageResource(m.getMovieImage());
            synopsis = m.getMovieSynopsis();
            director = m.getMovieDirector();
        }

        @Override
        public void onClick(View view) {
            String iTitle = title.getText().toString();
            String iYear = year.getText().toString();
            String iSynopsis = synopsis;

            Drawable drawable = image.getDrawable();
            Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();

            Intent i = new Intent(context, MovieDescriptionActivity.class);
            i.putExtra("title", iTitle);
            i.putExtra("year", iYear);
            i.putExtra("director", director);
            i.putExtra("synopsis", iSynopsis);
            i.putExtra("image", b);

            view.getContext().startActivity(i);

        }
    }
}
