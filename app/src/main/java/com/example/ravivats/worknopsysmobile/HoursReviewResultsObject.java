package com.example.ravivats.worknopsysmobile;

/**
 * Created by Ravi Vats on 02-09-2018.
 */

public class HoursReviewResultsObject {
    private String goingTime;
    private String workingTime;
    private String breakTime;
    private String returningTime;
    private String workDate;


    public HoursReviewResultsObject(String goingTime, String workingTime, String breakTime, String returningTime, String workDate){
        this.goingTime = goingTime;
        this.workingTime = workingTime;
        this.breakTime = breakTime;
        this.returningTime = returningTime;
        this.workDate = workDate;
    }

    public String getGoingTime() {
        return goingTime;
    }

    public void setGoingTime(String goingTime) {
        this.goingTime = goingTime;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }

    public String getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(String breakTime) {
        this.breakTime = breakTime;
    }

    public String getReturningTime() {
        return returningTime;
    }

    public void setReturningTime(String returningTime) {
        this.returningTime = returningTime;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

}
