package com.example.levon.departments.NetworkHelpers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserAuthorization {
    @GET("authenticate")
    Call<Void> authorization(@Query("id_token") String idToken);
}
