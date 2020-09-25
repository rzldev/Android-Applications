package com.example.greendaoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class barangAdapter extends RecyclerView.Adapter<barangAdapter.MyViewHolder>{

    private List<barang> list_barang;
    private barangAdapterFungsi bAdapterFungsi;

    public barangAdapter(List<barang> list_barang, barangAdapterFungsi bAdapterFungsi) {
        this.list_barang = list_barang;
        this.bAdapterFungsi = bAdapterFungsi;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_barang,parent,false);
        return new barangAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        barang b = list_barang.get(position);
        holder.tv_nama.setText(b.getNamaBarang());
        Locale localID = new Locale("in","ID");
        NumberFormat formatRP = NumberFormat.getCurrencyInstance(localID);
        holder.tv_harga.setText(formatRP.format(b.getHargaBarang()));
    }

    @Override
    public int getItemCount() {
        return list_barang.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_nama,tv_harga;
        ImageView iv_hapus;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nama = itemView.findViewById(R.id.tv_nama);
            tv_harga = itemView.findViewById(R.id.tv_harga);
            iv_hapus = itemView.findViewById(R.id.iv_hapus);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    bAdapterFungsi.onUpdate(getAdapterPosition());
                    return false;
                }
            });
            iv_hapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bAdapterFungsi.onDelete(getAdapterPosition());
                }
            });
        }
    }

    public interface barangAdapterFungsi{
        void onUpdate(int position);
        void onDelete(int position);
    }
}
