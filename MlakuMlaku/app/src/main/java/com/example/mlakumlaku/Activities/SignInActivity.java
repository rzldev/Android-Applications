package com.example.mlakumlaku.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mlakumlaku.R;

public class SignInActivity extends AppCompatActivity {

    EditText username, email, password, confirmation;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initialize();
        myClick();
    }

    private void myClick() {
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUsername = username.getText().toString().trim();
                String getEmail = email.getText().toString().trim();
                String getPassword = password.getText().toString().trim();
                String getConfirmation = confirmation.getText().toString().trim();

                if (getUsername.isEmpty()) {
                    username.setError("Masukan Username");
                    username.requestFocus();
                    return;
                }
                if (getEmail.isEmpty()) {
                    email.setError("Masukan Email");
                    email.requestFocus();
                    return;
                }
                if (getPassword.isEmpty()) {
                    password.setError("Masukan Password");
                    password.requestFocus();
                    return;
                }
                if (getUsername.isEmpty()) {
                    confirmation.setError("Masukan Konfirmasi Password");
                    confirmation.requestFocus();
                    return;
                }
                if (!getConfirmation.equals(getPassword)) {
                    confirmation.setError("Password Tidak Cocok");
                    confirmation.requestFocus();
                    return;
                }

                Bundle bundle = new Bundle();
                bundle.putString("username", getUsername);
                bundle.putString("email", getEmail);
                bundle.putString("password", getPassword);

                Intent intent = new Intent(SignInActivity.this, BioActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
    }

    private void initialize() {
        username = findViewById(R.id.iUsername);
        email = findViewById(R.id.iEmail);
        password = findViewById(R.id.iPassword);
        confirmation = findViewById(R.id.iConfirmation);
        signUp = findViewById(R.id.btnSignUp);
    }
}
