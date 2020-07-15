/*
 * Created by Dmitry Garmyshev on 15.07.20 15:26
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 15.07.20 15:09
 *
 */

package com.example.vladlinkexam.model.accounts.subclasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MTariffCurrent {

    @SerializedName("tid")
    @Expose
    private long tid;

    @SerializedName("tname")
    @Expose
    private String tname;

    @SerializedName("abonpay")
    @Expose
    private float abonpay;

    @SerializedName("max_credit")
    @Expose
    private float maxCredit;

    public MTariffCurrent(){}

    public long getTid() {
        return tid;
    }

    public void setTid(long tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public float getAbonpay() {
        return abonpay;
    }

    public void setAbonpay(float abonpay) {
        this.abonpay = abonpay;
    }

    public float getMaxCredit() {
        return maxCredit;
    }

    public void setMaxCredit(float maxCredit) {
        this.maxCredit = maxCredit;
    }
}
