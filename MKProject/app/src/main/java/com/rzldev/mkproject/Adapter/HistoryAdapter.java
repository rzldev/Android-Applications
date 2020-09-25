package com.rzldev.mkproject.Adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rzldev.mkproject.API.ApiPackage;
import com.rzldev.mkproject.R;
import com.rzldev.mkproject.Results.HistoryResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Context context;
    private List<HistoryResult> results;

    public HistoryAdapter(Context context, List<HistoryResult> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cview_history, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.ViewHolder holder, int position) {
        HistoryResult result = results.get(position);

        holder.judul.setText(result.getJudul());
        holder.tiketdibeli.setText(result.getTiketDibeli());
        holder.waktu.setText(result.getWaktu());
        holder.tanggal.setText(result.getTanggal());
        holder.username = result.getUsername();
        holder.total.setText(result.getTotal());
    }

    @Override
    public int getItemCount() { return results.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView judul, tiketdibeli, tanggal, waktu, total;
        String username;

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://habibrohman046.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        ApiPackage apiPackage = retrofit.create(ApiPackage.class);


        public ViewHolder(View itemView) {
            super(itemView);

            judul = itemView.findViewById(R.id.judul);
            tiketdibeli = itemView.findViewById(R.id.no);
            waktu = itemView.findViewById(R.id.waktu);
            tanggal = itemView.findViewById(R.id.tgl);
            total = itemView.findViewById(R.id.harga);
        };

    }
}
