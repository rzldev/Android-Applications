package com.example.tugasmatakuliah;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MataKuliahAdapter extends RecyclerView.Adapter<MataKuliahAdapter.MyViewHolder>{

    private Context mContext;
    private ArrayList<MataKuliah> listMataKuliah = new ArrayList<>();

    public MataKuliahAdapter(Context mContext, ArrayList<MataKuliah> listMataKuliah) {
        this.mContext = mContext;
        this.listMataKuliah = listMataKuliah;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mata_kuliah, parent, false);
        return new MataKuliahAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MataKuliah mk = listMataKuliah.get(position);

        holder.Gambar.setImageResource(Integer.valueOf(mk.getGambar()));
        holder.Judul.setText(String.valueOf(mk.getMatakuliah()));
        holder.sks.setText(String.valueOf(mk.getSks()));
        holder.nilaiAkhir.setText(String.valueOf(mk.NilaiAKhir()));
        holder.grade.setText(mk.Grade((int) Double.parseDouble(holder.nilaiAkhir.getText().toString())));

    }

    @Override
    public int getItemCount() {
        return listMataKuliah.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView Gambar;
        TextView Judul, Genre, sks, nilaiAkhir, grade;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Gambar = itemView.findViewById(R.id.imgMatKul);
            Judul = itemView.findViewById(R.id.namaMatkul);
            sks = itemView.findViewById(R.id.sks);
            nilaiAkhir = itemView.findViewById(R.id.nilaiAkhir);
            grade = itemView.findViewById(R.id.grade);

        }
    }

}
