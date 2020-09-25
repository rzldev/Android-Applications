package com.example.sqliteapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText name, pass, updateOld, updateNew, hapus;
    Button tambah, lihat, update, delete;
    MyDBAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.editName);
        pass = findViewById(R.id.editPass);
        updateOld = findViewById(R.id.editText3);
        updateNew = findViewById(R.id.editText5);
        hapus = findViewById(R.id.editText6);
        tambah = findViewById(R.id.button);
        lihat = findViewById(R.id.button2);
        update = findViewById(R.id.button3);
        delete = findViewById(R.id.button4);

        adapter = new MyDBAdapter(this);

        tambah.setOnClickListener(this);
        lihat.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button :
                String textName = name.getText().toString();
                String textPass = pass.getText().toString();

                if (textName.isEmpty() || textPass.isEmpty()) {
                    Info.Show(view.getContext(), "Masukan username dan password");

                } else {
                    long id = adapter.insertData(textName, textPass);

                    if (id <= 0) {
                        Info.Show(view.getContext(), "Gagal tambah Data");
                        name.setText("");
                        pass.setText("");

                    } else {
                        Info.Show(view.getContext(), "Sukses tambah Data");
                        name.setText("");
                        pass.setText("");

                    }
                }

                break;
            case R.id.button2 :
                String data = adapter.getData();
                if (data == null) {
                    Info.Show(view.getContext(), "No Data");
                } else {
                    Info.Show(view.getContext(), data);
                }
                break;

            case R.id.button3 :
                String textNew = updateNew.getText().toString();
                String textOld = updateOld.getText().toString();

                if (textNew.isEmpty() || textOld.isEmpty()) {
                    Info.Show(view.getContext(), "Masukan nama user lama dan baru");

                } else {
                    long id = adapter.updateName(textNew, textOld);

                    if (id <= 0) {
                        Info.Show(view.getContext(), "Gagal Update");
                        updateOld.setText("");
                        updateNew.setText("");
                    } else {
                        Info.Show(view.getContext(), "Sukses Update");
                        updateOld.setText("");
                        updateNew.setText("");
                    }
                }

                break;

            case R.id.button4 :
                String t1 = hapus.getText().toString();

                if (t1.isEmpty()) {
                    Info.Show(view.getContext(), "Masukan nama user lama dan baru");

                } else {
                    long id = adapter.delete(t1);

                    if (id <= 0) {
                        Info.Show(view.getContext(), "Gagal Update");
                        hapus.setText("");
                        hapus.setText("");
                    } else {
                        Info.Show(view.getContext(), "Sukses Update");
                        hapus.setText("");
                        hapus.setText("");
                    }
                }

                break;
        }
    }
}
