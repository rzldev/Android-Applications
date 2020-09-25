package com.example.sqliteapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Barang> barang = new ArrayList<>();

    public MainAdapter(Context context, ArrayList<Barang> barang) {
        this.context = context;
        this.barang = barang;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_barang, parent, false);
        return new MainAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        Barang b = barang.get(position);

        holder.txtBarang.setText(b.getBarang());
        holder.txtHarga.setText(b.getHarga());
    }

    @Override
    public int getItemCount() {
        return barang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtBarang, txtHarga;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtBarang = itemView.findViewById(R.id.ouputNama);
            txtHarga = itemView.findViewById(R.id.ouputHarga);
        }
    }
}
