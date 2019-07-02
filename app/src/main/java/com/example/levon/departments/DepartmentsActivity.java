package com.example.levon.departments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.levon.departments.Adapters.DepartmentsAdapter;
import com.example.levon.departments.Models.Department;
import com.example.levon.departments.NetworkHelpers.NetworkRequest;

import java.util.ArrayList;
import java.util.List;

public class DepartmentsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Department> list = new ArrayList<>();
    private DepartmentsAdapter departmentsAdapter = new DepartmentsAdapter(this, list);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments);
        initViews();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(departmentsAdapter);
        recyclerView.setHasFixedSize(true);
        NetworkRequest.sharedInstance().makeDepartmentsGetRequest(this);
    }

    private void initViews() {
        recyclerView = findViewById(R.id.departments_list);
    }

    public void setupDepartmentsList(List<Department> list) {
        this.list.addAll(0, list);
        departmentsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
