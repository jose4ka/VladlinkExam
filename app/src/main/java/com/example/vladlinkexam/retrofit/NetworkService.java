package com.example.vladlinkexam.retrofit;

import com.example.vladlinkexam.retrofit.api.ApiVladlinkAccounts;
import com.example.vladlinkexam.retrofit.api.ApiVladlinkLogin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


//Класс для использования библиотеки Retrofit
//Используется паттерн Singleton
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
