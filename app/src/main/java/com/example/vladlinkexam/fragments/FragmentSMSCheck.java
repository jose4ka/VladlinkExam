/*
 * Created by Dmitry Garmyshev on 14.07.20 18:12
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 18:12
 *
 */

package com.example.vladlinkexam.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.vladlinkexam.R;
import com.example.vladlinkexam.interfaces.InterfaceLoginActivity;
import com.example.vladlinkexam.session.Session;


public class FragmentSMSCheck extends Fragment {

    private InterfaceLoginActivity interfaceLoginActivity;


    private View v;
    private Button btnCheckSMSCode;
    private EditText etSMSCode;

    public FragmentSMSCheck(InterfaceLoginActivity interfaceLoginActivity){
        this.interfaceLoginActivity = interfaceLoginActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_s_m_s_check, container, false);
        initializeScreenElements();
        return v;
    }

    private void initializeScreenElements(){
        btnCheckSMSCode = v.findViewById(R.id.btnCheckSMSCode);
        btnCheckSMSCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceLoginActivity.checkSMSCode(etSMSCode.getText().toString());
            }
        });
        etSMSCode = v.findViewById(R.id.etSMSCode);
    }



}