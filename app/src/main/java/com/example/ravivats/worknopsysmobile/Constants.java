package com.example.ravivats.worknopsysmobile;

import android.support.annotation.BoolRes;

import com.example.ravivats.worknopsysmobile.domain.Authorization;
import com.example.ravivats.worknopsysmobile.domain.Employee;
import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Constants {

    final static String LOGOUT_URL = "http://worknopsys.ml/api/employees/logout";
    private static Boolean posLocation;
    private static Boolean projEnhance;
    private static Employee EMPLOYEE;
    private static Authorization AUTH;
    private static JSONArray CUSTOMERS;
    private static String evidenceGTime1;
    private static String evidenceGTime2;
    private static String evidenceWTime1;
    private static String evidenceWTime2;
    private static String evidenceBTime1;
    private static String evidenceBTime2;
    private static String evidenceRTime1;
    private static String evidenceRTime2;

    public static String getEvidenceRTime2() { return evidenceRTime2; }

    public static void setEvidenceRTime2(String evidenceRTime2) { Constants.evidenceRTime2 = evidenceRTime2; }

    public static String getEvidenceBTime1() { return evidenceBTime1; }

    public static void setEvidenceBTime1(String evidenceBTime1) { Constants.evidenceBTime1 = evidenceBTime1; }

    public static String getEvidenceRTime1() { return evidenceRTime1; }

    public static void setEvidenceRTime1(String evidenceRTime1) { Constants.evidenceRTime1 = evidenceRTime1; }

    public static String getEvidenceBTime2() { return evidenceBTime2; }

    public static void setEvidenceBTime2(String evidenceBTime2) { Constants.evidenceBTime2 = evidenceBTime2; }

    public static String getEvidenceWTime2() { return evidenceWTime2; }

    public static void setEvidenceWTime2(String evidenceWTime2) { Constants.evidenceWTime2 = evidenceWTime2; }

    public static String getEvidenceWTime1() { return evidenceWTime1; }

    public static void setEvidenceWTime1(String evidenceWTime1) { Constants.evidenceWTime1 = evidenceWTime1; }

    public static String getEvidenceGTime2() { return evidenceGTime2; }

    public static void setEvidenceGTime2(String evidenceGTime2) { Constants.evidenceGTime2 = evidenceGTime2; }

    public static String getEvidenceGTime1() { return evidenceGTime1; }

    public static void setEvidenceGTime1(String evidenceGTime1) { Constants.evidenceGTime1 = evidenceGTime1; }

    public static JSONArray getCUSTOMERS() {
        return CUSTOMERS;
    }

    public static Boolean getProjEnhance() { return projEnhance; }

    public static void setProjEnhance(Boolean projEnhance) { Constants.projEnhance = projEnhance;}

    public static Boolean getPosLocation() { return posLocation; }

    public static void setPosLocation(Boolean posLocation) { Constants.posLocation = posLocation; }

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
