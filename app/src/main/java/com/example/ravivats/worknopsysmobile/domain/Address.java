
package com.example.ravivats.worknopsysmobile.domain;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address implements Serializable {

    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Postcode")
    @Expose
    private String postcode;
    @SerializedName("CountryCode")
    @Expose
    private String countryCode;
    private final static long serialVersionUID = -1604354746111975648L;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

}
