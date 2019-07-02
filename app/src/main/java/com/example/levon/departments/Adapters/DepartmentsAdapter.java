package com.example.levon.departments.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.levon.departments.DepartmentPageActivity;
import com.example.levon.departments.Models.Department;
import com.example.levon.departments.R;

import java.util.List;

public class DepartmentsAdapter extends RecyclerView.Adapter<DepartmentsAdapter.DepartmentsViewHolder>{

    final private Context context;
    final private List<Department> list;

    public DepartmentsAdapter(final Context context, final List<Department> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public DepartmentsAdapter.DepartmentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.departments_item_style, parent, false);

        return new DepartmentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DepartmentsAdapter.DepartmentsViewHolder holder, int position) {
        String location = list.get(position).getLocation().getCountry().getCountryName() + ","
                + list.get(position).getLocation().getCity();
        holder.departmentName.setText(list.get(position).getDepartmentName());
        holder.departmentLocation.setText(location);
        holder.departmentAddress.setText(list.get(position).getLocation().getStreetAddress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DepartmentsViewHolder extends RecyclerView.ViewHolder {
        TextView departmentName;
        TextView departmentLocation;
        TextView departmentAddress;

        public DepartmentsViewHolder(View itemView) {
            super(itemView);
            departmentName = itemView.findViewById(R.id.department_name);
            departmentLocation = itemView.findViewById(R.id.department_location);
            departmentAddress = itemView.findViewById(R.id.department_address);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DepartmentPageActivity.currentDepartment = list.get(getAdapterPosition());
                    context.startActivity(new Intent(context, DepartmentPageActivity.class));
                }
            });
        }
    }
}
