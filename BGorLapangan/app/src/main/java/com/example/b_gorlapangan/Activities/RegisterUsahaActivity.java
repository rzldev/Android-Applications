package com.example.b_gorlapangan.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.b_gorlapangan.API;
import com.example.b_gorlapangan.Models.Agency;
import com.example.b_gorlapangan.R;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterUsahaActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMAGE_REQUEST = 1001;
    String agensi, phone, username, email, password, owner, ownerPhone;
    Uri ktp;
    Uri ijinUsaha;
    Bitmap bitmap;

    TextInputEditText rekening;
    AppCompatSpinner bank;
    ConstraintLayout uploadSuratIjinUsaha;
    Button daftar;
    ImageView imgIjin;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_usaha);

        initializing();
        myIntent();
        mySpinner();
        myLog();
        myClick();
    }

    private void myClick() {
        uploadSuratIjinUsaha.setOnClickListener(this);
        daftar.setOnClickListener(this);
    }

    private void myLog() {
        Log.d("lapangan", "agensi:" + agensi);
        Log.d("lapangan", "phone:" + phone);
        Log.d("lapangan", "username:" + username);
        Log.d("lapangan", "email:" + email);
        Log.d("lapangan", "password:" + password);
        Log.d("lapangan", "owner:" + owner);
        Log.d("lapangan", "ownerPhone:" + ownerPhone);
        Log.d("lapangan", "ktp:" + ktp);
    }

    private void mySpinner() {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, R.layout.spinner_text);
        spinnerAdapter.add("Nama Bank");
        spinnerAdapter.addAll(getResources().getStringArray(R.array.namaBank));
        bank.setAdapter(spinnerAdapter);
    }

    private void initializing() {
        rekening = findViewById(R.id.rekening);
        bank = findViewById(R.id.bank);
        uploadSuratIjinUsaha = findViewById(R.id.uploadIjinUsaha);
        daftar = findViewById(R.id.btnDaftar);
        imgIjin = findViewById(R.id.imgIjin);
    }

    private void myIntent() {
        Intent gi = getIntent();
        agensi = gi.getStringExtra("agensi");
        phone = gi.getStringExtra("phone");
        username = gi.getStringExtra("username");
        email = gi.getStringExtra("email");
        password = gi.getStringExtra("password");
        owner = gi.getStringExtra("owner");
        ownerPhone = gi.getStringExtra("ownerPhone");
        ktp = Uri.parse(gi.getStringExtra("ktp"));
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_REQUEST && data != null && data.getData() != null) {
            ijinUsaha = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), ijinUsaha);
                imgIjin.setImageBitmap(bitmap);
                Toast.makeText(this, String.valueOf(ijinUsaha), Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.uploadIjinUsaha :
                chooseImage();
                break;
            case R.id.btnDaftar :
                insertData();
                break;
        }
    }

    private void insertData() {
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading ...");
        progress.show();

        String url = "https://booking-gor.lug-surabaya.com/api/agencies/";

        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        API api = retrofit.create(API.class);
//        Call<Agency> agencyCall = api.agency(
//                agensi,
//                phone,
//                email,
//                username,
//                password,
//                owner,
//                ownerPhone,
//                bank.getSelectedItem(),
//                rekening.getText().toString()
//
//        );

        Intent intent = new Intent(RegisterUsahaActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private File convIntoFile(Uri imageUri) {
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                System.currentTimeMillis() + "_image.webp");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.WEBP,0, baos);
        byte[] bitmapData = baos.toByteArray();

        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapData);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

}
