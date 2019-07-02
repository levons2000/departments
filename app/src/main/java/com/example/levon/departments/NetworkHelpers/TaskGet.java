package com.example.levon.departments.NetworkHelpers;

import com.example.levon.departments.Models.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TaskGet {
    @GET("tasks")
    Call<List<Task>> getTasks(@Query("") String count);
}
