package com.example.levon.departments.NetworkHelpers;

import com.example.levon.departments.Models.Department;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DepartmentsGet {
    @GET("departments")
    Call<List<Department>> getDepartments();
}
