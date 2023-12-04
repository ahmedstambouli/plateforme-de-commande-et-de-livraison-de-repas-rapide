package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.R;

public class livreurhome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.livreurhome);
        if (!ConnectivityUtils.isConnectedToInternet(this)) {
            Intent intent = new Intent(this, noInternet.class);
            startActivity(intent);
        }
    }
}