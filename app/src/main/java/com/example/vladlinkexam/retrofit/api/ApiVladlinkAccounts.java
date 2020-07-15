/*
 * Created by Dmitry Garmyshev on 14.07.20 14:52
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 14:52
 *
 */


/*
Методы для работы с API счетов
 */

package com.example.vladlinkexam.retrofit.api;


import com.example.vladlinkexam.model.accounts.accountsList.MAccountsListData;
import com.example.vladlinkexam.model.accounts.singleAccount.MSingleAccountData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ApiVladlinkAccounts {

    //Отдельный префикс для удобного использования ег ов запросах
    public static final String PREFIX= "/v1/public/users";

    @GET(PREFIX+"/my")
    @Headers("Content-Type: application/json")
    Call<MAccountsListData> getAccounts(@Header( "Authorization") String token);

    @GET(PREFIX+"/{account_id}")
    @Headers("Content-Type: application/json")
    Call<MSingleAccountData> getAccountData(@Header( "Authorization") String token
    ,@Path("account_id") long id);
}
