package com.example.webviewapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText url;
    Button search;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializing();
        myClick();
    }

    private void myClick() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (url.getText().toString().isEmpty()) {
                    url.setError("Masukan URL");
                    url.requestFocus();
                    return;
                }
                myWebView();
            }
        });
    }

    private void myWebView() {
        webView.loadUrl(url.getText().toString());
    }

    private void initializing() {
        url = findViewById(R.id.url);
        search = findViewById(R.id.searchBtn);
        webView = findViewById(R.id.webview);

        setContentView(webView);

    }


}
