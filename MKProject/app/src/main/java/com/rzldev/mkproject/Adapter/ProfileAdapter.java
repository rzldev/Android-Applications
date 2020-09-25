package com.rzldev.mkproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rzldev.mkproject.Activities.FilmActivity;
import com.rzldev.mkproject.R;
import com.rzldev.mkproject.Results.PlayingResult;
import com.rzldev.mkproject.Results.ProfileResult;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder>{


    private Context context;
    private List<ProfileResult> profileResults;

    public ProfileAdapter(Context context, List<ProfileResult> profileResults) {
        this.context = context;
        this.profileResults = profileResults;
    }

    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cview_film, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ProfileAdapter.ViewHolder holder, int position) {
        ProfileResult profileResult = profileResults.get(position);

        holder.id_username = profileResult.getUsername();
        holder.saldo = profileResult.getSaldo();
        holder.telp = profileResult.getTelp();

    }

    @Override
    public int getItemCount() {
        return profileResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        String id_username, saldo, telp;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, FilmActivity.class);
            intent.putExtra("username", id_username);
            intent.putExtra("saldo", saldo);
            intent.putExtra("telp", telp);
            context.startActivity(intent);
        }
    }
}
