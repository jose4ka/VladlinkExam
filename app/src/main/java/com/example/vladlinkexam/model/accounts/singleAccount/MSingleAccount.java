/*
 * Created by Dmitry Garmyshev on 15.07.20 15:26
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 15.07.20 15:22
 *
 */

/*
Модель для получения полных данных о счёте

Отличие от MAccount заключается в том, что при получении подробных данных о счёте,
поле isSMS приходит в виде строки, а не массив целых чисел как в MAccount

Поэтому, для приёма корректных данных отдельного счёта, пришлось создавать новую модель
и обёртку под неё
 */
package com.example.vladlinkexam.model.accounts.singleAccount;

import com.example.vladlinkexam.model.accounts.subclasses.MAddress;
import com.example.vladlinkexam.model.accounts.subclasses.MTariffCurrent;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MSingleAccount {
    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("ulogin")
    @Expose
    private String uLogin;

    @SerializedName("full_name")
    @Expose
    private String fullName;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("bill")
    @Expose
    private double bill;

    @SerializedName("block")
    @Expose
    private boolean block;

    @SerializedName("cblock")
    @Expose
    private boolean cblock;

    @SerializedName("tariff")
    @Expose
    private float tariff;

    @SerializedName("tariff_next")
    @Expose
    private float tariffNext;

    @SerializedName("tariff_current")
    @Expose
    private MTariffCurrent tariffCurrent;

    @SerializedName("u_address")
    @Expose
    private List<MAddress> uMAddresses;

    @SerializedName("is_juridical")
    @Expose
    private boolean isJuridical;

    @SerializedName("is_sms")
    @Expose
    private String isSMS;

    @SerializedName("city_id")
    @Expose
    private long cityId;

    @SerializedName("skidko")
    @Expose
    private float skidko;

    @SerializedName("balls")
    @Expose
    private float balls;

    @SerializedName("credit")
    @Expose
    private float credit;

    @SerializedName("is_phone_user")
    @Expose
    private boolean isPhoneUser;

    @SerializedName("uc_status")
    @Expose
    private int ucStatus;

    public MSingleAccount(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<MAddress> getuMAddresses() {
        return uMAddresses;
    }

    public void setuMAddresses(List<MAddress> uMAddresses) {
        this.uMAddresses = uMAddresses;
    }

    public String getuLogin() {
        return uLogin;
    }

    public void setuLogin(String uLogin) {
        this.uLogin = uLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public boolean isCblock() {
        return cblock;
    }

    public void setCblock(boolean cblock) {
        this.cblock = cblock;
    }

    public float getTariff() {
        return tariff;
    }

    public void setTariff(float tariff) {
        this.tariff = tariff;
    }

    public float getTariffNext() {
        return tariffNext;
    }

    public void setTariffNext(float tariffNext) {
        this.tariffNext = tariffNext;
    }

    public MTariffCurrent getTariffCurrent() {
        return tariffCurrent;
    }

    public void setTariffCurrent(MTariffCurrent tariffCurrent) {
        this.tariffCurrent = tariffCurrent;
    }

    public boolean isJuridical() {
        return isJuridical;
    }

    public void setJuridical(boolean juridical) {
        isJuridical = juridical;
    }

    public String getIsSMS() {
        return isSMS;
    }

    public void setIsSMS(String isSMS) {
        this.isSMS = isSMS;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public float getSkidko() {
        return skidko;
    }

    public void setSkidko(float skidko) {
        this.skidko = skidko;
    }

    public float getBalls() {
        return balls;
    }

    public void setBalls(float balls) {
        this.balls = balls;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public boolean isPhoneUser() {
        return isPhoneUser;
    }

    public void setPhoneUser(boolean phoneUser) {
        isPhoneUser = phoneUser;
    }

    public int getUcStatus() {
        return ucStatus;
    }

    public void setUcStatus(int ucStatus) {
        this.ucStatus = ucStatus;
    }
}
