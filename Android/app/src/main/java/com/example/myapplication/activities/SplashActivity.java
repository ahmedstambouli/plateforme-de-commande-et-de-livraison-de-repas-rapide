package com.example.myapplication.activities;

import static java.time.temporal.ChronoUnit.MILLIS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.myapplication.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Use MILLIS.getDuration().toMillis() to obtain the milliseconds from MILLIS
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, Accueil.class));
                finish();
            }
        }, 1000); // 2000 milliseconds (2 seconds)

    }}