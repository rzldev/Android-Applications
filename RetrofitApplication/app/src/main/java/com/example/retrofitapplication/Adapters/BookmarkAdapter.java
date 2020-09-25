package com.example.retrofitapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitapplication.Classes.Mahasiswa;
import com.example.retrofitapplication.Classes.MainRequest;
import com.example.retrofitapplication.Databases.DAODbHelper;
import com.example.retrofitapplication.Databases.DaoAdapter;
import com.example.retrofitapplication.R;

import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.ViewHolder> {

    private Context context;
    private List<Mahasiswa> mahasiswaList;
    private DaoAdapter daoAdapter;

    public BookmarkAdapter(Context context, List<Mahasiswa> mahasiswaList) {
        this.context = context;
        this.mahasiswaList = mahasiswaList;
        daoAdapter = new DaoAdapter(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false);
        return new BookmarkAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Mahasiswa m = mahasiswaList.get(position);

        holder.nama.setText(m.getNama());
        holder.nim.setText(m.getNim());

        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFav(holder.fav, holder.nama, holder.nim);
            }
        });

        int check = daoAdapter.isMahasiswaExist(holder.nama.getText().toString());
        if (check == 1) {
            holder.fav.setChecked(true);
            holder.fav.setBackgroundResource(R.drawable.ic_favorite);
        } else {
            holder.fav.setChecked(false);
            holder.fav.setBackgroundResource(R.drawable.ic_favorite_border);
        }
    }

    @Override
    public int getItemCount() {
        return mahasiswaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama, nim;
        ToggleButton fav;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.itemNama);
            nim = itemView.findViewById(R.id.itemNIM);
            fav = itemView.findViewById(R.id.fav);
        }
    }

    public void addNewData(List<Mahasiswa> mahasiswaList) {
        this.mahasiswaList.addAll(mahasiswaList);
        notifyDataSetChanged();
    }

    public void clear() {
        int size = mahasiswaList.size();
        mahasiswaList.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void myFav(ToggleButton fav, TextView nama, TextView nim) {
        if (fav.isChecked()) {
            fav.setChecked(true);
            fav.setBackgroundResource(R.drawable.ic_favorite);
            daoAdapter.insertMahasiswa(nama.getText().toString(), nim.getText().toString());
        } else {
            fav.setChecked(false);
            fav.setBackgroundResource(R.drawable.ic_favorite_border);
            daoAdapter.deleteMahasiswa(nama.getText().toString());
            clear();
            daoAdapter.getMahasiswa(this);
        }
    }
}
