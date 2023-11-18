package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
public class noInternet extends AppCompatActivity {
    Button relaod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);
        relaod = findViewById(R.id.rel);
        relaod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectivityUtils connectivityUtils = new ConnectivityUtils();
                if (connectivityUtils.isConnectedToInternet(noInternet.this)) {

                    Toast.makeText(noInternet.this, "Connecté", Toast.LENGTH_SHORT).show();
                    onBackPressed();


                } else {
                    Toast.makeText(noInternet.this, "verifier vos connexion", Toast.LENGTH_SHORT).show();

                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            }
        });
    }

}