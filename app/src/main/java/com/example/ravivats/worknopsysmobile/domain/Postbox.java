
package com.example.ravivats.worknopsysmobile.domain;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Postbox implements Serializable {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Postcode")
    @Expose
    private String postcode;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("CountryCode")
    @Expose
    private String countryCode;
    private final static long serialVersionUID = -6604996499346420345L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

}
