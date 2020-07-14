/*
 * Created by Dmitry Garmyshev on 14.07.20 20:08
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 19:55
 *
 */

package com.example.vladlinkexam.model.login.phoneNumber;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//Модель для отправки номера телефона на сервер
public class MPhoneNumber {


    @SerializedName("phone")
    @Expose
    private String phoneNumber;

    public MPhoneNumber(){}

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
