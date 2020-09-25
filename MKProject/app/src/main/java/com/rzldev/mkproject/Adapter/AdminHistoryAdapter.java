package com.rzldev.mkproject.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rzldev.mkproject.API.ApiPackage;
import com.rzldev.mkproject.R;
import com.rzldev.mkproject.Results.AdminHistoryResult;
import com.rzldev.mkproject.Results.HistoryResult;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminHistoryAdapter extends RecyclerView.Adapter<AdminHistoryAdapter.ViewHolder> {

    private Context context;
    private List<AdminHistoryResult> results;

    public AdminHistoryAdapter(Context context, List<AdminHistoryResult> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public AdminHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cview_history_admin, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(AdminHistoryAdapter.ViewHolder holder, int position) {
        AdminHistoryResult result = results.get(position);

        holder.judul.setText(result.getJudul());
        holder.tiketdibeli.setText(result.getTiketDibeli());
        holder.waktu.setText(result.getWaktu());
        holder.tanggal.setText(result.getTanggal());
        holder.total.setText(result.getTotal());
        holder.username.setText(result.getUsername());
        holder.id_username = result.getUsername();
    }

    @Override
    public int getItemCount() { return results.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView judul, tiketdibeli, tanggal, waktu, total, username;
        String id_username;

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://habibrohman046.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        ApiPackage apiPackage = retrofit.create(ApiPackage.class);


        public ViewHolder(View itemView) {
            super(itemView);

            judul = itemView.findViewById(R.id.judul_history_admin);
            tiketdibeli = itemView.findViewById(R.id.no_history_admin);
            waktu = itemView.findViewById(R.id.waktu_history_admin);
            tanggal = itemView.findViewById(R.id.tgl_history_admin);
            total = itemView.findViewById(R.id.harga_history_admin);
            username = itemView.findViewById(R.id.username_user);
        };

    }

}
