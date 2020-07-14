/*
 * Created by Dmitry Garmyshev on 14.07.20 21:59
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 14.07.20 21:59
 *
 */

package com.example.vladlinkexam.model.accounts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MAccountsListData {

    @SerializedName("status")
    private int status;


    @SerializedName("data")
    @Expose
    ArrayList <MAccount> data = new ArrayList <MAccount> ();

    @SerializedName("paginate")
    @Expose
    Paginate PaginateObject;


    public float getStatus() {
        return status;
    }

    public Paginate getPaginate() {
        return PaginateObject;
    }



    public void setStatus(int status) {
        this.status = status;
    }

    public void setPaginate(Paginate paginateObject) {
        this.PaginateObject = paginateObject;
    }

    public ArrayList<MAccount> getData() {
        return data;
    }

    public Paginate getPaginateObject() {
        return PaginateObject;
    }

    public MAccountsListData(){}


    public class Paginate {
        private float count_page;
        private float count_item;



        public float getCount_page() {
            return count_page;
        }

        public float getCount_item() {
            return count_item;
        }

        // Setter Methods

        public void setCount_page(float count_page) {
            this.count_page = count_page;
        }

        public void setCount_item(float count_item) {
            this.count_item = count_item;
        }
    }
}



