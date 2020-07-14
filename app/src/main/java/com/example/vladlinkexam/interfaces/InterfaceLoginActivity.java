/*
 * Created by Dmitry Garmyshev on 14.07.20 14:10
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 14:10
 *
 */

package com.example.vladlinkexam.interfaces;

import com.example.vladlinkexam.session.Session;

public interface InterfaceLoginActivity {

    Session getAuthCode(String phone);

    Session checkSMSCode(String code);

    void showFragmentPhoneEnter();

    void showFragmentSMSCheck();

    void startSessionActivity(String token);
}
