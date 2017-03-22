package com.example.ravivats.worknopsysmobile;

import com.example.ravivats.worknopsysmobile.domain.Authorization;
import com.example.ravivats.worknopsysmobile.domain.Employee;
import org.json.JSONArray;



/**
 * Created by Ravi Vats on 27-12-2016.
 */

public class Constants {

    final static String LOGOUT_URL = "http://worknopsys.ml/api/employees/logout";
    private static Employee EMPLOYEE;
    private static Authorization AUTH;
    private static JSONArray CUSTOMERS;
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

    public static Authorization getAUTH() {
        return AUTH;
    }

    public static void setAUTH(Authorization AUTH) {
        Constants.AUTH = AUTH;
    }
}
