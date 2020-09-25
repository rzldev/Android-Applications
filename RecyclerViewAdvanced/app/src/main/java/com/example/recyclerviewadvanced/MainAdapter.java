package com.example.recyclerviewadvanced;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Mantan> mantans;

    public MainAdapter(Context context, ArrayList<Mantan> mantans) {
        this.context = context;
        this.mantans = mantans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mantan, parent, false);
        return new MainAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Mantan m = mantans.get(position);

        holder.nama.setText(m.getNama());
        holder.lama.setText(String.valueOf(m.getLama()));
        holder.kenangan.setText(m.getKenangan());

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                myPopup(view);

                return false;
            }

            private void myPopup(View view) {
                PopupMenu menu = new PopupMenu(context, view);
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.actionUpdate :
                                updateItem();
                                break;
                            case R.id.actionDelete :
                                deleteItem();
                                break;
                        }

                        return false;
                    }
                });
                MenuInflater menuInflater = menu.getMenuInflater();
                menuInflater.inflate(R.menu.menu_update_delete, menu.getMenu());
                menu.show();
            }

            private void deleteItem() {
                new AlertDialog.Builder(context)
                        .setTitle("Delete")
                        .setMessage("Are you sure want to delete this item?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                mantans.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, mantans.size());
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .show();
            }

            private void updateItem() {
                View popupView = LayoutInflater.from(context).inflate(R.layout.form_update_data, null);
                final PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;

                popupWindow.setWidth(width - 100);

                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(false);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                popupWindow.showAtLocation(new RelativeLayout(context), Gravity.CENTER, -20, -20);

                showData(popupView, popupWindow);

            }

            private void showData(final View popupView, final PopupWindow popupWindow) {
                final Mantan m = mantans.get(position);

                final EditText nama, lama, kenangan;
                Button update;

                nama = popupView.findViewById(R.id.updateNama);
                lama = popupView.findViewById(R.id.updateLama);
                kenangan = popupView.findViewById(R.id.updateKenangan);
                update = popupView.findViewById(R.id.btnUpdate);

                nama.setText(m.getNama());
                lama.setText(String.valueOf(m.getLama()));
                kenangan.setText(m.getKenangan());

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        m.setNama(nama.getText().toString());
                        m.setLama(Integer.parseInt(lama.getText().toString()));
                        m.setKenangan(kenangan.getText().toString());

                        mantans.set(position, m);
                        notifyDataSetChanged();

                        popupWindow.dismiss();
                    }
                });
            }

        });
    }

    @Override
    public int getItemCount() {
        return mantans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama, lama, kenangan;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.outputNama);
            lama = itemView.findViewById(R.id.outputLama);
            kenangan = itemView.findViewById(R.id.outputKenangan);
            cardView = itemView.findViewById(R.id.itemMantan);

        }

    }



}
