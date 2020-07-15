/*
 * Created by Dmitry Garmyshev on 14.07.20 20:11
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 19:55
 *
 */

/*
Data бертка для модели ответа на номер телефона
 */
package com.example.vladlinkexam.model.login.requestID;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MRequestCodeData {

    @SerializedName("data")
    @Expose
    public MRequestID requestID;

    public MRequestCodeData(){}

    public MRequestID getRequestID() {
        return requestID;
    }

    public void setRequestID(MRequestID requestID) {
        this.requestID = requestID;
    }
}
