package com.example.retrofitapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitapplication.Classes.MainRequest;
import com.example.retrofitapplication.Databases.DaoAdapter;
import com.example.retrofitapplication.R;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private Context context;
    private List<MainRequest> mainRequestList;
    private DaoAdapter daoAdapter;

    public MainAdapter(Context context, List<MainRequest> mainRequestList) {
        this.context = context;
        this.mainRequestList = mainRequestList;
        this.daoAdapter = new DaoAdapter(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false);
        return new MainAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        MainRequest mr = mainRequestList.get(position);

        holder.nama.setText(mr.getNama());
        holder.nim.setText(", " + mr.getNim());

        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFav(holder.favorite, holder.nama, holder.nim);
            }
        });

        int check = daoAdapter.isMahasiswaExist(holder.nama.getText().toString());
        if (check == 1) {
            holder.favorite.setChecked(true);
            holder.favorite.setBackgroundResource(R.drawable.ic_favorite);
        } else {
            holder.favorite.setChecked(false);
            holder.favorite.setBackgroundResource(R.drawable.ic_favorite_border);
        }
    }

    @Override
    public int getItemCount() {
        return mainRequestList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nama, nim;
        ToggleButton favorite;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.itemNama);
            nim = itemView.findViewById(R.id.itemNIM);
            favorite = itemView.findViewById(R.id.fav);

        }
    }

    public void addNewData(List<MainRequest> mainRequestList) {
        this.mainRequestList.addAll(mainRequestList);
        notifyDataSetChanged();
    }

    public void clear() {
        int size = mainRequestList.size();
        mainRequestList.clear();
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
        }
    }

}
