package com.example.animationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean fadeStatus = false;
    private boolean slideStatus = false;

    private ImageView flash, reverseFlash;
    private Button btnFade, btnSlideHorz, btnSlideVert, btnRotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flash = findViewById(R.id.imgFlash);
        reverseFlash = findViewById(R.id.imgReverseFlash);
        btnFade = findViewById(R.id.btnFade);
        btnSlideHorz = findViewById(R.id.btnSlideHorz);
        btnSlideVert = findViewById(R.id.btnSlideVert);
        btnRotate = findViewById(R.id.btnRotate);

        flash.setImageResource(R.drawable.img_flash);
        reverseFlash.setImageResource(R.drawable.img_reverse_flash);

        reverseFlash.setAlpha(0f);

        btnFade.setOnClickListener(this);
        btnSlideHorz.setOnClickListener(this);
        btnSlideVert.setOnClickListener(this);
        btnRotate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFade :
                fade();
                return;
            case R.id.btnSlideHorz :
                slideHorz();
                return;
            case R.id.btnSlideVert :
                slideVert();
                return;
            case R.id.btnRotate :
                rorate();
                return;
        }
    }

    private void rorate() {
        flash.setAlpha(1f);
        reverseFlash.setAlpha(0f);
        flash.setTranslationX(0f);
        flash.setRotation(0f);

        flash.animate().rotation(720f).setDuration(1000);
    }

    private void slideVert() {
        reverseFlash.setAlpha(1f);
        flash.setAlpha(1f);

        if (slideStatus == true) {
            reverseFlash.animate().translationYBy(-2000f).setDuration(1000);
            flash.animate().translationYBy(-3000f).setDuration(1000);
            slideStatus = false;
        } else {
            reverseFlash.setTranslationY(-2000f);
            flash.animate().translationYBy(3000f).setDuration(1000);
            reverseFlash.animate().translationYBy(2000f).setDuration(1000);
            slideStatus = true;
        }
    }

    private void slideHorz() {
        reverseFlash.setAlpha(1f);
        flash.setAlpha(1f);

        if (slideStatus == true) {
            reverseFlash.animate().translationXBy(-1500f).setDuration(1000);
            flash.animate().translationXBy(-1500f).setDuration(1000);
            slideStatus = false;
        } else {
            reverseFlash.setTranslationX(-1500f);
            flash.animate().translationXBy(1500f).setDuration(1000);
            reverseFlash.animate().translationXBy(1500f).setDuration(1000);
            slideStatus = true;
        }
    }

    private void fade() {
        flash.setTranslationX(0f);
        reverseFlash.setTranslationX(0f);

        if (fadeStatus == true) {
            reverseFlash.animate().alpha(0f).setDuration(1000);
            flash.animate().alpha(1f).setDuration(1000);
            fadeStatus = false;
        } else {
            reverseFlash.setAlpha(0f);
            flash.animate().alpha(0f).setDuration(1000);
            reverseFlash.animate().alpha(1f).setDuration(1000);
            fadeStatus = true;
        }

    }
}
