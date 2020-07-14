/*
 * Created by Dmitry Garmyshev on 14.07.20 22:23
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 22:23
 *
 */

package com.example.vladlinkexam.model.accounts;

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
}
