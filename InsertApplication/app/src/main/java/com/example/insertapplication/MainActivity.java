package com.example.insertapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText nama, harga;
    Button insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nama = findViewById(R.id.itemName);
        harga = findViewById(R.id.itemPrice);
        insert = findViewById(R.id.btnInsert);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestAsync ra = new RequestAsync();
                ra.execute();
            }
        });
    }

    public class RequestAsync extends AsyncTask<String, String, String> {

        String getNama = nama.getText().toString();
        String getHarga = harga.getText().toString();

        @Override
        protected String doInBackground(String... strings) {
            try {
// POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("nama", getNama);
                postDataParams.put("harga", getHarga);
                postDataParams.put("admin", "Amrizal Rizky Fajar 17410100110");


                return RequestHandler.sendPost("https://script.google.com/macros/s/AKfycbxYlAPWTX5b8_PjSG7F5eCFv04cMmMRGKIzCmm82znIRiYrTX8/exec", postDataParams);
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != null) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        }

    }

}
