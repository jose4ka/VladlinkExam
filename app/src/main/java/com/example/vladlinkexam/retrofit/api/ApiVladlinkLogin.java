/*
 * Created by Dmitry Garmyshev on 14.07.20 14:30
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 14:30
 *
 */

/*
Методы для работы с API логина
 */
package com.example.vladlinkexam.retrofit.api;

import com.example.vladlinkexam.model.login.phoneNumber.MPhoneNumberData;
import com.example.vladlinkexam.model.login.requestID.MRequestCodeData;
import com.example.vladlinkexam.model.login.smsQuery.MSMSQueryData;
import com.example.vladlinkexam.model.login.smsResponce.MSMSResponceData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;



public interface ApiVladlinkLogin {

    //Отдельные префиксы для удобного использования их в запросах

    //Префикс для получения кода
    public static final String PREFIX_CODE_REQUEST = "/v1/auth/subscribers/authByCode/request";

    //Префикс для проверки кода
    public static final String PREFIX_CODE_CHECK = "/v1/auth/subscribers/authByCode/check";



    @POST(PREFIX_CODE_REQUEST)
    @Headers("Content-Type: application/json")
    Call<MRequestCodeData> getCode(@Body MPhoneNumberData mDataPhoneNumber);

    @POST(PREFIX_CODE_CHECK)
    @Headers("Content-Type: application/json")
    Call<MSMSResponceData> checkSMS(@Body MSMSQueryData msmsQueryData);


}
