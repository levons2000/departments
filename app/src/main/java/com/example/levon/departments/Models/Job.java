package com.example.levon.departments.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Job {

    @SerializedName("employee")
    @Expose
    private Employee employee;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("jobTitle")
    @Expose
    private String jobTitle;
    @SerializedName("maxSalary")
    @Expose
    private Integer maxSalary;
    @SerializedName("minSalary")
    @Expose
    private Integer minSalary;
    @SerializedName("tasks")
    @Expose
    private List<Task> tasks = null;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Integer maxSalary) {
        this.maxSalary = maxSalary;
    }

    public Integer getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}
