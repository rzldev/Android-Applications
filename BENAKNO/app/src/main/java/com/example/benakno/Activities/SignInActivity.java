package com.example.benakno.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.benakno.DB.MyDBAdapter;
import com.example.benakno.R;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    EditText inputEmail, inputPassword;
    Button login;

    MyDBAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initializing();
        myClick();
    }

    private void initializing() {
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        login = findViewById(R.id.login);

        adapter = new MyDBAdapter(this);
    }

    private void myClick() {
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (inputEmail.getText().toString().isEmpty()) {
            inputEmail.setError("Masukan Email");
            inputEmail.requestFocus();
            return;
        }
        if (inputPassword.getText().toString().isEmpty()) {
            inputPassword.setError("Masukan Password");
            inputPassword.requestFocus();
            return;
        }
        if (inputPassword.getText().toString().length() < 8) {
            inputPassword.setError("Password Kurang Dari 8 Karakter");
            inputPassword.requestFocus();
            return;
        }

        if (adapter.getData().contains( inputPassword.getText().toString() + " " + inputEmail.getText().toString())) {
            SharedPreferences.Editor editor = getSharedPreferences("UserData", MODE_PRIVATE).edit();
            editor.putString("email", inputEmail.getText().toString());

            int position = 0;

            String[] userData = adapter.getData().split(" ");
            for (int x = 0; x < userData.length; x++) {
                if (userData[x].equals(inputPassword.getText().toString())) {
                    position = x - 1;
                    editor.putString("name", userData[position]);

                    break;
                }
            }

            editor.apply();

            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        } else {
            Toast.makeText(this, "Login Gagal", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SignInActivity.this, FirstActivity.class);
        startActivity(intent);
        finish();
    }
}
