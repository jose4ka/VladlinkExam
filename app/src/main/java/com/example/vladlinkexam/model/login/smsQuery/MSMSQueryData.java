/*
 * Created by Dmitry Garmyshev on 14.07.20 20:15
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 19:55
 *
 */

package com.example.vladlinkexam.model.login.smsQuery;

import com.example.vladlinkexam.model.login.smsQuery.MSMSQuery;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MSMSQueryData {

    @SerializedName("data")
    @Expose
    public MSMSQuery smsQuery;

    public MSMSQueryData(){
        smsQuery = new MSMSQuery();
    }

    public MSMSQuery getSmsQuery() {
        return smsQuery;
    }

    public void setSmsQuery(MSMSQuery smsQuery) {
        this.smsQuery = smsQuery;
    }
}
