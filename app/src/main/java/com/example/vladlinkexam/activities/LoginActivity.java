/*
 * Created by Dmitry Garmyshev on 14.07.20 13:59
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 13:59
 *
 */

package com.example.vladlinkexam.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.vladlinkexam.R;
import com.example.vladlinkexam.fragments.FragmentPhoneEnter;
import com.example.vladlinkexam.fragments.FragmentSMSCheck;
import com.example.vladlinkexam.interfaces.InterfaceLoginActivity;
import com.example.vladlinkexam.model.accounts.MAccountsListData;
import com.example.vladlinkexam.model.login.phoneNumber.MPhoneNumberData;
import com.example.vladlinkexam.model.login.requestID.MRequestCodeData;
import com.example.vladlinkexam.model.login.phoneNumber.MPhoneNumber;
import com.example.vladlinkexam.model.login.requestID.MRequestID;
import com.example.vladlinkexam.model.login.smsQuery.MSMSQuery;
import com.example.vladlinkexam.model.login.smsQuery.MSMSQueryData;
import com.example.vladlinkexam.model.login.smsResponce.MSMSResponce;
import com.example.vladlinkexam.model.login.smsResponce.MSMSResponceData;
import com.example.vladlinkexam.retrofit.NetworkService;
import com.example.vladlinkexam.session.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Данная активность у нас запускается самой первой, и в ней происходит последующая авторизация.

/*
В данной активности на экране фигурируют только фрагменты, которые мы меняем в зависимости от ситуации.
Это позволяет нам затрачивать меньше ресурсов на создание новых активностей, и не нужно
заморачиваться над тем как передавать данные между активностями
 */

/*
Так же у нас есть отдельный интерфейс, для связи дочерних фрагментов с нашей активностью
т.е. "протянуты" только те методы, которые могут выполнять фрагменты
Это нужно для того чтобы обойтись без получения объекта родительской активности
 */

//Получение данных производится с использованием библионтек Retrofit2 и Gson
public class LoginActivity extends AppCompatActivity implements InterfaceLoginActivity {

    //Тег необходимый для логирования
    private static final String LOG_NETWORK_TAG = "LOGIN_ACTIVITY_NETWORK";

    /*
    Текущая сессия, в ней хранятся нужные для авторизации пользователя данные
    и так же они нужны для дальнейшего использования приложения
     */
    private Session currentSession;

    //Фрагменты которые у нас будут выводиться на экран
    private FragmentPhoneEnter fragmentPhoneEnter;
    private FragmentSMSCheck fragmentSMSCheck;
    private FragmentTransaction fragmentTransaction; //Используется для смены фрагментов


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeSession(); //Инициализация сессии
        initializeFragments(); //Инициализация объектов фрагментов
        showFragmentPhoneEnter(); //Сразу просим пользователя выполнить вход

    }

    //Метод для инициализации объекта сессии, и задания начальных значений в ней
    private void initializeSession(){
        currentSession = new Session();
        //номер телефона указанный в ТЗ
        currentSession.setPhoneNumber("+79444444444");
        //По ТЗ, данный код всегда равен 1234
        currentSession.setAutorizationCode("1234");
    }





    private void initializeFragments(){
        fragmentPhoneEnter = new FragmentPhoneEnter(this);
        fragmentSMSCheck = new FragmentSMSCheck(this);
    }


    //Реализованные методы из интерфейса

    /*
    Сначала мы создаём объект data обёртки для класса MPhoneNumber
    и уже в нём инициализируем необходимые для запроса переменные
     */
    @Override
    public Session getAuthCode(String phone) {
        MPhoneNumberData mDataWrapper = new MPhoneNumberData(); //Data обёртка

        MPhoneNumber phoneNumber = new MPhoneNumber(); //Создаём объект с номером телефона
        phoneNumber.setPhoneNumber(phone); //Устанавливаем номер телефона
        mDataWrapper.setData(phoneNumber); //Добавляем объект с номером телефона - в обёртку

        //Выполняем сам запрос на получение кода, по номеру телефона
        NetworkService.getInstance().getLoginApi()
                .getCode(mDataWrapper)
                .enqueue(new Callback<MRequestCodeData>() {
                    @Override
                    public void onResponse(Call<MRequestCodeData> call, Response<MRequestCodeData> response) {
                        try {
                            Log.i(LOG_NETWORK_TAG, "Получение кода доступа с сервера...");
                            Log.i(LOG_NETWORK_TAG, call.request().toString());

                            if (response.body() != null){
                                Log.i(LOG_NETWORK_TAG, "Успешно!");

                                //Создаём объект обертки исходя из ответа с сервера
                                MRequestCodeData requestAnswer = response.body();

                                //Создаём объект который содержит в себе request_id
                                //и инициализируем его с помощью ранее полученного requestAnswer
                                MRequestID requestID = requestAnswer.getRequestID();

                                //Из requestID достаём полученный с сервера request_id
                                //и добавляем его в нашу сессию
                                currentSession.setRequestID(requestID.getRequestID());

                                //Выводим в лог полученный код
                                Log.i(LOG_NETWORK_TAG, Long.toString(currentSession.getRequestID()));

                                //Сразу запускаем код с проверкой доступа по СМС
                                showFragmentSMSCheck();
                            }
                            else {
                                Log.i(LOG_NETWORK_TAG, "С сервера получен пустой объект, или возникла ошибка на стороне сервера");

                                if(response.errorBody() != null){
                                    Log.i(LOG_NETWORK_TAG, response.errorBody().string()); //Выводим ошибку с сервера
                                }
                            }
                        }
                        catch (Exception e){
                            Log.e(LOG_NETWORK_TAG, "Ошибка при чтении полученного запроса: ");
                            Log.e(LOG_NETWORK_TAG, e.getMessage());
                        }
                    }
                    @Override
                    public void onFailure(Call<MRequestCodeData> call, Throwable t) {
                        Log.e(LOG_NETWORK_TAG, "Ошибка при выполнении запроса к серверу");
                        Log.e(LOG_NETWORK_TAG, t.getMessage());
                    }
                });

        return currentSession;
    }



    /*
    Сначала мы создаём объект data обёртки для класса MSMSQuery
    и уже в нём инициализируем необходимые для запроса переменные
     */
    @Override
    public Session checkSMSCode(String code) {
        MSMSQueryData msmsQueryData = new MSMSQueryData(); //Data обёртка
        MSMSQuery msmsQuery = new MSMSQuery(); //Создаём объект для запроса СМС



        //Заполняем объект для запроса СМС
        msmsQuery.setCode(code);
        msmsQuery.setPhoneNumber(currentSession.getPhoneNumber());
        msmsQuery.setRequestID(currentSession.getRequestID());

        //Вставляем заполненный объект в обёртку
        msmsQueryData.setSmsQuery(msmsQuery);

        //Выполняем запрос на получение СМС
        NetworkService.getInstance()
                .getLoginApi()
                .checkSMS(msmsQueryData) //Передаём обёртку в метод запроса
                .enqueue(new Callback<MSMSResponceData>() {
                    @Override
                    public void onResponse(Call<MSMSResponceData> call, Response<MSMSResponceData> response) {
                        try {
                            Log.i(LOG_NETWORK_TAG, "Зарос на получение SMS кода...");
                            Log.i(LOG_NETWORK_TAG, call.request().toString());

                            if (response.body() != null){
                                //Используем приведение типов, для получения класса обёртки, с вложенным классом внутри
                                MSMSResponceData requestAnswer = response.body();
                                MSMSResponce requestID = requestAnswer.getMSMSResponce();

                                currentSession.setAuthToken(requestID.getToken());
                                currentSession.setPublicUids(requestID.publicUids);
                                currentSession.setCurrentUserName(requestID.getName());
                                currentSession.setUid(requestID.uId);

                                Log.i(LOG_NETWORK_TAG, Long.toString(currentSession.getUid()));
                                Log.i(LOG_NETWORK_TAG, currentSession.getCurrentUserName());
                                Log.i(LOG_NETWORK_TAG,requestID.getToken());
                                for(int i = 0; i < currentSession.getPublicUids().length; i++){
                                    Log.i(LOG_NETWORK_TAG, Long.toString(requestID.publicUids[i]));
                                }



                                startSessionActivity(currentSession.getAuthToken());
                            }
                            else {
                                Log.i(LOG_NETWORK_TAG, "NULL BODY");
                                Log.i(LOG_NETWORK_TAG, response.errorBody().string());
                            }
                        }
                        catch (Exception e){}
                    }

                    @Override
                    public void onFailure(Call<MSMSResponceData> call, Throwable t) {}
                });


        return currentSession;
    }


    @Override
    public void showFragmentPhoneEnter() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLoginMain, fragmentPhoneEnter);
        fragmentTransaction.commit();
    }

    @Override
    public void showFragmentSMSCheck() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLoginMain, fragmentSMSCheck);
        fragmentTransaction.commit();
    }

    @Override
    public void startSessionActivity(String token) {
        Toast.makeText(this, "Успешная авторизация",Toast.LENGTH_SHORT).show();

        Intent sessionActivityIntent = new Intent(getApplicationContext(), SessionActivity.class);
        sessionActivityIntent.putExtra("token", token);
        startActivity(sessionActivityIntent);

    }


}