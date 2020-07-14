/*
 * Created by Dmitry Garmyshev on 14.07.20 14:52
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 14:52
 *
 */

package com.example.vladlinkexam.retrofit.api;


import android.widget.CalendarView;

import com.example.vladlinkexam.model.accounts.MAccount;
import com.example.vladlinkexam.model.accounts.MAccountsListData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiVladlinkAccounts {
    public static final String PREFIX= "/v1/public/users";

    @GET(PREFIX+"/my")
    @Headers("Content-Type: application/json")
    Call<String> getAccounts(@Header( "Authorization") String token);

    @GET(PREFIX+"/{account_id}")
    @Headers("Content-Type: application/json")
    Call<String> getAccountData(@Header( "Authorization") String token
    , @Path("account_id") long id);
}
