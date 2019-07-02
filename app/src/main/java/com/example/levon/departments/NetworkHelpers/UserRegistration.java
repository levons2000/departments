package com.example.levon.departments.NetworkHelpers;

import com.example.levon.departments.Models.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserRegistration {
    @POST("register")
    Call<UserModel> registerAccount(@Body UserModel userModel);
}
