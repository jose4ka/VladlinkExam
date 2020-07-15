/*
 * Created by Dmitry Garmyshev on 15.07.20 15:26
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 15.07.20 15:04
 *
 */

package com.example.vladlinkexam.model.accounts.accountsList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MAccountsListData {

    @SerializedName("data")
    @Expose
    private List<MAccount> data;

    private MAccountsListData(){}

    public List<MAccount> getData() {
        return data;
    }

    public void setData(List<MAccount> data) {
        this.data = data;
    }
}



