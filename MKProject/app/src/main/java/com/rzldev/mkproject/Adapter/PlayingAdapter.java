package com.rzldev.mkproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rzldev.mkproject.Activities.FilmActivity;
import com.rzldev.mkproject.R;
import com.rzldev.mkproject.Results.PlayingResult;

import java.util.List;

public class PlayingAdapter extends RecyclerView.Adapter<PlayingAdapter.ViewHolder> {

    private Context context;
    private List<PlayingResult> results;

    String id_username;

    public PlayingAdapter(Context context, List<PlayingResult> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cview_film, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(PlayingAdapter.ViewHolder holder, int position) {
        PlayingResult result = results.get(position);

        holder.judul.setText(result.getJudul());
        holder.id_judul = result.getJudul();
        holder.durasi = result.getDurasi();
        holder.harga = result.getHarga();
        holder.sinopsis = result.getSinopsis();

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView judul;
        String id_judul, durasi, harga, sinopsis;
        String id_username;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            judul = itemView.findViewById(R.id.judul_cview);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, FilmActivity.class);
            intent.putExtra("id_judul", id_judul);
            intent.putExtra("durasi", durasi);
            intent.putExtra("harga", harga);
            intent.putExtra("sinopsis", sinopsis);
            intent.putExtra("id_username", id_username);
            context.startActivity(intent);
        }
    }
}
