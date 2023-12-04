package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.interfaces.Api;
import com.example.myapplication.interfaces.UserInt;
import com.example.myapplication.models.RegisterResponse;
import com.example.myapplication.models.User;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Sign_up extends AppCompatActivity {
   Button btn;
   EditText name;
   EditText password;
   EditText tel;
   EditText email;
   EditText address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        if (!ConnectivityUtils.isConnectedToInternet(this)) {
            Intent intent = new Intent(this, noInternet.class);
            startActivity(intent);
        }


        Button btn = findViewById(R.id.signup);
        name=findViewById(R.id.nom);
        password=findViewById(R.id.password);
        tel=findViewById(R.id.tel);
        email=findViewById(R.id.email);
        address=findViewById(R.id.address);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user = new User();
                user.setName(name.getText().toString());
                user.setEmail(email.getText().toString());
                user.setPassword(password.getText().toString());
                user.setTel(tel.getText().toString());
                user.setAddress(address.getText().toString());
                user.setEtat(1L); // assuming etat is a Long
                user.setRole("USER");
                user.setEnabled(true);
                Log.d("user", "Response Body: " );
                register(user);
            }
        });

    }
    public Void register(User user) {
        // Create a Retrofit instance
        Retrofit retrofit = Api.getRetrofitInstance();

        // Create an implementation of the UserInt interface
        UserInt userApi = retrofit.create(UserInt.class);



        // Make the API request
        Call<RegisterResponse> call = userApi.registerUser(user);
        call.enqueue(new Callback<RegisterResponse>() {
                         @Override
                         public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                             if (response.isSuccessful()) {
                                 // Handle successful registration response
                                 RegisterResponse registeredUser = response.body();
                                 showToast("Registered successfully");
                                  Intent i=new Intent(Sign_up.this,MainActivity.class);
                                  startActivity(i);
                                 try {
                                     String responseBody = new Gson().toJson(registeredUser);
                                 } catch (JsonSyntaxException e) {
                                     e.printStackTrace();
                                 }
                             } else {
                                 showToast("Registration failed");
                                 Log.e("RegistrationError", "Error code: " + response.code());
                                 try {
                                     String errorBody = response.errorBody().string();
                                     Log.e("RegistrationError", "Error body: " + errorBody);
                                 } catch (IOException e) {
                                     e.printStackTrace();
                                 }
                                 // You can access error information using response.errorBody()
                             }
                         }

                         private void showToast(String message) {
                             Toast.makeText(Sign_up.this, message, Toast.LENGTH_SHORT).show();
                         }


                         @Override
                         public void onFailure(Call<RegisterResponse> call, Throwable t) {
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