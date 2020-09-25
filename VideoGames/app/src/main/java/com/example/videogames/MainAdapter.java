package com.example.videogames;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Game> gamesArray = new ArrayList<>();

    public MainAdapter(Context context, ArrayList<Game> gamesArray) {
        this.context = context;
        this.gamesArray = gamesArray;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_games, parent, false);
        return new MainAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Game game = gamesArray.get(position);

        holder.gameTitle.setText(game.getTitle());
        holder.gameYear.setText(String.valueOf(game.getYear()));
        holder.gameImage.setImageResource(game.getImage());
        holder.gameDescription = game.getDescription();
        holder.gameRealeseDate = game.getDate();
    }

    @Override
    public int getItemCount() {
        return gamesArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView gameTitle, gameYear;
        ImageView gameImage;
        String gameRealeseDate, gameDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gameTitle = itemView.findViewById(R.id.gameTitle);
            gameYear = itemView.findViewById(R.id.gameYear);
            gameImage = itemView.findViewById(R.id.gameImage);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            bundle.putString("gameTitle", gameTitle.getText().toString());
            bundle.putString("gameDate", gameRealeseDate);
            bundle.putString("gameDesc", gameDescription);

            Intent intent = new Intent(context, GameDetailActivity.class);
            intent.putExtra("bundle", bundle);
            context.startActivity(intent);
        }
    }
}
