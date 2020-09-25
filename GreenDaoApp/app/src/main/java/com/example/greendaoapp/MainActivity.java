package com.example.greendaoapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements
        barangAdapter.barangAdapterFungsi, UpdateFragment.UpdateFragmentListener {

    private RecyclerView rv_barang;
    private DaoSession dbSession;
    private barangAdapter bAdapter;
    private List<barang> list_barang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Data Barang");
        dbSession = DbHandler.getInstance(MainActivity.this);
        rv_barang = findViewById(R.id.rv_barang);
        //Menampilkan Data ke Recycler
        list_barang = dbSession.getBarangDao().queryBuilder().list();
        bAdapter = new barangAdapter(list_barang,this);
        rv_barang.setLayoutManager(new LinearLayoutManager(this));
        rv_barang.setAdapter(bAdapter);
        bAdapter.notifyDataSetChanged();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TambahActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onUpdate(int position) {
        long id = list_barang.get(position).getIdBarang();
        String nama = list_barang.get(position).getNamaBarang();
        int harga = list_barang.get(position).getHargaBarang();

        FragmentManager fm = getSupportFragmentManager();
        UpdateFragment uf = UpdateFragment.newInstance(id, nama, harga);
        uf.show(fm, UpdateFragment.class.getSimpleName());
    }

    @Override
    public void onDelete(int position) {
        showDialogHapus(position, list_barang.get(position).getNamaBarang());
    }

    @Override
    public void requestUpdate(long id, String nama, int harga) {
        barang b = dbSession.getBarangDao().load(id);
        b.setNamaBarang(nama);
        b.setHargaBarang(harga);
        dbSession.getBarangDao().update(b);

        bAdapter.notifyDataSetChanged();
    }


    private void showDialogHapus(final int position, String nama) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Yakin ingin menghapus barang "+ nama + " ?");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Ya",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        /*
                        Fungsi delete suatu data bedasarkan idnya.
                         */
                        long idBrg = list_barang.get(position).getIdBarang();
                        dbSession.getBarangDao().deleteByKey(idBrg);

                        list_barang.remove(position);
                        bAdapter.notifyItemRemoved(position);
                        bAdapter.notifyItemRangeChanged(position, list_barang.size());

                        dialog.dismiss();
                    }
                });

        builder.setNegativeButton(
                "Tidak",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
