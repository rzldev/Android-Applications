package com.example.mypahlawan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PahlawanAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Pahlawan> pahlawan = new ArrayList<>();

    public PahlawanAdapter(Context context, ArrayList<Pahlawan> pahlawan) {
        this.context = context;
        this.pahlawan = pahlawan;
    }

    @Override
    public int getCount() {
        return pahlawan.size();
    }

    @Override
    public Object getItem(int position) {
        return pahlawan.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.pahlawan_item, parent, false);
        }

        ViewHolder viewHolder = new ViewHolder(convertView);
        Pahlawan myPahlawan = (Pahlawan) getItem(position);
        viewHolder.bind(myPahlawan);
        return convertView;
    }

    private class ViewHolder {
        private TextView namaPahlawan, deskripsiPahlawan;
        private ImageView fotoPahlawan;

        ViewHolder(View v){
            namaPahlawan = v.findViewById(R.id.itemName);
            deskripsiPahlawan = v.findViewById(R.id.itemDescription);
            fotoPahlawan = v.findViewById(R.id.itemImage);
        }

        public void bind(Pahlawan myPahlawan) {
            namaPahlawan.setText(myPahlawan.getName());
            deskripsiPahlawan.setText(myPahlawan.getDescription());
            fotoPahlawan.setImageResource(myPahlawan.getPhoto());
        }
    }
}
