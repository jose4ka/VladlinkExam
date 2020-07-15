/*
 * Created by Dmitry Garmyshev on 15.07.20 15:26
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 15.07.20 15:22
 *
 */

/*
Data обёртка для класса MSingleAccount
 */
package com.example.vladlinkexam.model.accounts.singleAccount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MSingleAccountData {

    @SerializedName("data")
    @Expose
    private MSingleAccount data;

    private MSingleAccountData(){}

    public MSingleAccount getData() {
        return data;
    }

    public void setData(MSingleAccount data) {
        this.data = data;
    }
}
