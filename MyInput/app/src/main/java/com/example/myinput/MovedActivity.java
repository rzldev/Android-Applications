package com.example.myinput;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MovedActivity extends AppCompatActivity implements View.OnClickListener {

    TextView email, telephone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moved);

        email = findViewById(R.id.useremail);
        telephone = findViewById(R.id.usertelephone);

        email.setOnClickListener(this);
        telephone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.useremail :
                String target = email.getText().toString();
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + target));
                startActivity(emailIntent);
                break;
            case R.id.usertelephone :
                String number = telephone.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
                startActivity(callIntent);
                break;
        }
    }
}
