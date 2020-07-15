/*
 * Created by Dmitry Garmyshev on 14.07.20 14:09
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 14:09
 *
 */

/*
Данная активность запускается второй по счёту, после успешной авторизации

Основная её суть заключается в том, что она просто выставляет различные фрагменты на экран,
которые в дальнейшем и организуют получение каких-либо данных с сервера

Так-же в этой активности есть Меню Опций, которое позволяет вернуться в предыдущую активность
 */

/*
Как и LoginActivity, данная активность предоставляет фрагментам свой интерфейс для
взаимодействия с ней
 */

/*
Во время инициализации фрагментов, к ним в конструктор отправляем текущий класс
т.к. нам нужно передать интерфейс для взаимодействия фрагментов с этой активностью
 */


package com.example.vladlinkexam.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.vladlinkexam.R;
import com.example.vladlinkexam.fragments.accounts.FragmentAccountInfo;
import com.example.vladlinkexam.fragments.accounts.FragmentAccountsList;
import com.example.vladlinkexam.interfaces.InterfaceSessionActivity;
import com.example.vladlinkexam.session.Session;


public class SessionActivity extends AppCompatActivity implements InterfaceSessionActivity {


    private static final String LOG_TAG = "SESSION_ACTIVITY";
    private static final String LOG_SPACE = "==============================";

    //Фрагменты для вывода на экран, и транзакция для них
    private FragmentAccountsList fragmentAccountsList;
    private FragmentAccountInfo fragmentAccountInfo;
    private FragmentTransaction fragmentTransaction;

    private Session currentSession; //Текущая сессия, инициализируется ниже

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        initializeCurrentSession();
        setTitle(currentSession.getCurrentUserName());
        showAccountsList();
    }


    //Инициализируем текущую сессию данными из интента
    private void initializeCurrentSession(){
        Log.i(LOG_TAG, LOG_SPACE);
        Log.i(LOG_TAG, "Начало инициализации сессии");

        currentSession = new Session();
        currentSession.setAuthToken(getIntent().getStringExtra("token"));
        currentSession.setCurrentUserName(getIntent().getStringExtra("full_name"));
        currentSession.setUid(getIntent().getLongExtra("uid", 0));

        Log.i(LOG_TAG, "Токен: "+currentSession.getAuthToken());
        Log.i(LOG_TAG, "Имя пользователя: "+currentSession.getCurrentUserName());
        Log.i(LOG_TAG, "Тип пользователя: "+currentSession.getCurrentUserName());

        Log.i(LOG_TAG, LOG_SPACE);
    }


    //Устанавливаем меню в верхний тулбар
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar_menu, menu);
        return true;
    }

    //Обработчик нажатий на меню из тулбара
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout:
                runLoginActivity();
                break;
        }
        return  true;
    }

    //Инициализируем фрагмент, и выводим его на экран
    @Override
    public void showAccountsList() {
        fragmentAccountsList = new FragmentAccountsList(currentSession.getAuthToken(), this);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameAccountsMain, fragmentAccountsList);
        fragmentTransaction.commit();
    }

    //Инициализируем фрагмент, и выводим его на экран
    @Override
    public void showAccountInfo(long accountId) {
        fragmentAccountInfo = new FragmentAccountInfo(currentSession.getAuthToken(), accountId, this);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameAccountsMain, fragmentAccountInfo);
        fragmentTransaction.commit();
    }

    /*
    Этот метод используется для того чтобы релогиниться
    Т.к. нам нужно заного провести процесс получения токена,
    то мы именно пересоздаём активность, а не переходим к ней по стеку
     */
    private void runLoginActivity(){
        Intent iLoginActivity = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(iLoginActivity);
    }


}