package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.interfaces.Api;
import com.example.myapplication.interfaces.UserInt;
import com.example.myapplication.models.JwtUtils;
import com.example.myapplication.models.LoginRequest;
import com.example.myapplication.models.LoginResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import io.jsonwebtoken.Claims;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
   Button btn;
   EditText email;
   EditText password;
   Button signin;
   String role;
    String etat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!ConnectivityUtils.isConnectedToInternet(this)) {
            Intent intent = new Intent(this, noInternet.class);
            startActivity(intent);

        }
        Button btn = findViewById(R.id.com);
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
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginRequest log = new LoginRequest();
                log.setEmail(email.getText().toString());
                log.setPassword(password.getText().toString());
                login(log);
            }
        });

    }
    public Void login(LoginRequest user) {
        // Create a Retrofit instance
        Retrofit retrofit = Api.getRetrofitInstance();

        // Create an implementation of the UserInt interface
        UserInt userApi = retrofit.create(UserInt.class);

        // Make the API request
        Call<LoginResponse> call = userApi.login(new LoginRequest(user.getEmail(), user.getPassword()));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    // Handle successful login response
                    LoginResponse loggedInUser = response.body();
                    Log.e("LoginError", "Error body: " + loggedInUser.token);
                    String responseBody = new Gson().toJson(loggedInUser);
                    Log.e("LoginSuccess", "Response body: " + responseBody);

                    // Decode the JWT token
                    Claims claims = JwtUtils.decodeJWT(loggedInUser.getToken());

                    // Retrieve information from the decoded claims
                    String userId = claims.getSubject();
                    for (Map.Entry<String, Object> entry : claims.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        Log.e("LoginSuccess", "Claim: " + key + " - Value: " + value);


                    }
                    role = claims.get("role").toString();
                    etat= claims.get("etat").toString();

                    if(etat.equals("1")){


                        if(role.equals("USER")){
                            Intent i = new Intent(MainActivity.this, home.class);  // Replace YourActivity with the appropriate activity
                            startActivity(i);
                        } else if (role.equals("LIVREUR")) {
                            Intent i = new Intent(MainActivity.this, livreurhome.class);  // Replace YourActivity with the appropriate activity
                            startActivity(i);
                        }

                    }else{
                        showToast("AcountBlocked please contact administration");
                           return;
                    }
                    Log.e("LoginSuccess", "Claim: " + role +"etat="+etat);


                    showToast("Login successful");
                } else {
                    showToast("Login failed");
                    Log.e("LoginError", "Error code: " + response.code());

                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("LoginError", "Error body: " + errorBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // You can access error information using response.errorBody()
                }
            }

            private void showToast(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Handle network failure
                if (t instanceof IOException) {
                    // IOException: Network or conversion error
                    showToast("Network error. Please check your internet connection.");
                } else {
                    // Other errors (e.g., parsing error, unexpected error)
                    showToast("Unexpected error. Please try again later.");
                }

                t.printStackTrace(); // Log the error details for debugging
            }
        });

        // Since this is an asynchronous operation, you may not return anything meaningful here
        return null;
    }

}