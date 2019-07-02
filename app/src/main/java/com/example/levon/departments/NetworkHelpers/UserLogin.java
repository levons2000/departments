package com.example.levon.departments.NetworkHelpers;

import com.example.levon.departments.Models.UserLoginModel;
import com.example.levon.departments.Models.UserTokenModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserLogin {
    @POST("authenticate")
    Call<UserTokenModel> loginToAccount(@Body UserLoginModel userLoginModel);
}
