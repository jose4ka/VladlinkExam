/*
 * Created by Dmitry Garmyshev on 14.07.20 14:10
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 14:10
 *
 */

package com.example.vladlinkexam.interfaces;

import com.example.vladlinkexam.model.accounts.accountsList.MAccountsListData;
import com.example.vladlinkexam.model.accounts.oneAccount.MSingleAccount;

import java.util.List;

public interface InterfaceSessionActivity {

    List<MAccountsListData> getAccountsList();

    MSingleAccount getSelectedAccount();

    void showAccountsList();

    void showAccountInfo(long id);
}
