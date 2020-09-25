package com.example.jsonweather;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Main> mainList;
    private List<Weather> weatherList;

    public MainAdapter(Context context, ArrayList<Main> mainList, List<Weather> weatherList) {
        this.context = context;
        this.mainList = mainList;
        this.weatherList = weatherList;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false);

        return new MainAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        Weather w = weatherList.get(position);
        Main m = mainList.get(position);

        holder.cuaca.setText("Cuaca : " + String.valueOf(w.getMain()));
        holder.deskripsi.setText("Deskripsi : " + String.valueOf(w.getDescription()));
        holder.min.setText("Suhu min : " + String.valueOf(m.getTempMin()));
        holder.max.setText("Suhu max : " + String.valueOf(m.getTempMax()));
        holder.kelembapan.setText("Kelembapan : " + String.valueOf(m.getHumidity()));
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cuaca, deskripsi, min, max, kelembapan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cuaca = itemView.findViewById(R.id.itemCuaca);
            deskripsi = itemView.findViewById(R.id.itemDeskripsi);
            min = itemView.findViewById(R.id.itemSuhuMin);
            max = itemView.findViewById(R.id.itemSuhuMax);
            kelembapan = itemView.findViewById(R.id.itemKelembapan);
        }
    }
}
