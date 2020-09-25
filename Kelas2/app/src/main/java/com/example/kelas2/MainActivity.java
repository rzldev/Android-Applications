package com.example.kelas2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView helloworld = (TextView)findViewById(R.id.HelloWorld);
        Button ok = (Button)findViewById(R.id.ButtonOK);
        Button reset = (Button)findViewById(R.id.ButtonReset);

        x = 0;

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x += 1;
                helloworld.setText(String.valueOf(x));
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helloworld.setText("");
                x = 0;
            }
        });
    }
}
