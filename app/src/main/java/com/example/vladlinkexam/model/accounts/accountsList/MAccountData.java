/*
 * Created by Dmitry Garmyshev on 15.07.20 15:26
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 15.07.20 15:04
 *
 */

package com.example.vladlinkexam.model.accounts.accountsList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MAccountData {

    @SerializedName("data")
    @Expose
    private MAccount data;

    private MAccountData(){}

    public MAccount getData() {
        return data;
    }

    public void setData(MAccount data) {
        this.data = data;
    }
}
