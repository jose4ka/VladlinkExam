/*
 * Created by Dmitry Garmyshev on 15.07.20 19:32
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 18:56
 *
 */

/*
Данный фрагмент у нас используется для ввода номера телефона, и выполнения дальнейшего запроса
на получения кода доступа

Для связи с родительскойактивностью используется интерфейс
 */

package com.example.vladlinkexam.fragments.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vladlinkexam.R;
import com.example.vladlinkexam.interfaces.InterfaceLoginActivity;

public class FragmentPhoneEnter extends Fragment {

    //Интерфейс для связи с родительской активностью
    private InterfaceLoginActivity interfaceLoginActivity;

    //View элементы с экрана
    private View mainView;
    private Button btnConfirmPhoneNumber;
    private EditText etPhoneNumber;

    //Получаем интерфейс для взаимодействия с родительской активностью
    public FragmentPhoneEnter(InterfaceLoginActivity interfaceLoginActivity){
        this.interfaceLoginActivity = interfaceLoginActivity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_phone_enter, container, false);
        initializeScreenElements();
        return mainView;
    }


    /*
    Отдельный метод для инициализации элементов экрана
    Больше используется для удобности
     */
    private void initializeScreenElements(){
        btnConfirmPhoneNumber = mainView.findViewById(R.id.btnConfirmPhoneNumber);
        btnConfirmPhoneNumber.setOnClickListener(new OnClickListener() {
            /*
            В данном обработчике после проверки номера на валидность,
            через интерфейс обращаемся к активности и выаолняем запрос на получение кода
             */
            @Override
            public void onClick(View view) {
                if(checkIsValidPhoneNumber()){
                    interfaceLoginActivity.getAuthCode(etPhoneNumber.getText().toString());
                }
                else Toast.makeText(getContext(), "Пожалуйста, введите корректный номер", Toast.LENGTH_SHORT).show();
            }
        });

        etPhoneNumber = mainView.findViewById(R.id.etPhoneNumber);
    }

    //Метод для проверки введённого номера на валидность
    /*
    Основные критерии - первый знак должен равняться '+',
    длина строки должна быть равна 12 (включая '+')
     */
    private boolean checkIsValidPhoneNumber(){
        String lNumber = etPhoneNumber.getText().toString();

        boolean result = false;
        if((lNumber != null) && (!lNumber.isEmpty())){
            if(lNumber.length() == 12){
                if(lNumber.charAt(0) == '+'){
                    result = true;
                }
            }
        }
        else {result = false;}

        return result;
    }

}