/*
 * Created by Dmitry Garmyshev on 14.07.20 14:13
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 14:13
 *
 */

/*
Отдельный класс - контейнер, для более удобного хренения переменных и данных о текщей сессии
 */

package com.example.vladlinkexam.session;


public class Session {

    private String phoneNumber; //Номер телефона который используется для авторизации в этой сессии
    private long requestID; //Код который запрашивается для СМС
    private long uid; //Тип пользователя
    private String authCode; //Код пришедший в СМС
    private String authToken; //Токен доступа в текущей сессии
    private String currentUserName; //Имя текущего пользователя авторизованного в системе
    private long[] publicUids; //Доступные ID абон. счетов

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

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
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

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
}

