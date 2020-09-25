package com.example.b_gorlapangan.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.b_gorlapangan.Auth;
import com.example.b_gorlapangan.R;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText email, password;
    TextView daftar;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initalizing();
        myClick();
    }

    private void myClick() {
        login.setOnClickListener(this);
        daftar.setOnClickListener(this);
    }

    private void initalizing() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.btnLogin);
        daftar = findViewById(R.id.daftar);
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
                if (password.getText().toString().isEmpty()) {
                    password.setError("Masukan Password");
                    password.requestFocus();
                    return;
                }

                myLogin();

                break;

            case R.id.daftar :
                Intent intent = new Intent(LoginActivity.this, RegisterAgencyActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void myLogin() {
        Auth a = new Auth(this);
        a.saveAuth("123");
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
