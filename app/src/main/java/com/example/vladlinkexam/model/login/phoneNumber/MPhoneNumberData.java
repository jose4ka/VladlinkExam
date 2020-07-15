/*
 * Created by Dmitry Garmyshev on 14.07.20 20:11
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 20:08
 *
 */

//Используем агрегацию
//"data" обёртка для модели MPhoneData

package com.example.vladlinkexam.model.login.phoneNumber;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MPhoneNumberData {

    @SerializedName("data")
    @Expose
    public MPhoneNumber data;


    public MPhoneNumberData(){}

    public MPhoneNumber getData() {
        return data;
    }

    public void setData(MPhoneNumber data) {
        this.data = data;
    }

}
