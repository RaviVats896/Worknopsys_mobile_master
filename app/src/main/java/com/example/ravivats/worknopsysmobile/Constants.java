package com.example.ravivats.worknopsysmobile;

import android.support.annotation.BoolRes;

import com.example.ravivats.worknopsysmobile.domain.Authorization;
import com.example.ravivats.worknopsysmobile.domain.Employee;
import com.example.ravivats.worknopsysmobile.domain.WorkingOrder;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

public class Constants {

    final static String LOGOUT_URL = "http://worknopsys.ml:5000/api/employees/logout";
    private static Map<String, String> taskMap, taskInvMap, projectMap, projectInvMap, customerMap, customerInvMap, resourceMap, resourceInvMap;
    private static ArrayList<WorkingOrder> workingOrders;
    private static Boolean posLocation, projEnhance;
    private static Employee EMPLOYEE;
    private static Authorization AUTH;
    private static JSONArray CUSTOMERS;
    private static String evidenceWorkDate, evidenceGTime1, evidenceGTime2, evidenceWTime1, evidenceWTime2, evidenceBTime1, evidenceBTime2, evidenceRTime1, evidenceRTime2;

    public static Map<String, String> getResourceInvMap() {
        return resourceInvMap;
    }

    public static void setResourceInvMap(Map<String, String> resourceInvMap) {
        Constants.resourceInvMap = resourceInvMap;
    }

    public static Map<String, String> getResourceMap() {
        return resourceMap;
    }

    public static void setResourceMap(Map<String, String> resourceMap) {
        Constants.resourceMap = resourceMap;
    }

    public static Map<String, String> getCustomerInvMap() {
        return customerInvMap;
    }

    public static void setCustomerInvMap(Map<String, String> customerInvMap) {
        Constants.customerInvMap = customerInvMap;
    }

    public static Map<String, String> getProjectInvMap() {
        return projectInvMap;
    }

    public static void setProjectInvMap(Map<String, String> projectInvMap) {
        Constants.projectInvMap = projectInvMap;
    }

    public static Map<String, String> getTaskInvMap() {
        return taskInvMap;
    }

    public static void setTaskInvMap(Map<String, String> taskInvMap) {
        Constants.taskInvMap = taskInvMap;
    }

    public static ArrayList<WorkingOrder> getWorkingOrders() {
        return workingOrders;
    }

    public static void setWorkingOrders(ArrayList<WorkingOrder> workingOrders) {
        Constants.workingOrders = workingOrders;
    }

    public static Map<String, String> getCustomerMap() {
        return customerMap;
    }

    public static void setCustomerMap(Map<String, String> customerMap) {
        Constants.customerMap = customerMap;
    }

    public static Map<String, String> getProjectMap() {
        return projectMap;

    }

    public static void setProjectMap(Map<String, String> projectMap) {
        Constants.projectMap = projectMap;
    }

    public static Map<String, String> getTaskMap() {
        return taskMap;
    }

    public static void setTaskMap(Map<String, String> taskMap) {
        Constants.taskMap = taskMap;
    }

    public static String getEvidenceWorkDate() {
        return evidenceWorkDate;
    }

    public static void setEvidenceWorkDate(String evidenceWorkDate) {
        Constants.evidenceWorkDate = evidenceWorkDate;
    }

    public static String getEvidenceRTime2() {
        return evidenceRTime2;
    }

    public static void setEvidenceRTime2(String evidenceRTime2) {
        Constants.evidenceRTime2 = evidenceRTime2;
    }

    public static String getEvidenceBTime1() {
        return evidenceBTime1;
    }

    public static void setEvidenceBTime1(String evidenceBTime1) {
        Constants.evidenceBTime1 = evidenceBTime1;
    }

    public static String getEvidenceRTime1() {
        return evidenceRTime1;
    }

    public static void setEvidenceRTime1(String evidenceRTime1) {
        Constants.evidenceRTime1 = evidenceRTime1;
    }

    public static String getEvidenceBTime2() {
        return evidenceBTime2;
    }

    public static void setEvidenceBTime2(String evidenceBTime2) {
        Constants.evidenceBTime2 = evidenceBTime2;
    }

    public static String getEvidenceWTime2() {
        return evidenceWTime2;
    }

    public static void setEvidenceWTime2(String evidenceWTime2) {
        Constants.evidenceWTime2 = evidenceWTime2;
    }

    public static String getEvidenceWTime1() {
        return evidenceWTime1;
    }

    public static void setEvidenceWTime1(String evidenceWTime1) {
        Constants.evidenceWTime1 = evidenceWTime1;
    }

    public static String getEvidenceGTime2() {
        return evidenceGTime2;
    }

    public static void setEvidenceGTime2(String evidenceGTime2) {
        Constants.evidenceGTime2 = evidenceGTime2;
    }

    public static String getEvidenceGTime1() {
        return evidenceGTime1;
    }

    public static void setEvidenceGTime1(String evidenceGTime1) {
        Constants.evidenceGTime1 = evidenceGTime1;
    }

    public static JSONArray getCUSTOMERS() {
        return CUSTOMERS;
    }

    public static Boolean getProjEnhance() {
        return projEnhance;
    }

    public static void setProjEnhance(Boolean projEnhance) {
        Constants.projEnhance = projEnhance;
    }

    public static Boolean getPosLocation() {
        return posLocation;
    }

    public static void setPosLocation(Boolean posLocation) {
        Constants.posLocation = posLocation;
    }

    public static void setCUSTOMERS(JSONArray CUSTOMERS) {
        Constants.CUSTOMERS = CUSTOMERS;
    }

    public static Employee getEMPLOYEE() {
        return EMPLOYEE;
    }

    public static void setEMPLOYEE(Employee EMPLOYEE) {
        Constants.EMPLOYEE = EMPLOYEE;
    }

    public static Authorization getAUTH() {
        return AUTH;
    }

    public static void setAUTH(Authorization AUTH) {
        Constants.AUTH = AUTH;
    }

    public static String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        return sdf.format(calendar.getTime());
    }

    public static String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh-mm", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        return sdf.format(calendar.getTime());
    }
}
