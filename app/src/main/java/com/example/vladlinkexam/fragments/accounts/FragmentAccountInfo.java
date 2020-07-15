/*
 * Created by Dmitry Garmyshev on 15.07.20 19:33
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 15.07.20 19:33
 *
 */

package com.example.vladlinkexam.fragments.accounts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.vladlinkexam.R;
import com.example.vladlinkexam.interfaces.InterfaceSessionActivity;
import com.example.vladlinkexam.model.accounts.oneAccount.MSingleAccount;
import com.example.vladlinkexam.model.accounts.oneAccount.MSingleAccountData;
import com.example.vladlinkexam.model.accounts.subclasses.MAddress;
import com.example.vladlinkexam.retrofit.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentAccountInfo extends Fragment {

    private static final String LOG_TAG = "SESSION_ACTIVITY";

    private long selectedId;
    private String token;

    private TextView tvIAccountId
            ,tvIAccountEmail
            ,tvIAccountULogin
            ,tvIAccountFullName
            ,tvIAccountBill
            ,tvIAccountBlock
            ,tvIAccountCBlock
            ,tvIAccountTariff
            ,tvIAccountTariffNext
            ,tvIAccountTariffCurrentName
            ,tvIAccountTariffCurrentId
            ,tvIAccountTariffCurrentAbonPay
            ,tvIAccountTariffCurrentMaxCredit
            ,tvIAccountIsJuridical
            ,tvIAccountIsSMS
            ,tvIAccountCityId
            ,tvIAccountSkidko
            ,tvIAccountBalls
            ,tvIAccountCredit
            ,tvIAccountIsPhoneUser
            ,tvIAccountUCStatus;

    private List<MAddress> listAddresses;

    private Button btnIAccountBack;

    private View mainView;

    private InterfaceSessionActivity interfaceSessionActivity;

    public FragmentAccountInfo(String token, long selectedId, InterfaceSessionActivity interfaceSessionActivity) {
        this.token = token;
        this.selectedId = selectedId;
        this.interfaceSessionActivity = interfaceSessionActivity;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_account_info, container, false);

        initializeScreenElements();
        getAccountData(selectedId);
        return mainView;
    }

    private void initializeScreenElements(){
        tvIAccountEmail = mainView.findViewById(R.id.tvIAccountEmail);
        tvIAccountFullName = mainView.findViewById(R.id.tvIAccountFullName);
        tvIAccountId = mainView.findViewById(R.id.tvIAccountId);
        tvIAccountULogin = mainView.findViewById(R.id.tvIAccountULogin);
        tvIAccountBalls = mainView.findViewById(R.id.tvIAccountBalls);
        tvIAccountBill = mainView.findViewById(R.id.tvIAccountBill);
        tvIAccountBlock = mainView.findViewById(R.id.tvIAccountBlock);
        tvIAccountCBlock = mainView.findViewById(R.id.tvIAccountCBlock);
        tvIAccountTariff = mainView.findViewById(R.id.tvIAccountTariff);
        tvIAccountTariffNext = mainView.findViewById(R.id.tvIAccountTariffNext);
        tvIAccountTariffCurrentName = mainView.findViewById(R.id.tvIAccountTariffCurrentName);
        tvIAccountTariffCurrentId = mainView.findViewById(R.id.tvIAccountTariffCurrentId);
        tvIAccountTariffCurrentAbonPay = mainView.findViewById(R.id.tvIAccountTariffCurrentAbonPay);
        tvIAccountTariffCurrentMaxCredit = mainView.findViewById(R.id.tvIAccountTariffCurrentMaxCredit);
        tvIAccountIsJuridical = mainView.findViewById(R.id.tvIAccountIsJuridical);
        tvIAccountIsSMS = mainView.findViewById(R.id.tvIAccountIsSMS);
        tvIAccountCityId = mainView.findViewById(R.id.tvIAccountCityId);
        tvIAccountSkidko = mainView.findViewById(R.id.tvIAccountSkidko);
        tvIAccountCredit = mainView.findViewById(R.id.tvIAccountCredit);
        tvIAccountIsPhoneUser = mainView.findViewById(R.id.tvIAccountIsPhoneUser);
        tvIAccountUCStatus = mainView.findViewById(R.id.tvIAccountUCStatus);

        btnIAccountBack = mainView.findViewById(R.id.btnIAccountBack);
        btnIAccountBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceSessionActivity.showAccountsList();
            }
        });
    }


    private void getAccountData(long id){
        NetworkService.getInstance()
                .getAccountsApi()
                .getAccountData(token, id).
                enqueue(new Callback<MSingleAccountData>() {
                    @Override
                    public void onResponse(Call<MSingleAccountData> call, Response<MSingleAccountData> response) {
                        try {
                            Log.i(LOG_TAG, call.request().toString());
                            if(response.body() != null){
                                Log.i(LOG_TAG, "Получен отдельный аккаунт!");
                                Log.i(LOG_TAG, Long.toString(response.body().getData().getId()));
                                Log.i(LOG_TAG, response.body().getData().getTariffCurrent().getTname());
                                Log.i(LOG_TAG, response.body().getData().getFullName());
                                Log.i(LOG_TAG, response.body().getData().getuLogin());


                                fillScreenFields(response.body().getData());

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
    }


    private void fillScreenFields(MSingleAccount lSingleAccount){
        tvIAccountEmail.setText(lSingleAccount.getEmail());
        tvIAccountFullName.setText(lSingleAccount.getFullName());
        tvIAccountId.setText(Long.toString(lSingleAccount.getId()));
        tvIAccountULogin.setText(lSingleAccount.getuLogin());
        tvIAccountBalls.setText(Float.toString(lSingleAccount.getBalls()));
        tvIAccountBill.setText(Double.toString(lSingleAccount.getBill()));
        tvIAccountBlock.setText(lSingleAccount.isBlock() ? "Заблокированно" : "Не заблокированно");
        tvIAccountCBlock.setText(lSingleAccount.isCblock() ? "cblock" : "не cblock");
        tvIAccountTariff.setText(Float.toString(lSingleAccount.getTariff()));
        tvIAccountTariffNext.setText(Float.toString(lSingleAccount.getTariffNext()));
        tvIAccountTariffCurrentName.setText(lSingleAccount.getTariffCurrent().getTname());
        tvIAccountTariffCurrentId.setText(Long.toString(lSingleAccount.getTariffCurrent().getTid()));
        tvIAccountTariffCurrentAbonPay.setText(Float.toString(lSingleAccount.getTariffCurrent().getAbonpay()));
        tvIAccountTariffCurrentMaxCredit.setText(Float.toString(lSingleAccount.getTariffCurrent().getMaxCredit()));
        tvIAccountIsJuridical.setText(lSingleAccount.isJuridical() ? "Юридическое лицо" : "Частное лицо");
        tvIAccountIsSMS.setText(lSingleAccount.getIsSMS());
        tvIAccountCityId.setText(Long.toString(lSingleAccount.getCityId()));
        tvIAccountSkidko.setText(Float.toString(lSingleAccount.getSkidko()));
        tvIAccountCredit.setText(Float.toString(lSingleAccount.getCredit()));
        tvIAccountIsPhoneUser.setText(lSingleAccount.isPhoneUser() ? "Is phone user = true" : "Is phone user = false");
        tvIAccountUCStatus.setText(Integer.toString(lSingleAccount.getUcStatus()));
    }
}