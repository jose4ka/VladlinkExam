/*
 * Created by Dmitry Garmyshev on 14.07.20 13:59
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 13:59
 *
 */

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

/*
Для удобства, вся работа с внешним API происходит здесь, чтобы не заморачиваться переносом
данных из одного фрагмента в другой
 */

/*
Для того чтобы избежать создания кучи переменных в этом классе,
используем класс Session, в котором хранятся все нужные переменные (используется как контейнер)
Это так же позволяет нам более удобно использовать данные в разных Retrofit запросах
 */


package com.example.vladlinkexam.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.vladlinkexam.R;
import com.example.vladlinkexam.fragments.login.FragmentPhoneEnter;
import com.example.vladlinkexam.fragments.login.FragmentSMSCheck;
import com.example.vladlinkexam.interfaces.InterfaceLoginActivity;
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

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements InterfaceLoginActivity {


    private static final String LOG_NETWORK_TAG = "LOGIN_ACTIVITY_NETWORK";
    private static final String LOG_SPACE = "==============================";
    private static final String DEFAULT_PHONE_NUMBER = "+79444444444"; //номер телефона указанный в ТЗ
    private static final String DEFAULT_SMS_CODE = "1234"; //По ТЗ, данный код всегда равен 1234

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
        initializeSession();
        initializeFragments();
        showFragmentPhoneEnter(); //Сразу просим пользователя выполнить вход

    }


    private void initializeSession(){
        currentSession = new Session();
        currentSession.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        currentSession.setAuthCode(DEFAULT_SMS_CODE);
    }


    private void initializeFragments(){
        fragmentPhoneEnter = new FragmentPhoneEnter(this);
        fragmentSMSCheck = new FragmentSMSCheck(this);
    }

    //Реализованные методы из интерфейса

    //Метод который устанавливает на главный экран меню ввода номера телефона
    @Override
    public void showFragmentPhoneEnter() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction(); //Инициализируем транзакцию
        fragmentTransaction.replace(R.id.frameLoginMain, fragmentPhoneEnter); //Указываем в какой элемент, и какой фрагмент мы выводим
        fragmentTransaction.commit(); //Завершаем транзакцию
    }

    //Метод который устанавливает на главный экран меню ввода кода из смс
    @Override
    public void showFragmentSMSCheck() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLoginMain, fragmentSMSCheck);
        fragmentTransaction.commit();
    }

    /*
    Сначала мы создаём объект data обёртки для класса MPhoneNumber
    и уже в нём инициализируем необходимые для запроса переменные (Агрегация)
     */
    @Override
    public void getAuthCode(String phone) {
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
                            Log.i(LOG_NETWORK_TAG, LOG_SPACE);
                            Log.i(LOG_NETWORK_TAG, "Получение кода доступа с сервера...");
                            Log.i(LOG_NETWORK_TAG, call.request().toString());

                            if (response.body() != null){
                                Log.i(LOG_NETWORK_TAG, "Успешно!");


                                //Создаём объект обертки исходя из ответа с сервера
                                MRequestCodeData requestAnswer = response.body();

                                //Создаём объект который содержит в себе request_id
                                //и инициализируем его с помощью ранее полученной обёртки
                                MRequestID lRequestID = requestAnswer.getRequestID();

                                //Из requestID достаём полученный с сервера request_id
                                //и добавляем его в нашу сессию
                                currentSession.setRequestID(lRequestID.getRequestID());

                                //Выводим в лог полученный код
                                Log.i(LOG_NETWORK_TAG, "Код с сервера: "+Long.toString(currentSession.getRequestID()));

                                Log.i(LOG_NETWORK_TAG, LOG_SPACE);
                                //Сразу запускаем код с проверкой доступа по СМС
                                showFragmentSMSCheck();
                            }
                            else {
                                Log.i(LOG_NETWORK_TAG, LOG_SPACE);
                                Log.i(LOG_NETWORK_TAG, "С сервера получен пустой объект, или возникла ошибка на стороне сервера");
                                Toast.makeText(getApplicationContext(), "С сервера получен пустой объект, или возникла ошибка на стороне сервера!", Toast.LENGTH_SHORT).show();

                                if(response.errorBody() != null){
                                    Log.e(LOG_NETWORK_TAG, response.errorBody().string()); //Выводим ошибку с сервера
                                    Log.i(LOG_NETWORK_TAG, LOG_SPACE);
                                }
                            }
                        }
                        catch (Exception e){
                            Toast.makeText(getApplicationContext(), "Ошибка при чтении полученного ответа!", Toast.LENGTH_SHORT).show();
                            Log.i(LOG_NETWORK_TAG, LOG_SPACE);
                            Log.e(LOG_NETWORK_TAG, "Ошибка при чтении полученного ответа: ");
                            Log.e(LOG_NETWORK_TAG, e.getMessage());
                            Log.i(LOG_NETWORK_TAG, LOG_SPACE);
                        }
                    }
                    @Override
                    public void onFailure(Call<MRequestCodeData> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Ошибка при выполнении запроса к серверу!", Toast.LENGTH_SHORT).show();
                        Log.i(LOG_NETWORK_TAG, LOG_SPACE);
                        Log.e(LOG_NETWORK_TAG, "Ошибка при выполнении запроса к серверу");
                        Log.e(LOG_NETWORK_TAG, t.getMessage());
                        Log.i(LOG_NETWORK_TAG, LOG_SPACE);
                    }
                });
    }



    /*
    Сначала мы создаём объект data обёртки для класса MSMSQuery
    и уже в нём инициализируем необходимые для запроса переменные

     */
    @Override
    public void checkSMSCode(String SMSCode) {
        MSMSQueryData msmsQueryData = new MSMSQueryData(); //Data обёртка
        MSMSQuery msmsQuery = new MSMSQuery(); //Создаём объект для запроса СМС

        //Заполняем объект для запроса СМС
        msmsQuery.setCode(SMSCode);
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
                            Log.i(LOG_NETWORK_TAG, LOG_SPACE);
                            Log.i(LOG_NETWORK_TAG, "Зарос на получение SMS кода...");
                            Log.i(LOG_NETWORK_TAG, call.request().toString());

                            if (response.body() != null){
                                //Используем приведение типов, для получения класса обёртки, с вложенным классом внутри
                                MSMSResponceData requestAnswer = response.body();
                                MSMSResponce requestID = requestAnswer.getMSMSResponce();

                                //В объекте сессии выставляем указанные переменные
                                currentSession.setAuthToken(requestID.getToken());
                                currentSession.setPublicUids(requestID.getPublicUids());
                                currentSession.setCurrentUserName(requestID.getName());
                                currentSession.setUid(requestID.getuId());

                                //Для отладки выводим полученные данные в лог
                                Log.i(LOG_NETWORK_TAG, "Токен: "+currentSession.getAuthToken());
                                Log.i(LOG_NETWORK_TAG, "Доступные ID: "+Arrays.toString(currentSession.getPublicUids()));
                                Log.i(LOG_NETWORK_TAG, "Имя пользователя: "+currentSession.getCurrentUserName());
                                Log.i(LOG_NETWORK_TAG, "Тип пользователя: "+Long.toString(currentSession.getUid()));
                                Log.i(LOG_NETWORK_TAG, LOG_SPACE);
                                //Т.к. всё хорошо, и СМС код был проверен, мы запускаем следующую активность
                                startSessionActivity();
                            }
                            else {
                                Log.i(LOG_NETWORK_TAG, LOG_SPACE);
                                Log.e(LOG_NETWORK_TAG, "С сервера получен пустой объект, или возникла ошибка на стороне сервера!");
                                Log.i(LOG_NETWORK_TAG, LOG_SPACE);
                                Toast.makeText(getApplicationContext(), "С сервера получен пустой объект, или возникла ошибка на стороне сервера!", Toast.LENGTH_SHORT).show();

                                if(response.errorBody() != null){
                                    Log.e(LOG_NETWORK_TAG, response.errorBody().string());
                                    Log.i(LOG_NETWORK_TAG, LOG_SPACE);
                                }
                            }
                        }
                        catch (Exception e){
                            Toast.makeText(getApplicationContext(), "Ошибка при чтении полученного ответа!", Toast.LENGTH_SHORT).show();
                            Log.i(LOG_NETWORK_TAG, LOG_SPACE);
                            Log.e(LOG_NETWORK_TAG, "Ошибка при чтении полученного ответа: ");
                            Log.e(LOG_NETWORK_TAG, e.getMessage());
                            Log.i(LOG_NETWORK_TAG, LOG_SPACE);
                        }
                    }

                    @Override
                    public void onFailure(Call<MSMSResponceData> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Ошибка при выполнении запроса к серверу!", Toast.LENGTH_SHORT).show();
                        Log.i(LOG_NETWORK_TAG, LOG_SPACE);
                        Log.e(LOG_NETWORK_TAG, "Ошибка при выполнении запроса к серверу");
                        Log.e(LOG_NETWORK_TAG, t.getMessage());
                        Log.i(LOG_NETWORK_TAG, LOG_SPACE);
                    }
                });
    }


    //Метод который запускает следующую активность (просмотр лицевых счетов)
    public void startSessionActivity() {
        Intent sessionActivityIntent = new Intent(getApplicationContext(), SessionActivity.class);//Создаём интент на нужную активность

        sessionActivityIntent.putExtra("token", currentSession.getAuthToken()); //Добавляем в интент наш токен, который нужен для дальнейшей оаботы активности
        sessionActivityIntent.putExtra("full_name", currentSession.getCurrentUserName()); //Имя пользователя для последующего отображения
        sessionActivityIntent.putExtra("uid", currentSession.getUid()); //Тип пользователя

        startActivity(sessionActivityIntent); //Запускаем активность
    }

}