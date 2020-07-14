/*
 * Created by Dmitry Garmyshev on 14.07.20 14:13
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 14:13
 *
 */

package com.example.vladlinkexam.session;

public class Session {

    //Номер телефона который был использован при авторизации в этой сессии
    private String phoneNumber;

    //Код который запрашивался для СМС
    private long requestID;

    private long uid;

    //Код пришедший в СМС (согласно ТЗ, он всегда равен 1234)
    private String autorizationCode = "1234";

    //Токен доступа к текущей сессии
    private String authToken;

    //Имя текущего пользователя авторизованного в системе
    private String currentUserName;

    private long[] publicUids;

    private String name;

    public Session(){}

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

    public String getAutorizationCode() {
        return autorizationCode;
    }

    public void setAutorizationCode(String autorizationCode) {
        this.autorizationCode = autorizationCode;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getCurrentUserName() {
        return currentUserName;
    }

    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }

    public long[] getPublicUids() {
        return publicUids;
    }

    public void setPublicUids(long[] publicUids) {
        this.publicUids = publicUids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
}

