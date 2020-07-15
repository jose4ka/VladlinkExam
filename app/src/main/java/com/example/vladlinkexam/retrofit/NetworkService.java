/*
 * Created by Dmitry Garmyshev on 16.07.20 0:57
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 16.07.20 0:37
 *
 */

//Класс для использования библиотеки Retrofit
//Используется паттерн Singleton

package com.example.vladlinkexam.retrofit;

import com.example.vladlinkexam.retrofit.api.ApiVladlinkAccounts;
import com.example.vladlinkexam.retrofit.api.ApiVladlinkLogin;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;



public class NetworkService {

    //Экземпляр этого объекта, нужен для реализации паттерна Singleton
    private static NetworkService instance;
    //Базоый URL для обращения к серверу, будет использоваться для всех запросов
    private static final String BASE_URL = "https://test-api.vladlink.ru";

    //Объект из библиотеки Retrofit2
    private Retrofit mRetrofit;

    //В конструкторе инициализируем библиотечный объект для дальнейшей работы
    private NetworkService(){


        mRetrofit = new Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(ScalarsConverterFactory.create()).
                addConverterFactory(GsonConverterFactory.create()).
                build();
    }


    //Реализация паттерна Singleton - один класс на всю программу
    public static NetworkService getInstance(){
        if(instance == null){
            instance = new NetworkService();
        }

        return instance;
    }


    public ApiVladlinkLogin getLoginApi(){
        return mRetrofit.create(ApiVladlinkLogin.class);
    }

    public ApiVladlinkAccounts getAccountsApi(){return mRetrofit.create(ApiVladlinkAccounts.class);}
}
