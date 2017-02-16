
package com.example.ravivats.worknopsysmobile.domain;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer implements Serializable
{

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("DebitorNumber")
    @Expose
    private String debitorNumber;
    @SerializedName("CreationDate")
    @Expose
    private String creationDate;
    @SerializedName("EditedOn")
    @Expose
    private String editedOn;
    @SerializedName("EditedBy")
    @Expose
    private String editedBy;
    @SerializedName("Salutation")
    @Expose
    private String salutation;
    @SerializedName("Phone")
    @Expose
    private String phone;
    @SerializedName("Fax")
    @Expose
    private String fax;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("BankDetails")
    @Expose
    private BankDetails bankDetails;
    @SerializedName("Postbox")
    @Expose
    private Postbox postbox;
    @SerializedName("Address")
    @Expose
    private Address address;
    private final static long serialVersionUID = 8794078614135213254L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDebitorNumber() {
        return debitorNumber;
    }

    public void setDebitorNumber(String debitorNumber) {
        this.debitorNumber = debitorNumber;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getEditedOn() {
        return editedOn;
    }

    public void setEditedOn(String editedOn) {
        this.editedOn = editedOn;
    }

    public String getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public BankDetails getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(BankDetails bankDetails) {
        this.bankDetails = bankDetails;
    }

    public Postbox getPostbox() {
        return postbox;
    }

    public void setPostbox(Postbox postbox) {
        this.postbox = postbox;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
