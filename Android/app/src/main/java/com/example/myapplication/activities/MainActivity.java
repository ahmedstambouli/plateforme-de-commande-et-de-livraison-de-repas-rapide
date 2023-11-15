package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {
   Button btn;
   EditText email;
   EditText password;
   Button signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.signup);
        password=findViewById(R.id.login_pwd);
        email=findViewById(R.id.login);
        signin=findViewById(R.id.signinbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, Sign_up.class);
                startActivity(i);

            }
        });

    }

}