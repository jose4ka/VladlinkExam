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
import com.example.vladlinkexam.interfaces.InterfaceSessionActivity;
import com.example.vladlinkexam.model.accounts.accountsList.MAccountsListData;
import com.example.vladlinkexam.model.accounts.oneAccount.MOneAccount;
import com.example.vladlinkexam.model.accounts.oneAccount.MOneAccountData;
import com.example.vladlinkexam.retrofit.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SessionActivity extends AppCompatActivity implements InterfaceSessionActivity {

    private static final String LOG_TAG = "SESSION_ACTIVITY";

    private String currentToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        currentToken = getIntent().getStringExtra("token");

        testAccounts(currentToken);
    }


    private void testAccounts(String token){
        NetworkService.getInstance()
                .getAccountsApi()
                .getAccounts(token)
                .enqueue(new Callback<MAccountsListData>() {
                    @Override
                    public void onResponse(Call<MAccountsListData> call, Response<MAccountsListData> response) {
                        try {
                            Log.i(LOG_TAG, call.request().toString());
                            if(response.body() != null){
                                Log.i(LOG_TAG, "Получен список аккаунтов!");
                                Log.i(LOG_TAG, "List data: "+response.body().getData().get(0).getId());
                                Log.i(LOG_TAG, "List data: "+response.body().getData().get(0).getEmail());

                                getAccountInfo();
                            }
                            else {
                                Log.i(LOG_TAG, "Пустой список аккаунтов!");
                                Log.i(LOG_TAG, response.errorBody().string());
                            }
                        }
                        catch (Exception e){

                        }

                    }

                    @Override
                    public void onFailure(Call<MAccountsListData> call, Throwable t) {
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
                enqueue(new Callback<MOneAccountData>() {
                    @Override
                    public void onResponse(Call<MOneAccountData> call, Response<MOneAccountData> response) {
                        try {
                            Log.i(LOG_TAG, call.request().toString());
                            if(response.body() != null){
                                Log.i(LOG_TAG, "Получен отдельный аккаунт!");
                                Log.i(LOG_TAG, "Data: "+response.body().getData().getId());
                                Log.i(LOG_TAG, "Data: "+response.body().getData().getEmail());
                            }
                            else {
                                Log.i(LOG_TAG, "Пустой аккаунт!");
                                Log.i(LOG_TAG, response.errorBody().string());
                            }
                        }
                        catch (Exception e){

                        }
                    }

                    @Override
                    public void onFailure(Call<MOneAccountData> call, Throwable t) {

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

    @Override
    public List<MAccountsListData> getAccountsList(String token) {
        return null;
    }

    @Override
    public MOneAccount getSelectedAccount(String token) {
        return null;
    }
}