package com.example.b_gorlapangan.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.b_gorlapangan.R;

public class RegisterAgencyActivity extends AppCompatActivity implements View.OnClickListener {

    EditText agensi, phone, username, email, password, confirmPassword;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_agency);

        initializing();
        myCLick();
    }

    private void myCLick() {
        register.setOnClickListener(this);
    }

    private void initializing() {
        agensi = findViewById(R.id.agensi);
        phone = findViewById(R.id.phone);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.konfirmasi);
        register = findViewById(R.id.btnRegister);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegister :
                if (agensi.getText().toString().isEmpty()) {
                    agensi.setError("Masukan nama agensi");
                    agensi.requestFocus();
                    return;
                }
                if (phone.getText().toString().isEmpty()) {
                    phone.setError("Masukan nomor telepon");
                    phone.requestFocus();
                    return;
                }
                if (username.getText().toString().isEmpty()) {
                    username.setError("Masukan username");
                    username.requestFocus();
                    return;
                }
                if (email.getText().toString().isEmpty()) {
                    email.setError("Masukan email");
                    email.requestFocus();
                    return;
                }
                if (confirmPassword.getText().toString().isEmpty()) {
                    confirmPassword.setError("Masukan kembali password");
                    confirmPassword.requestFocus();
                    return;
                }
                if (!confirmPassword.getText().toString().equals(password.getText().toString())) {
                    confirmPassword.setError("Password tidak cocok");
                    confirmPassword.requestFocus();
                    return;
                }

                registering();

                break;
        }
    }

    private void registering() {
        Intent intent = new Intent(RegisterAgencyActivity.this, RegisterOwnerActivity.class);
        intent.putExtra("agensi", agensi.getText().toString());
        intent.putExtra("phone", phone.getText().toString());
        intent.putExtra("username", username.getText().toString());
        intent.putExtra("email", email.getText().toString());
        intent.putExtra("password", password.getText().toString());
        startActivity(intent);
    }
}
