package com.example.levon.departments.NetworkHelpers;

import com.example.levon.departments.Models.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EmployeesGet {
    @GET("employees")
    Call<List<Employee>> getEmployees(@Query("") String count);
}
