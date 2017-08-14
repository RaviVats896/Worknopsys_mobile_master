package com.example.ravivats.worknopsysmobile;

public class WorkingOrderObject {
    private String mTask;
    private String mWeekDate;
    private String mProjectName;
    private String mActivity;
    private String mCustomerName;
    private String mCustomerAddress;

    public WorkingOrderObject(String wDay, String wDate, String pName, String cName, String cAddress) {
        mTask = wDay;
        mWeekDate = wDate;
        mProjectName = pName;
        mCustomerName = cName;
        mCustomerAddress = cAddress;
    }

    public WorkingOrderObject(String wDay, String wDate, String pName, String activity, String cName, String cAddress) {
        mTask = wDay;
        mWeekDate = wDate;
        mProjectName = pName;
        mActivity = activity;
        mCustomerName = cName;
        mCustomerAddress = cAddress;
    }

    public String getmTask() {
        return mTask;
    }

    public void setmTask(String mTask) {
        this.mTask = mTask;
    }

    public String getmWeekDate() {
        return mWeekDate;
    }

    public void setmWeekDate(String mWeekDate) {
        this.mWeekDate = mWeekDate;
    }

    public String getmProjectName() {
        return mProjectName;
    }

    public void setmProjectName(String mProjectName) {
        this.mProjectName = mProjectName;
    }

    public String getmCustomerName() {
        return mCustomerName;
    }

    public void setmCustomerName(String mCustomerName) {
        this.mCustomerName = mCustomerName;
    }

    public String getmCustomerAddress() {
        return mCustomerAddress;
    }

    public void setmCustomerAddress(String mCustomerAddress) {
        this.mCustomerAddress = mCustomerAddress;
    }

    public String getmActivity() {
        return mActivity;
    }

    public void setmActivity(String mActivity) {
        this.mActivity = mActivity;
    }
}
