/*
 * Created by Dmitry Garmyshev on 14.07.20 14:09
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 14:09
 *
 */

package com.example.vladlinkexam.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.vladlinkexam.R;
import com.example.vladlinkexam.model.accounts.MAccount;
import com.example.vladlinkexam.retrofit.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SessionActivity extends AppCompatActivity {

    private static final String LOG_TAG = "SESSION_ACTIVITY";

    private String currentToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        currentToken = getIntent().getStringExtra("token");

        testAccounts(currentToken, 1);
    }


    private void testAccounts(String token, long id){
        NetworkService.getInstance()
                .getAccountsApi()
                .getAccounts(token)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        try {
                            Log.i(LOG_TAG, call.request().toString());
                            if(response.body() != null){
                                Log.i(LOG_TAG, "SUCCESS!");
                                Log.i(LOG_TAG, response.body());

                                getAccountInfo();
                            }
                            else {
                                Log.i(LOG_TAG, "NULL BODY!");
                                Log.i(LOG_TAG, response.errorBody().string());
                            }
                        }
                        catch (Exception e){

                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        try {
                            Log.i(LOG_TAG, "FAILURE!");
                            Log.i(LOG_TAG, call.request().toString());
                            Log.i(LOG_TAG, t.getMessage());
                        }
                        catch (Exception e){
                            Log.i(LOG_TAG, e.getMessage());
                        }

                    }
                });
    }


    private void getAccountInfo(){
        NetworkService.getInstance()
                .getAccountsApi()
                .getAccountData(currentToken, 150396l).
                enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        try {
                            Log.i(LOG_TAG, call.request().toString());
                            if(response.body() != null){
                                Log.i(LOG_TAG, "SUCCESS!");
                                Log.i(LOG_TAG, response.body());
                            }
                            else {
                                Log.i(LOG_TAG, "NULL BODY!");
                                Log.i(LOG_TAG, response.errorBody().string());
                            }
                        }
                        catch (Exception e){

                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                        try {
                            Log.i(LOG_TAG, "FAILURE!");
                            Log.i(LOG_TAG, call.request().toString());
                            Log.i(LOG_TAG, t.getMessage());
                        }
                        catch (Exception e){
                            Log.i(LOG_TAG, e.getMessage());
                        }
                    }
                });
    }
}