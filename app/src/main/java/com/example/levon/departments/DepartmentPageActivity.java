package com.example.levon.departments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.levon.departments.Adapters.EmployeesAdapter;
import com.example.levon.departments.Models.Department;
import com.example.levon.departments.Models.Employee;
import com.example.levon.departments.NetworkHelpers.NetworkRequest;

import java.util.List;

public class DepartmentPageActivity extends AppCompatActivity {

    public static Department currentDepartment;

    private TextView departmentNameText;
    private TextView departmentLocationText;
    private TextView departmentStateProvinceText;
    private TextView departmentAddressText;
    private TextView departmentPostalCodesText;
    private RecyclerView employeeRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_page);
        initViews();
        addInformation();
        NetworkRequest.sharedInstance().makeEmployeeGetRequest(this);
    }

    public void addEmployeeList(List<Employee> list) {
        EmployeesAdapter employeesAdapter = new EmployeesAdapter(this, list);
        employeeRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        employeeRecycler.setHasFixedSize(true);
        employeeRecycler.setAdapter(employeesAdapter);
    }

    private void initViews() {
        departmentNameText = findViewById(R.id.department_page_name);
        departmentLocationText = findViewById(R.id.department_page_location);
        departmentStateProvinceText = findViewById(R.id.department_page_province);
        departmentAddressText = findViewById(R.id.department_page_address);
        departmentPostalCodesText = findViewById(R.id.department_page_postal);
        employeeRecycler = findViewById(R.id.employee_list);
    }

    private void addInformation() {
        departmentNameText.setText(currentDepartment.getDepartmentName());
        departmentLocationText.setText(String.format("%s,%s",
                currentDepartment.getLocation().getCountry().getCountryName(),
                currentDepartment.getLocation().getCity()));
        departmentStateProvinceText.setText(currentDepartment.getLocation().getStateProvince());
        departmentAddressText.setText(currentDepartment.getLocation().getStreetAddress());
        departmentPostalCodesText.setText(currentDepartment.getLocation().getPostalCode());
    }
}
