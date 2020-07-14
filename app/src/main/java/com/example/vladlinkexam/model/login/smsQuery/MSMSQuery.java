/*
 * Created by Dmitry Garmyshev on 14.07.20 20:10
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 20:09
 *
 */

package com.example.vladlinkexam.model.login.smsQuery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//Модель запроса для авторизации по СМС-коду
public class MSMSQuery {

    @SerializedName("phone")
    @Expose
    private String phoneNumber;

    @SerializedName("request_id")
    @Expose
    private long requestID;

    @SerializedName("code")
    @Expose
    private String code;

    public MSMSQuery(){}

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getRequestID() {
        return requestID;
    }

    public void setRequestID(long requestID) {
        this.requestID = requestID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
