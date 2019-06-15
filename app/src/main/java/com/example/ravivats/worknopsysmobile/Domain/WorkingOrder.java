package com.example.ravivats.worknopsysmobile.Domain;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorkingOrder implements Serializable
{

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("Resources")
    @Expose
    private String resources;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("Employee")
    @Expose
    private String employee;
    @SerializedName("Customer")
    @Expose
    private String customer;
    @SerializedName("Task")
    @Expose
    private String task;
    @SerializedName("StartDate")
    @Expose
    private String startDate;
    @SerializedName("Project")
    @Expose
    private String project;
    @SerializedName("Address")
    @Expose
    private String address;
    private final static long serialVersionUID = -4816486543354469319L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}

