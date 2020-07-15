/*
 * Created by Dmitry Garmyshev on 15.07.20 19:33
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 15.07.20 19:33
 *
 */

package com.example.vladlinkexam.fragments.accounts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vladlinkexam.R;
import com.example.vladlinkexam.adapters.AdapterAccounts;
import com.example.vladlinkexam.interfaces.InterfaceSessionActivity;
import com.example.vladlinkexam.model.accounts.accountsList.MAccount;
import com.example.vladlinkexam.model.accounts.accountsList.MAccountsListData;
import com.example.vladlinkexam.retrofit.NetworkService;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentAccountsList extends Fragment implements AdapterAccounts.CallbackBtn {

    private static final String LOG_TAG = "SESSION_ACTIVITY";


    private AdapterAccounts adapterAccounts;
    private RecyclerView rvAccountsList;
    private List<MAccount> accountsList;

    private View mainView;

    private String authToken;

    private InterfaceSessionActivity interfaceSessionActivity;



    public FragmentAccountsList(String token, InterfaceSessionActivity interfaceSessionActivity){
        this.interfaceSessionActivity = interfaceSessionActivity;
        this.authToken = token;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_accounts_list, container, false);
        initializeScreenElements();
        getAccountsFromServer(authToken, this);
        return mainView;
    }



    private void initializeScreenElements(){
        rvAccountsList = mainView.findViewById(R.id.rvAccountsList);
    }


    private void getAccountsFromServer(String token, AdapterAccounts.CallbackBtn lCallBackBtn){
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
                                Log.i(LOG_TAG, Integer.toString(response.body().getData().size()));

                                accountsList = response.body().getData();

                                adapterAccounts = new AdapterAccounts(getContext(), lCallBackBtn, accountsList);
                                rvAccountsList.setAdapter(adapterAccounts);
                                rvAccountsList.setLayoutManager(new LinearLayoutManager(getContext()));
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


    @Override
    public void selected(long accountId) {
        Toast.makeText(getContext(), "SELECTED",Toast.LENGTH_SHORT).show();
        interfaceSessionActivity.showAccountInfo(accountId);
    }



}