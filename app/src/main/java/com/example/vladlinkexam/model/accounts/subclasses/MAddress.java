/*
 * Created by Dmitry Garmyshev on 15.07.20 15:26
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 22:13
 *
 */

package com.example.vladlinkexam.model.accounts.subclasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MAddress {

    @SerializedName("did")
    @Expose
    private String did;

    @SerializedName("descr")
    @Expose
    private String descr;

    @SerializedName("floor")
    @Expose
    private String floor = null;

    @SerializedName("porch")
    @Expose
    private String porch;

    @SerializedName("dom_name")
    @Expose
    private String domName;


    // Getter Methods

    public String getDid() {
        return did;
    }

    public String getDescr() {
        return descr;
    }

    public String getFloor() {
        return floor;
    }

    public String getPorch() {
        return porch;
    }

    public String getDomName() {
        return domName;
    }

    // Setter Methods

    public void setDid(String did) {
        this.did = did;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public void setPorch(String porch) {
        this.porch = porch;
    }

    public void setDomName(String domName) {
        this.domName = domName;
    }
}
