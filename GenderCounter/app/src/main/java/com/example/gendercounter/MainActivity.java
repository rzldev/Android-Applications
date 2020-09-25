package com.example.gendercounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView men, women, total;
    private Button countMen, countWomen, reset;

    int xMen, xWomen, xTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        men = findViewById(R.id.Men);
        women = findViewById(R.id.Women);
        total = findViewById(R.id.total);

        countMen = findViewById(R.id.CountMen);
        countWomen = findViewById(R.id.CountWoman);
        reset = findViewById(R.id.Reset);

        xMen = 0;
        xWomen = 0;
        xTotal = 0;

        men.setText("Jumlah Pria : " + String.valueOf(xMen));
        women.setText("Jumlah Wanita : " + String.valueOf(xWomen));
        total.setText("Jumlah Penumpang : " + String.valueOf(xTotal));

        countMen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xMen += 1;
                xTotal += 1;

                men.setText("Jumlah Pria : " + String.valueOf(xMen));
                total.setText("Jumlah Penumpang : " + String.valueOf(xTotal));
            }
        });

        countWomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xWomen += 1;
                xTotal += 1;

                women.setText("Jumlah Wanita : " + String.valueOf(xWomen));
                total.setText("Jumlah Penumpang : " + String.valueOf(xTotal));
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xMen = 0;
                xWomen = 0;
                xTotal = 0;

                men.setText("Jumlah Pria : " + String.valueOf(xMen));
                women.setText("Jumlah Wanita : " + String.valueOf(xWomen));
                total.setText("Jumlah Penumpang : " + String.valueOf(xTotal));
            }
        });
    }
}
