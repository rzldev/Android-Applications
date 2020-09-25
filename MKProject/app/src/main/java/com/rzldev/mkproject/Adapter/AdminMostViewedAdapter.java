package com.rzldev.mkproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rzldev.mkproject.Activities.AdminFilmInfoActivity;
import com.rzldev.mkproject.Activities.FilmActivity;
import com.rzldev.mkproject.Fragments.AdminMostViewedFragment;
import com.rzldev.mkproject.R;
import com.rzldev.mkproject.Results.MostViewedResult;

import java.util.List;

import butterknife.OnClick;

public class AdminMostViewedAdapter extends RecyclerView.Adapter<AdminMostViewedAdapter.ViewHolder> {

    private Context context;
    private List<MostViewedResult> results;

    public AdminMostViewedAdapter(Context context, List<MostViewedResult> results) {
        this.context = context;
        this.results = results;

    }

    @Override
    public AdminMostViewedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_most_viewed_admin, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(AdminMostViewedAdapter.ViewHolder holder, int position) {
        MostViewedResult result = results.get(position);

        holder.judul.setText(result.getJudul());
        holder.tiket_dibeli.setText(result.getTotalTiket());
        holder.id_judul = result.getJudul();
        holder.total_tiket = result.getTotalTiket();
        holder.total_pendapatan = result.getTotalPendapatan();

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView judul, tiket_dibeli;
        String id_judul, total_tiket, total_pendapatan;


        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            judul = itemView.findViewById(R.id.judulmv_admin);
            tiket_dibeli = itemView.findViewById(R.id.tiket);
        }

        @OnClick
        public void onClick(View v) {
            Intent intent = new Intent(context, AdminFilmInfoActivity.class);
            intent.putExtra("id_judul",id_judul);
            intent.putExtra("total_tiket", total_tiket);
            intent.putExtra("total_pendapatan", total_pendapatan);
            context.startActivity(intent);
        }
    }
}
