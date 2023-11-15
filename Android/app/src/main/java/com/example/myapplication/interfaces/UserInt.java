package com.example.myapplication.interfaces;

import com.example.myapplication.models.LoginRequest;
import com.example.myapplication.models.LoginResponse;
import com.example.myapplication.models.RegisterResponse;
import com.example.myapplication.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
public interface UserInt {

    @POST("users/register")
    Call<RegisterResponse> registerUser(@Body User user);
    @POST("users/login")
    Call<LoginResponse> login(@Body LoginRequest login);

}
