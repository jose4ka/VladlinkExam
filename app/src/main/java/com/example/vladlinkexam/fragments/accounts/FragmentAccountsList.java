/*
 * Created by Dmitry Garmyshev on 15.07.20 19:33
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 15.07.20 19:33
 *
 */

/*
Фрагмент используемый для отображения полученных с сервера абонентских счетов

Отличие от фрагментов авторизации - запросы к серверу делает сам фрагмент,
это гораздо упрощает работу с переключением фрагментов и передачей данных

Интерфейс "AdapterAccounts.CallbackBtn" родом из адаптера "AdapterAccounts"
и нужен он для того, чтобы установить связь с элементами из списка на экране (колбэк)

После выбора нужного нам элемента (счёта), через интерфейс мы выводим на экран следующий фрагмент
с подробной информацией об этом элемете (счёте)
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
    private static final String LOG_SPACE = "==============================";



    private View mainView;

    //Элементы необходимые для отображения списка
    private RecyclerView rvAccountsList;

    //токен из конструктора, для работы с сетью
    private String authToken;

    private InterfaceSessionActivity interfaceSessionActivity; //Интерфейс для связи с основной активностью

    public FragmentAccountsList(String token, InterfaceSessionActivity interfaceSessionActivity){
        this.interfaceSessionActivity = interfaceSessionActivity;
        this.authToken = token;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_accounts_list, container, false);
        initializeScreenElements();
        getAccountsFromServer(authToken); //Сразу же получаем список счетов
        return mainView;
    }



    private void initializeScreenElements(){
        rvAccountsList = mainView.findViewById(R.id.rvAccountsList);
    }


    private void getAccountsFromServer(String token){
        NetworkService.getInstance()
                .getAccountsApi()
                .getAccounts(token)
                .enqueue(new Callback<MAccountsListData>() {
                    @Override
                    public void onResponse(Call<MAccountsListData> call, Response<MAccountsListData> response) {
                        try {
                            Log.i(LOG_TAG, LOG_SPACE);
                            Log.i(LOG_TAG, "Получение списка счетов...");
                            Log.i(LOG_TAG, call.request().toString());

                            if(response.body() != null){
                                Log.i(LOG_TAG, "Получен список счетов!");
                                Log.i(LOG_TAG, "Кол-во счетов: "+Integer.toString(response.body().getData().size()));

                                //Выводим полученный список на экран
                                List<MAccount> accountsList = response.body().getData();
                                updateRecyclerView(accountsList);

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
                    public void onFailure(Call<MAccountsListData> call, Throwable t) {
                        Toast.makeText(getContext(), "Ошибка при выполнении запроса к серверу!", Toast.LENGTH_SHORT).show();
                        Log.i(LOG_TAG, LOG_SPACE);
                        Log.e(LOG_TAG, "Ошибка при выполнении запроса к серверу");
                        Log.e(LOG_TAG, t.getMessage());
                        Log.i(LOG_TAG, LOG_SPACE);

                    }
                });
    }

    //Метод который заставляем вывести данные в список
    private void updateRecyclerView(List<MAccount> lMAccountList){
        AdapterAccounts adapterAccounts = new AdapterAccounts(getContext(), this, lMAccountList);
        rvAccountsList.setAdapter(adapterAccounts);
        rvAccountsList.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    /*
    Метод из интерфейса адаптера
    Он срабатывает при выборе нужного объекта
     */
    @Override
    public void selectItem(long accountId) {
        //тут сразу же обращаемся к активности, которая выводит подробную информацию
        interfaceSessionActivity.showAccountInfo(accountId);
    }



}