package com.example.myaccountmanagement;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Account> accounts;

    public SecondAdapter(Context context, ArrayList<Account> accounts) {
        this.context = context;
        this.accounts = accounts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_account, parent,false);

        return new SecondAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Account a = accounts.get(position);

        holder.nama.setText(a.getNama());
        holder.email.setText(a.getEmail());
        holder.id = a.getId();
        holder.pass = a.getPassword();
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        TextView nama, email;
        String id, pass;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.accountName);
            email = itemView.findViewById(R.id.accountEmail);

            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            Intent intent = new Intent(context, DetailAccountActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("nama", nama.getText().toString());
            intent.putExtra("email", email.getText().toString());
            intent.putExtra("password", pass);
            context.startActivity(intent);

            return false;
        }
    }
}
