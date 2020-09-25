package com.example.myinput;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button aboutUs, dial, data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutUs = findViewById(R.id.aboutus);
        data = findViewById(R.id.data);
        dial = findViewById(R.id.dial);

        aboutUs.setOnClickListener(this);
        data.setOnClickListener(this);
        dial.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.aboutus :
                Intent aboutUsIntent = new Intent(MainActivity.this, MovedActivity.class);
                startActivity(aboutUsIntent);
                break;
            case R.id.data :
                Intent dataIntent = new Intent(MainActivity.this, MovedActivity.class);
                startActivity(dataIntent);
                break;
            case R.id.dial :
                String phoneNumber = "08563076813";
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                startActivity(dialIntent);
                break;
        }
    }
}
