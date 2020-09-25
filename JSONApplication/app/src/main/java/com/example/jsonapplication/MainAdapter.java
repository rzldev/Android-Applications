package com.example.jsonapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context context;
    private List<MainRequest> mainRequests;

    public MainAdapter(Context context, List<MainRequest> mainRequests) {
        this.context = context;
        this.mainRequests = mainRequests;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false);
        return new MainAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MainRequest mr = mainRequests.get(position);
        holder.id.setText("ID : " + String.valueOf(mr.getId()));
        holder.nama.setText("Item : " + String.valueOf(mr.getItem()));

        Log.d("myID", "onBindViewHolder: " + String.valueOf(mr.getId()));

    }

    @Override
    public int getItemCount() {
        return mainRequests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, nama;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.itemId);
            nama = itemView.findViewById(R.id.itemName);
        }
    }
}
