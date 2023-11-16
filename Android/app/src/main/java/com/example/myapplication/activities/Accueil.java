package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class Accueil extends AppCompatActivity {
  Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        Button btn = findViewById(R.id.com);
     btn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent i = new Intent(Accueil.this, MainActivity.class);  // Replace YourActivity with the appropriate activity
             startActivity(i);
         }
     });
    }
}