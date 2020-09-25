package com.example.mlakumlaku.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mlakumlaku.Adapters.RetrofitAdapter;
import com.example.mlakumlaku.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class BioActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1001;
    EditText birthDate, address, facebook, instagram;
    CircleImageView photoProfile;
    Button signup;

    RetrofitAdapter ra;

    String username, email, password;
    long time;
    Uri gambarUri;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio);

        getPassedData();
        initialize();
        myClick();
    }

    private void myClick() {
        photoProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getBirthDate = birthDate.getText().toString().trim();
                String getAddress = address.getText().toString().trim();
                String getFacebook = facebook.getText().toString().trim();
                String getInstagram = instagram.getText().toString().trim();

                if (getBirthDate.isEmpty()) {
                    birthDate.setError("Masukan Tanggal Lahir");
                    birthDate.requestFocus();
                    return;
                }
                if (getAddress.isEmpty()) {
                    address.setError("Masukan Alamat");
                    address.requestFocus();
                    return;
                }
                if (getFacebook.isEmpty()) {
                    facebook.setError("Masukan Facebook");
                    facebook.requestFocus();
                    return;
                }
                if (getInstagram.isEmpty()) {
                    instagram.setError("Masukan Instagram");
                    instagram.requestFocus();
                    return;
                }
                try {
                    BitmapDrawable drawable = (BitmapDrawable) photoProfile.getDrawable();
                    Bitmap prfl = drawable.getBitmap();
                    Bitmap bg = BitmapFactory.decodeResource(getApplication().getResources(), R.drawable.cookies);

                    time = System.currentTimeMillis();
                    String stringProfileImg = "profile_img_" + String.valueOf(time);
                    String bitmapProfileImg = imageToString(prfl);
                    String stringBgImg = "bg_img_" + String.valueOf(time);
                    String bitmapBgImg = imageToString(bg);

                    ra.register(username, email, password, getBirthDate, getAddress, getFacebook, getInstagram, 0, stringProfileImg, bitmapProfileImg, stringBgImg, bitmapBgImg);
                } catch(Exception e) {
                    Toast.makeText(BioActivity.this, "Masukan Foto", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initialize() {
        birthDate = findViewById(R.id.iDate);
        address = findViewById(R.id.iAddress);
        facebook = findViewById(R.id.iFacebook);
        instagram = findViewById(R.id.iInstagram);
        photoProfile = findViewById(R.id.profileImg);
        signup = findViewById(R.id.btnSignUp);

        ra = new RetrofitAdapter(BioActivity.this);
    }

    private void getPassedData() {
        Bundle b = getIntent().getBundleExtra("bundle");
        username = b.getString("username");
        email = b.getString("email");
        password = b.getString("password");

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
            gambarUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), gambarUri);
                photoProfile.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(BioActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    private String imageToString(Bitmap b) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }
}
