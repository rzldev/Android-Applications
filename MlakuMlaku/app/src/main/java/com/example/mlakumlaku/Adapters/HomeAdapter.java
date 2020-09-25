package com.example.mlakumlaku.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mlakumlaku.Activities.EventActivity;
import com.example.mlakumlaku.R;
import com.example.mlakumlaku.Requests.WisataRequest;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context context;
    private List<WisataRequest> wisataRequestList;

    public HomeAdapter(Context context, List<WisataRequest> wisataRequestList) {
        this.context = context;
        this.wisataRequestList = wisataRequestList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_home, parent, false);
        return new HomeAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WisataRequest wr = wisataRequestList.get(position);
        holder.title.setText(wr.getName());
        holder.events.setText("0 Events");
        holder.address.setText(wr.getAddress());
        Glide.with(context).load(wr.getImage()).into(holder.image);
        holder.id = wr.getId();
    }

    @Override
    public int getItemCount() {
        return wisataRequestList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, events, address;
        ImageView image;
        String id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.itemTitle);
            events = itemView.findViewById(R.id.itemEvents);
            address = itemView.findViewById(R.id.itemAddress);
            image = itemView.findViewById(R.id.itemImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, EventActivity.class);
                    intent.putExtra("id", id);
                    context.startActivity(intent);
                }
            });
        }
    }

    public void addNewData(List<WisataRequest> wisataRequestList) {
        this.wisataRequestList.addAll(wisataRequestList);
        notifyDataSetChanged();
    }

    public void clear() {
        int size = wisataRequestList.size();
        wisataRequestList.clear();
        notifyItemRangeRemoved(0, size);
    }
}
