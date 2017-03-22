package com.example.ravivats.worknopsysmobile.domain;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Authorization implements Serializable
{

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("adm_emp_cust_create")
    @Expose
    private Boolean admEmpCustCreate;
    @SerializedName("adm_emp_cust_view")
    @Expose
    private Boolean admEmpCustView;
    @SerializedName("adm_emp_proj_create")
    @Expose
    private Boolean admEmpProjCreate;
    @SerializedName("adm_emp_proj_view")
    @Expose
    private Boolean admEmpProjView;
    @SerializedName("adm_wo_create")
    @Expose
    private Boolean admWoCreate;
    @SerializedName("adm_wo_view")
    @Expose
    private Boolean admWoView;
    @SerializedName("adm_wo_edit")
    @Expose
    private Boolean admWoEdit;
    @SerializedName("adm_wo_delete")
    @Expose
    private Boolean admWoDelete;
    @SerializedName("auth_pass")
    @Expose
    private Boolean authPass;
    @SerializedName("auth_pin")
    @Expose
    private Boolean authPin;
    @SerializedName("hours_add")
    @Expose
    private Boolean hoursAdd;
    @SerializedName("hours_edit")
    @Expose
    private Boolean hoursEdit;
    @SerializedName("hours_member_QRscan")
    @Expose
    private Boolean hoursMemberQRscan;
    @SerializedName("wo_show_all")
    @Expose
    private Boolean woShowAll;
    @SerializedName("wo_sort_distance")
    @Expose
    private Boolean woSortDistance;
    @SerializedName("__v")
    @Expose
    private Integer v;
    private final static long serialVersionUID = 6881057308372463329L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Boolean getAdmEmpCustCreate() {
        return admEmpCustCreate;
    }

    public void setAdmEmpCustCreate(Boolean admEmpCustCreate) {
        this.admEmpCustCreate = admEmpCustCreate;
    }

    public Boolean getAdmEmpCustView() {
        return admEmpCustView;
    }

    public void setAdmEmpCustView(Boolean admEmpCustView) {
        this.admEmpCustView = admEmpCustView;
    }

    public Boolean getAdmEmpProjCreate() {
        return admEmpProjCreate;
    }

    public void setAdmEmpProjCreate(Boolean admEmpProjCreate) {
        this.admEmpProjCreate = admEmpProjCreate;
    }

    public Boolean getAdmEmpProjView() {
        return admEmpProjView;
    }

    public void setAdmEmpProjView(Boolean admEmpProjView) {
        this.admEmpProjView = admEmpProjView;
    }

    public Boolean getAdmWoCreate() {
        return admWoCreate;
    }

    public void setAdmWoCreate(Boolean admWoCreate) {
        this.admWoCreate = admWoCreate;
    }

    public Boolean getAdmWoView() {
        return admWoView;
    }

    public void setAdmWoView(Boolean admWoView) {
        this.admWoView = admWoView;
    }

    public Boolean getAdmWoEdit() {
        return admWoEdit;
    }

    public void setAdmWoEdit(Boolean admWoEdit) {
        this.admWoEdit = admWoEdit;
    }

    public Boolean getAdmWoDelete() {
        return admWoDelete;
    }

    public void setAdmWoDelete(Boolean admWoDelete) {
        this.admWoDelete = admWoDelete;
    }

    public Boolean getAuthPass() {
        return authPass;
    }

    public void setAuthPass(Boolean authPass) {
        this.authPass = authPass;
    }

    public Boolean getAuthPin() {
        return authPin;
    }

    public void setAuthPin(Boolean authPin) {
        this.authPin = authPin;
    }

    public Boolean getHoursAdd() {
        return hoursAdd;
    }

    public void setHoursAdd(Boolean hoursAdd) {
        this.hoursAdd = hoursAdd;
    }

    public Boolean getHoursEdit() {
        return hoursEdit;
    }

    public void setHoursEdit(Boolean hoursEdit) {
        this.hoursEdit = hoursEdit;
    }

    public Boolean getHoursMemberQRscan() {
        return hoursMemberQRscan;
    }

    public void setHoursMemberQRscan(Boolean hoursMemberQRscan) {
        this.hoursMemberQRscan = hoursMemberQRscan;
    }

    public Boolean getWoShowAll() {
        return woShowAll;
    }

    public void setWoShowAll(Boolean woShowAll) {
        this.woShowAll = woShowAll;
    }

    public Boolean getWoSortDistance() {
        return woSortDistance;
    }

    public void setWoSortDistance(Boolean woSortDistance) {
        this.woSortDistance = woSortDistance;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}