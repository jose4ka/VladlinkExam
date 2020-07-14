/*
 * Created by Dmitry Garmyshev on 14.07.20 16:27
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 16:27
 *
 */

package com.example.vladlinkexam.model.login.smsResponce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MSMSResponceData {

    @SerializedName("data")
    @Expose
    public com.example.vladlinkexam.model.login.smsResponce.MSMSResponce MSMSResponce;

    public MSMSResponceData(){}

    public com.example.vladlinkexam.model.login.smsResponce.MSMSResponce getMSMSResponce() {
        return MSMSResponce;
    }

    public void setMSMSResponce(com.example.vladlinkexam.model.login.smsResponce.MSMSResponce MSMSResponce) {
        this.MSMSResponce = MSMSResponce;
    }
}
