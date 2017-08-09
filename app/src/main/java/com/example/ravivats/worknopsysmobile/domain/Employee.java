package com.example.ravivats.worknopsysmobile.domain;


import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Employee implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("ValidFrom")
    @Expose
    private String validFrom;
    @SerializedName("ValidTill")
    @Expose
    private String validTill;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("Profession")
    @Expose
    private String profession;
    @SerializedName("Bank")
    @Expose
    private String bank;
    @SerializedName("BankCode")
    @Expose
    private String bankCode;
    @SerializedName("BankAccount")
    @Expose
    private String bankAccount;
    @SerializedName("AccountHolder")
    @Expose
    private String accountHolder;
    @SerializedName("Currency")
    @Expose
    private String currency;
    @SerializedName("WageIndex")
    @Expose
    private String wageIndex;
    @SerializedName("EMPLocation")
    @Expose
    private String eMPLocation;
    @SerializedName("Phone")
    @Expose
    private String phone;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("Nationality")
    @Expose
    private String nationality;
    @SerializedName("DateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Comments")
    @Expose
    private String comments;
    @SerializedName("FileName")
    @Expose
    private String fileName;
    @SerializedName("__v")
    @Expose
    private Integer v;
    private final static long serialVersionUID = -4891984686574686224L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public String getValidTill() {
        return validTill;
    }

    public void setValidTill(String validTill) {
        this.validTill = validTill;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getWageIndex() {
        return wageIndex;
    }

    public void setWageIndex(String wageIndex) {
        this.wageIndex = wageIndex;
    }

    public String getEMPLocation() {
        return eMPLocation;
    }

    public void setEMPLocation(String eMPLocation) {
        this.eMPLocation = eMPLocation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}



