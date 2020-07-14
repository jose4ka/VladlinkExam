/*
 * Created by Dmitry Garmyshev on 14.07.20 20:09
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 19:55
 *
 */

package com.example.vladlinkexam.model.login.requestID;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//Модель ответа на отправленный номер телефона
public class MRequestID {

    @SerializedName("request_id")
    @Expose
    private long requestID;

    public MRequestID(){}

    public long getRequestID() {
        return requestID;
    }

    public void setRequestID(long requestID) {
        this.requestID = requestID;
    }
}
