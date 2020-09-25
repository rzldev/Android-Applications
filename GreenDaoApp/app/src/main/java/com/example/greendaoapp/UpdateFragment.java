package com.example.greendaoapp;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends DialogFragment {

    EditText inputNama, inputHarga;
    Button simpan;

    private long idBarang;
    private String namaBarang;
    private int hargaBarang;
    private UpdateFragmentListener ufl;

    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update, container, false);

        if (getArguments() != null) {
            idBarang = getArguments().getLong("id");
            namaBarang = getArguments().getString("nama");
            hargaBarang = getArguments().getInt("harga");
        }

        inputNama = view.findViewById(R.id.et_nama);
        inputHarga = view.findViewById(R.id.et_harga);
        simpan = view.findViewById(R.id.btn_simpan);

        inputNama.setText(namaBarang);
        inputHarga.setText(String.valueOf(hargaBarang));

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myNama = inputNama.getText().toString();
                String myHarga = inputHarga.getText().toString();

                ufl.requestUpdate(idBarang, myNama, Integer.parseInt(myHarga));
                getDialog().dismiss();
            }
        });

        getDialog().setTitle("Ubah Data Barang");

        return view;
    }

    public interface UpdateFragmentListener {
        void requestUpdate(long id, String nama, int harga);
    }

    public static UpdateFragment newInstance(long id, String nama, int harga) {
        UpdateFragment uf = new UpdateFragment();
        Bundle b = new Bundle();
        b.putLong("id", id);
        b.putString("nama", nama);
        b.putInt("harga", harga);
        uf.setArguments(b);
        return uf;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        ufl = (UpdateFragmentListener) context;
    }
}
