/*
 * Created by Dmitry Garmyshev on 14.07.20 14:09
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 14:09
 *
 */

package com.example.vladlinkexam.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.vladlinkexam.R;
import com.example.vladlinkexam.fragments.accounts.FragmentAccountInfo;
import com.example.vladlinkexam.fragments.accounts.FragmentAccountsList;
import com.example.vladlinkexam.interfaces.InterfaceSessionActivity;
import com.example.vladlinkexam.model.accounts.accountsList.MAccountsListData;
import com.example.vladlinkexam.model.accounts.oneAccount.MSingleAccount;
import com.example.vladlinkexam.model.accounts.oneAccount.MSingleAccountData;
import com.example.vladlinkexam.retrofit.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SessionActivity extends AppCompatActivity implements InterfaceSessionActivity {

    private static final String LOG_TAG = "SESSION_ACTIVITY";

    private FragmentAccountsList fragmentAccountsList;
    private FragmentAccountInfo fragmentAccountInfo;
    private FragmentTransaction fragmentTransaction;


    private String currentToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        currentToken = getIntent().getStringExtra("token");

        showAccountsList();

    }




    @Override
    public void showAccountInfo(long id) {
        fragmentAccountInfo = new FragmentAccountInfo(currentToken, id, this);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameAccountsMain, fragmentAccountInfo);
        fragmentTransaction.commit();
    }

    @Override
    public void showAccountsList() {
        fragmentAccountsList = new FragmentAccountsList(currentToken, this);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameAccountsMain, fragmentAccountsList);
        fragmentTransaction.commit();
    }



    @Override
    public List<MAccountsListData> getAccountsList() {

        NetworkService.getInstance()
                .getAccountsApi()
                .getAccounts(currentToken)
                .enqueue(new Callback<MAccountsListData>() {
                    @Override
                    public void onResponse(Call<MAccountsListData> call, Response<MAccountsListData> response) {
                        try {
                            Log.i(LOG_TAG, call.request().toString());
                            if(response.body() != null){
                                Log.i(LOG_TAG, "Получен список аккаунтов!");
                                Log.i(LOG_TAG, "List data: "+response.body().getData().get(0).getId());
                                Log.i(LOG_TAG, "List data: "+response.body().getData().get(0).getEmail());
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

        return null;
    }


    @Override
    public MSingleAccount getSelectedAccount() {

        NetworkService.getInstance()
                .getAccountsApi()
                .getAccountData(currentToken, 150396l).
                enqueue(new Callback<MSingleAccountData>() {
                    @Override
                    public void onResponse(Call<MSingleAccountData> call, Response<MSingleAccountData> response) {
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
                    public void onFailure(Call<MSingleAccountData> call, Throwable t) {

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

        return null;
    }


}