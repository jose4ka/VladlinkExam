/*
 * Created by Dmitry Garmyshev on 15.07.20 19:32
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 20:07
 *
 */

/*
Данный фрагмент у нас используется для проверки кода СМС и выполнения дальнейшего запроса
на получения токена

Для связи с родительскойактивностью используется интерфейс
 */

package com.example.vladlinkexam.fragments.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vladlinkexam.R;
import com.example.vladlinkexam.interfaces.InterfaceLoginActivity;


public class FragmentSMSCheck extends Fragment {

    private InterfaceLoginActivity interfaceLoginActivity;

    //Элементы экрана
    private View mainView;
    private Button btnCheckSMSCode;
    private EditText etSMSCode;

    public FragmentSMSCheck(InterfaceLoginActivity interfaceLoginActivity){
        this.interfaceLoginActivity = interfaceLoginActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_s_m_s_check, container, false);
        initializeScreenElements();
        return mainView;
    }


    private void initializeScreenElements(){
        btnCheckSMSCode = mainView.findViewById(R.id.btnCheckSMSCode);
        btnCheckSMSCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Всё что требудется - обратиться к род. активности, и проверить код
                Дальше активность решает сама, что делать дальше
                */
                if (checkIsValidCode()){
                    interfaceLoginActivity.checkSMSCode(etSMSCode.getText().toString());
                }
                else Toast.makeText(getContext(), "Пожалуйста, введите полученный код!",Toast.LENGTH_SHORT).show();

            }
        });

        etSMSCode = mainView.findViewById(R.id.etSMSCode);
    }


    private boolean checkIsValidCode(){
        boolean result = false;

        String lCode = etSMSCode.getText().toString();

        if((lCode != null) && (!lCode.isEmpty())){
            result = true;
        }

        return result;
    }


}