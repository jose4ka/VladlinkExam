/*
 * Created by Dmitry Garmyshev on 14.07.20 20:09
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 19:55
 *
 */

package com.example.vladlinkexam.model.login.smsResponce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//Модель ответа для авторизации по SMS-коду
public class MSMSResponce {

    @SerializedName("uid")
    @Expose
    public long uId;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("auth_token")
    @Expose
    public String token;

    @SerializedName("public_uids")
    @Expose
    public long[] publicUids;

    public MSMSResponce(){}

    public long getuId() {
        return uId;
    }

    public void setuId(long uId) {
        this.uId = uId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long[] getPublicUids() {
        return publicUids;
    }

    public void setPublicUids(long[] publicUids) {
        this.publicUids = publicUids;
    }
}
