/*
 * Created by Dmitry Garmyshev on 15.07.20 19:32
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 18:56
 *
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

    private View v;
    private Button btnConfirmPhoneNumber;
    private EditText etPhoneNumber;


    public FragmentPhoneEnter(InterfaceLoginActivity interfaceLoginActivity){
        this.interfaceLoginActivity = interfaceLoginActivity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_phone_enter, container, false);
        initializeScreenElements();

        return v;

    }


    private void initializeScreenElements(){
        btnConfirmPhoneNumber = v.findViewById(R.id.btnConfirmPhoneNumber);
        btnConfirmPhoneNumber.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValidPhoneNumber()){
                    interfaceLoginActivity.getAuthCode(etPhoneNumber.getText().toString());
                }
                else Toast.makeText(getContext(), "Пожалуйста, введите корректный номер", Toast.LENGTH_SHORT).show();
            }
        });

        etPhoneNumber = v.findViewById(R.id.etPhoneNumber);

    }

    //Метод для проверки введённого номера на валидность
    private boolean isValidPhoneNumber(){
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