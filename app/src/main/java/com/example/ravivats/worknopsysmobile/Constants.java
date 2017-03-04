package com.example.ravivats.worknopsysmobile;

import com.example.ravivats.worknopsysmobile.domain.Employee;

import org.json.JSONArray;

/**
 * Created by Ravi Vats on 27-12-2016.
 */

public class Constants {

    private static Employee EMPLOYEE;
    private static JSONArray CUSTOMERS;

    public static boolean isLogout() {
        return logout;
    }

    public static void setLogout(boolean logout) {
        Constants.logout = logout;
    }

    private static boolean logout;
    public static JSONArray getCUSTOMERS() {
        return CUSTOMERS;
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

}
