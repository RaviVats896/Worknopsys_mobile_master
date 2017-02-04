package com.example.ravivats.worknopsysmobile;

import com.example.ravivats.worknopsysmobile.domain.Employee;

/**
 * Created by Ravi Vats on 27-12-2016.
 */

public class Constants {
    private static Employee EMPLOYEE;

    public static Employee getEMPLOYEE() {
        return EMPLOYEE;
    }

    public static void setEMPLOYEE(Employee EMPLOYEE) {
        Constants.EMPLOYEE = EMPLOYEE;
    }

}
