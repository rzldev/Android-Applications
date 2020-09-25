package com.example.b_gorlapangan.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.b_gorlapangan.R;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

public class RegisterOwnerActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMAGE_REQUEST = 1001;
    ConstraintLayout uploadKTP;
    TextInputEditText owner, ownerPhone;
    Button selanjutnya;
    ImageView imgKTP;

    String agensi, phone, username, email, password;
    Uri uriGambarProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_owner);

        initializing();
        myIntent();
        myClick();
    }

    private void myClick() {
        selanjutnya.setOnClickListener(this);
        uploadKTP.setOnClickListener(this);
    }

    private void initializing() {
        uploadKTP = findViewById(R.id.uploadKTP);
        owner = findViewById(R.id.owner);
        ownerPhone = findViewById(R.id.ownerPhone);
        selanjutnya = findViewById(R.id.btnRegister);
        imgKTP = findViewById(R.id.imgKTP);
    }

    private void myIntent() {
        Intent gi = getIntent();
        agensi = gi.getStringExtra("agensi");
        phone = gi.getStringExtra("phone");
        username = gi.getStringExtra("username");
        email = gi.getStringExtra("email");
        password = gi.getStringExtra("password");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.uploadKTP :
                chooseImage();
                break;
            case R.id.btnRegister :
                if (owner.getText().toString().isEmpty()) {
                    owner.setError("Masukan nama pemilik agensi");
                    owner.requestFocus();
                    return;
                }
                if (ownerPhone.getText().toString().isEmpty()) {
                    ownerPhone.setError("Masukan nomor telepon pemilik agensi");
                    ownerPhone.requestFocus();
                    return;
                }

                passData();
                break;
        }
    }

    private void passData() {
        Intent intent = new Intent(RegisterOwnerActivity.this, RegisterUsahaActivity.class);
        intent.putExtra("agensi", agensi);
        intent.putExtra("phone", phone);
        intent.putExtra("username", username);
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        intent.putExtra("owner", owner.getText().toString());
        intent.putExtra("ownerPhone", ownerPhone.getText().toString());
        intent.putExtra("ktp", String.valueOf(uriGambarProfil));
        startActivity(intent);
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
            uriGambarProfil = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriGambarProfil);
                imgKTP.setImageBitmap(bitmap);
                Toast.makeText(this, String.valueOf(uriGambarProfil), Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }

    }
}
