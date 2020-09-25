package com.example.myaccountmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int MY_PERMISSION_REQUEST_SEND_SMS = 1001;
    TextInputEditText email, pass;
    Button login;
    TextView lupaPass;

    MyDBAdapter myDBAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializing();
        firstAccount();
        myClick();
        checkSMSPermission();

    }

    private void firstAccount() {
        String data = myDBAdapter.getData();
        if (data.length() < 1) {
            long id = myDBAdapter.insertData("ADMIN", "admin@demo.com", "admin123");

        }
    }

    private void checkSMSPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSION_REQUEST_SEND_SMS);

        }
    }

    private void myClick() {
        login.setOnClickListener(this);
        lupaPass.setOnClickListener(this);
    }

    private void initializing() {
        email = findViewById(R.id.inputEmail);
        pass = findViewById(R.id.inputPassword);
        login = findViewById(R.id.btnLogin);
        lupaPass = findViewById(R.id.lupaPassword);

        myDBAdapter = new MyDBAdapter(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin :
                if (email.getText().toString().isEmpty()) {
                    email.setError("Masukan Email");
                    email.requestFocus();
                    return;
                }
                if (pass.getText().toString().isEmpty()) {
                    pass.setError("Masukan Email");
                    pass.requestFocus();
                    return;
                }

                if (email.getText().toString().equals("admin@demo.com") && pass.getText().toString().equals("admin123")) {
                    funcLogin();

                } else {
                    Toast.makeText(this, "Email atau Password salah", Toast.LENGTH_SHORT).show();

                }

                break;
            case R.id.lupaPassword :
                String phoneNumber = "087851597561";
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNumber, null, "admin123", null, null);

                break;

        }
    }

    private void funcLogin() {
        int login = myDBAdapter.loginSession(email.getText().toString(), pass.getText().toString());

        if (login != 1) {
            Toast.makeText(this, "Cek Email dan Password", Toast.LENGTH_SHORT).show();

        } else {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
            finish();

        }
    }
}
