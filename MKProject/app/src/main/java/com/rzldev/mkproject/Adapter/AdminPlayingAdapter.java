package com.rzldev.mkproject.Adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rzldev.mkproject.API.ApiPackage;
import com.rzldev.mkproject.Activities.AdminFilmUpdateActivity;
import com.rzldev.mkproject.Activities.FilmActivity;
import com.rzldev.mkproject.R;
import com.rzldev.mkproject.Responds.InsertUpdateDeleteRespond;
import com.rzldev.mkproject.Results.PlayingResult;

import java.util.List;

import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminPlayingAdapter extends RecyclerView.Adapter<AdminPlayingAdapter.ViewHolder>{

    private Context context;
    private List<PlayingResult> results;

    public AdminPlayingAdapter(Context context, List<PlayingResult> results) {
        this.context = context;
        this.results = results;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cview_film_admin, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(AdminPlayingAdapter.ViewHolder holder, int position) {
        PlayingResult result = results.get(position);

        holder.judul.setText(result.getJudul());
        holder.id_judul = result.getJudul();
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView judul;
        private ImageView deletefilm, updatefilm;
        String id_judul;

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://habibrohman046.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        ApiPackage apiPackage = retrofit.create(ApiPackage.class);


        public ViewHolder(View itemView) {
            super(itemView);

            judul = itemView.findViewById(R.id.judul_cview_admin);
            deletefilm = itemView.findViewById(R.id.cview_delete_film);
            updatefilm = itemView.findViewById(R.id.cview_update_film);
            deletefilm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialogDelete(id_judul);
                }
            });

            updatefilm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(context, AdminFilmUpdateActivity.class);

                }
            });

        }

        private void showDialogDelete(final String id_judul){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            // set title dialog
            alertDialogBuilder.setTitle("Hapus Film ?");

            // set pesan dari dialog
            alertDialogBuilder
                    .setMessage("Anda yakin Ingin Menghapus Film ?")
                    .setCancelable(false)
                    .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {

                            deleteFilm(id_judul);
                        }
                    })
                    .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialog.show();
        }

        public void deleteFilm(String id_judul) {

            final ProgressDialog dialog = new ProgressDialog(context);
            dialog.setMessage("Loading ...");
            dialog.setCancelable(false);
            dialog.show();

            Call<InsertUpdateDeleteRespond> call = apiPackage.deletefilmrespond(id_judul);

            call.enqueue(new Callback<InsertUpdateDeleteRespond>() {
                @Override
                public void onResponse(Call<InsertUpdateDeleteRespond> call, Response<InsertUpdateDeleteRespond> response) {
                    if (response.isSuccessful()) {
                        dialog.hide();
                        Toast.makeText(context, "Lapangan berhasil di hapus", Toast.LENGTH_SHORT).show();

                    } else {
                        dialog.hide();
                        Toast.makeText(context, "not Correct", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<InsertUpdateDeleteRespond> call, Throwable t) {
                    dialog.hide();
                }
            });

        }

    }

}
