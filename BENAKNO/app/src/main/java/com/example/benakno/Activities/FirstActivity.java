package com.example.benakno.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.benakno.R;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    Button signIn, signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        initializing();
        checkAuth();
        myClick();
    }

    private void checkAuth() {
        SharedPreferences sp = getSharedPreferences("UserData", MODE_PRIVATE);
        String name = sp.getString("name", "");
        if (name.equals(null)) {
            Intent intent = new Intent(FirstActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }

    }


    private void initializing() {
        signIn = findViewById(R.id.signin);
        signUp = findViewById(R.id.signup);
    }

    private void myClick() {
        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signin :
                Intent iSignIn = new Intent(FirstActivity.this, SignInActivity.class);
                startActivity(iSignIn);
                finish();
                break;
            case R.id.signup :
                Intent iSignUp = new Intent(FirstActivity.this, SignUpActivity.class);
                startActivity(iSignUp);
                finish();
                break;
        }
    }
}
