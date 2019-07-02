package com.example.levon.departments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.levon.departments.Adapters.JobsAdapter;
import com.example.levon.departments.Models.Employee;
import com.example.levon.departments.Models.Job;
import com.example.levon.departments.Models.Task;
import com.example.levon.departments.NetworkHelpers.NetworkRequest;

import java.util.List;

public class EmployeeActivity extends AppCompatActivity {

    public static Employee currentEmployee;

    private TextView employeeNameText;
    private TextView employeeSurnameText;
    private TextView employeeEmailText;
    private TextView employeePhoneText;
    private TextView employeeSalaryText;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        initViews();
        addInfo();
        NetworkRequest.sharedInstance().makeJobGetRequest(this);
    }

    private void initViews() {
        employeeNameText = findViewById(R.id.employee_info_name);
        employeeSurnameText = findViewById(R.id.employee_info_surname);
        employeeEmailText = findViewById(R.id.employee_info_email);
        employeePhoneText = findViewById(R.id.employee_info_phone);
        employeeSalaryText = findViewById(R.id.employee_info_salary);
        recyclerView = findViewById(R.id.job_list);
    }

    private void addInfo() {
        employeeNameText.setText(currentEmployee.getFirstName());
        employeeSurnameText.setText(currentEmployee.getLastName());
        employeeEmailText.setText(currentEmployee.getEmail());
        employeePhoneText.setText(currentEmployee.getPhoneNumber());
        employeeSalaryText.setText(String.valueOf(currentEmployee.getSalary()));
    }

    public void setupRecyclerView(List<Job> list, List<Task> taskList) {
        JobsAdapter jobsAdapter = new JobsAdapter(this, list, taskList);
        recyclerView.setAdapter(jobsAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}
