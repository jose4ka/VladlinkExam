/*
 * Created by Dmitry Garmyshev on 15.07.20 19:33
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 15.07.20 19:33
 *
 */

/*
Фрагмент который выводит на экран всю подробную информацию о выбранном счёте

В конструктор он принимает токен для дальнейшего обращения к серверу а так-же id счёта, который нужно отобразить

Для связи с родительской активностью используется интерфейс, котоый так же передаётся в конструкторе
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vladlinkexam.R;
import com.example.vladlinkexam.adapters.AdapterAddresses;
import com.example.vladlinkexam.interfaces.InterfaceSessionActivity;
import com.example.vladlinkexam.model.accounts.singleAccount.MSingleAccount;
import com.example.vladlinkexam.model.accounts.singleAccount.MSingleAccountData;
import com.example.vladlinkexam.model.accounts.subclasses.MAddress;
import com.example.vladlinkexam.retrofit.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentAccountInfo extends Fragment {

    private static final String LOG_TAG = "SESSION_ACTIVITY";
    private static final String LOG_SPACE = "==============================";

    //Данные для обращения к серверу, получаем из конструктора
    private long selectedId;
    private String token;

    private InterfaceSessionActivity interfaceSessionActivity;

    private View mainView;

    //Все элементы на экране для каждого поля выбранного счёта
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

    private Button btnIAccountBack; //Кнопка "назад"

    private RecyclerView rvAddresses; //RV для отображения адресов

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

    /*
    Отдельный метод для инициализации элементов на экране
    в данном случае очень хорошо подходит :)
     */
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
                //Активность просто выставляет на экран другой фрагмент
                interfaceSessionActivity.showAccountsList();
            }
        });

        rvAddresses = mainView.findViewById(R.id.rvIAccountAddresses);
    }


    private void getAccountData(long id){
        NetworkService.getInstance()
                .getAccountsApi()
                .getAccountData(token, id).
                enqueue(new Callback<MSingleAccountData>() {
                    @Override
                    public void onResponse(Call<MSingleAccountData> call, Response<MSingleAccountData> response) {
                        try {
                            Log.i(LOG_TAG, LOG_SPACE);
                            Log.i(LOG_TAG, "Получение информации о счёте: "+id);
                            Log.i(LOG_TAG, call.request().toString());

                            if(response.body() != null){
                                Log.i(LOG_TAG, "Получен счёт: "+id);

                                //Извлекаем из тела ответа адреса, и сразу их выводим
                                List<MAddress> listAddresses = response.body().getData().getuMAddresses();
                                showAddresses(listAddresses);

                                //Заполняем все остальные поля на экране
                                fillScreenFields(response.body().getData());
                                Log.i(LOG_TAG, LOG_SPACE);
                            }
                            else {
                                Log.i(LOG_TAG, LOG_SPACE);
                                Log.i(LOG_TAG, "С сервера получен пустой объект, или возникла ошибка на стороне сервера");
                                Toast.makeText(getContext(), "С сервера получен пустой объект, или возникла ошибка на стороне сервера!", Toast.LENGTH_SHORT).show();

                                if(response.errorBody() != null){
                                    Log.e(LOG_TAG, response.errorBody().string()); //Выводим ошибку с сервера
                                    Log.i(LOG_TAG, LOG_SPACE);
                                }
                            }
                        }
                        catch (Exception e){
                            Toast.makeText(getContext(), "Ошибка при чтении полученного ответа!", Toast.LENGTH_SHORT).show();
                            Log.i(LOG_TAG, LOG_SPACE);
                            Log.e(LOG_TAG, "Ошибка при чтении полученного ответа: ");
                            Log.e(LOG_TAG, e.getMessage());
                            Log.i(LOG_TAG, LOG_SPACE);
                        }
                    }

                    @Override
                    public void onFailure(Call<MSingleAccountData> call, Throwable t) {
                        Toast.makeText(getContext(), "Ошибка при выполнении запроса к серверу!", Toast.LENGTH_SHORT).show();
                        Log.i(LOG_TAG, LOG_SPACE);
                        Log.e(LOG_TAG, "Ошибка при выполнении запроса к серверу");
                        Log.e(LOG_TAG, t.getMessage());
                        Log.i(LOG_TAG, LOG_SPACE);
                    }
                });
    }


    //Отдельный метод для вывода элеменов в RV
    private void showAddresses(List<MAddress> addresses){
        AdapterAddresses adapterAddresses = new AdapterAddresses(getContext(), addresses);
        rvAddresses.setAdapter(adapterAddresses);
        rvAddresses.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    //Отдельный метод для заполнения всех полей на экране
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