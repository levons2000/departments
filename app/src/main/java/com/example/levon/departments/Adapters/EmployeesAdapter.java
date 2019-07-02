package com.example.levon.departments.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.levon.departments.EmployeeActivity;
import com.example.levon.departments.Models.Employee;
import com.example.levon.departments.R;

import java.util.List;

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesAdapter.EmployeesViewHolder> {

    private Context context;
    private List<Employee> list;

    public EmployeesAdapter(Context context, List<Employee> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public EmployeesAdapter.EmployeesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.employee_style, parent, false);
        return new EmployeesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeesAdapter.EmployeesViewHolder holder, int position) {
        Employee currentEmployee = list.get(position);
        holder.employeeNameText.setText(String.format("%s %s",
                currentEmployee.getFirstName(),
                currentEmployee.getLastName()));
        holder.employeeEmailText.setText(currentEmployee.getEmail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EmployeesViewHolder extends RecyclerView.ViewHolder {
        private TextView employeeNameText;
        private TextView employeeEmailText;

        public EmployeesViewHolder(View itemView) {
            super(itemView);
            employeeNameText = itemView.findViewById(R.id.employee_name);
            employeeEmailText = itemView.findViewById(R.id.employee_email);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EmployeeActivity.currentEmployee = list.get(getAdapterPosition());
                    context.startActivity(new Intent(context, EmployeeActivity.class));
                }
            });
        }
    }
}
