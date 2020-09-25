package com.example.mlakumlaku.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mlakumlaku.Adapters.DaoAdapter;
import com.example.mlakumlaku.Adapters.RetrofitAdapter;
import com.example.mlakumlaku.Classes.User;
import com.example.mlakumlaku.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText email, password;
    Button login, signup;
    TextView forgotPassword;

    RetrofitAdapter ra;
    DaoAdapter da;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();
        myClick();
        checkAuth();
    }

    private void checkAuth() {
        List<User> u = da.showUser();
        if (u.size() > 0) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void myClick() {
        login.setOnClickListener(LoginActivity.this);
        signup.setOnClickListener(LoginActivity.this);
        forgotPassword.setOnClickListener(LoginActivity.this);
    }

    private void initialize() {
        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        login = findViewById(R.id.btnLogin);
        signup = findViewById(R.id.btnSignUp);
        forgotPassword = findViewById(R.id.lupaPassword);

        ra = new RetrofitAdapter(LoginActivity.this);
        da = new DaoAdapter(LoginActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin :
                String getEmail = email.getText().toString().trim();
                String getPassword = password.getText().toString().trim();

                if(getEmail.isEmpty()) {
                    email.setError("Masukan Email");
                    email.requestFocus();
                    return;
                }
                if (getPassword.isEmpty()) {
                    password.setError("Masukan Password");
                    password.requestFocus();
                    return;
                }

                ra.login(getEmail, getPassword);

                break;

            case R.id.btnSignUp :
                Intent intent = new Intent(LoginActivity.this, SignInActivity.class);
                startActivity(intent);
                break;

            case R.id.lupaPassword :
                break;

        }
    }
}
