/*
 * Created by Dmitry Garmyshev on 15.07.20 15:26
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 15.07.20 15:22
 *
 */

package com.example.vladlinkexam.model.accounts.oneAccount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MOneAccountData {

    @SerializedName("data")
    @Expose
    private MOneAccount data;

    private MOneAccountData(){}

    public MOneAccount getData() {
        return data;
    }

    public void setData(MOneAccount data) {
        this.data = data;
    }
}
