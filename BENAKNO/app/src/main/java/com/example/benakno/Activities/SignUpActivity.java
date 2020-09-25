package com.example.benakno.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.benakno.DB.MyDBAdapter;
import com.example.benakno.R;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    EditText username, email, password, confirmPassword;
    Button signUp;

    MyDBAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initializing();
        myClick();
    }

    private void initializing() {
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        signUp = findViewById(R.id.signUp);

        adapter = new MyDBAdapter(this);
    }
    private void myClick() {
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        editTextRule();

        long id = adapter.insertData(username.getText().toString(), email.getText().toString(), password.getText().toString());

        if (id <= 0) {
            Toast.makeText(this, "Gagal Registrasi", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Berhasil Registrasi", Toast.LENGTH_SHORT).show();
            username.setText("");
            email.setText("");
            password.setText("");

            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();
        }

    }

    private void editTextRule() {
        if (username.getText().toString().isEmpty()) {
            username.setError("Masukan Username");
            username.requestFocus();
            return;
        }
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
        if (password.getText().toString().length() < 8) {
            password.setError("Password Kurang dari 8 Karakter");
            password.requestFocus();
            return;
        }
        if (confirmPassword.getText().toString().isEmpty()) {
            confirmPassword.setError("Masukan Confirmation Password");
            confirmPassword.requestFocus();
            return;
        }
        if (confirmPassword.getText().toString().length() < 8) {
            confirmPassword.setError("Confirmation Password Kurang Dari 8 Karakter");
            confirmPassword.requestFocus();
            return;
        }
        if (!confirmPassword.getText().toString().equals(password.getText().toString())) {
            confirmPassword.setError("Confirmation Password Tidak Cocok");
            confirmPassword.requestFocus();
            return;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SignUpActivity.this, FirstActivity.class);
        startActivity(intent);
        finish();
    }
}
