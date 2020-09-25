package com.example.personalaccounting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Accounting> accounting;

    int position;
    Accounting acc;
    int sumNominal;

    public MainAdapter(Context context, ArrayList<Accounting> accounting) {
        this.context = context;
        this.accounting = accounting;
    }

    @Override
    public int getCount() {
        return accounting.size();
    }

    @Override
    public Object getItem(int i) {
        return accounting.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_accounting, viewGroup, false);
        }

        ViewHolder viewHolder = new ViewHolder(view);
        acc = (Accounting) getItem(i);
        viewHolder.bind(acc, i);
        return view;
    }

    private class ViewHolder {
        EditText jenis, nominal, tanggal, uraian;
        Button delete;
        public ViewHolder(View view) {
            jenis = view.findViewById(R.id.outputJenis);
            nominal = view.findViewById(R.id.outputNominal);
            tanggal = view.findViewById(R.id.outputTanggal);
            delete = view.findViewById(R.id.btnDelete);
            uraian = view.findViewById(R.id.outputUraian);
        }

        public void bind(Accounting acc, int i) {
            jenis.setText(acc.getJenis());
            nominal.setText(String.valueOf(acc.getNominal()));
            tanggal.setText(acc.getTanggal());
            uraian.setText(acc.getUraian());

            jenis.setEnabled(false);
            nominal.setEnabled(false);
            tanggal.setEnabled(false);;
            uraian.setEnabled(false);

            final int position = i;

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    accounting.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

    }

}
