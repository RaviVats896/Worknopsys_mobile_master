
package com.example.ravivats.worknopsysmobile.domain;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BankDetails implements Serializable {

    @SerializedName("Bank")
    @Expose
    private String bank;
    @SerializedName("BankCode")
    @Expose
    private String bankCode;
    @SerializedName("BankAccount")
    @Expose
    private String bankAccount;
    @SerializedName("BankUser")
    @Expose
    private String bankUser;
    @SerializedName("IBAN")
    @Expose
    private String iBAN;
    @SerializedName("CountryCode")
    @Expose
    private String countryCode;
    @SerializedName("Currency")
    @Expose
    private String currency;
    private final static long serialVersionUID = -580367207848083036L;

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

    public String getBankUser() {
        return bankUser;
    }

    public void setBankUser(String bankUser) {
        this.bankUser = bankUser;
    }

    public String getIBAN() {
        return iBAN;
    }

    public void setIBAN(String iBAN) {
        this.iBAN = iBAN;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
