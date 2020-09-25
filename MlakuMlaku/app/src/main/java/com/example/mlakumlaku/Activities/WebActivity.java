package com.example.mlakumlaku.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.mlakumlaku.R;

public class WebActivity extends AppCompatActivity {

    WebView webView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Intent i = getIntent();
        url = i.getStringExtra("url");

        webView = findViewById(R.id.myWeb);
        webView.loadUrl(url);
    }
}
