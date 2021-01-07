package com.example.blackjackgame.rNetwork.request.sign;

import com.google.gson.annotations.SerializedName;

public class SignInGuestRequest {

    private String menu;
    @SerializedName("app_ver")
    private int appVer;
    private String ln;
    private String token;
    @SerializedName("ref_code")
    private String refCode;
    private String sdk;
    private String model;
    @SerializedName("market_name")
    private String marketName;
    private String manufacturer;
    private String country;

    public SignInGuestRequest(String menu, int appVer, String ln, String token, String refCode, String sdk, String model, String marketName, String manufacturer, String country) {
        this.menu = menu;
        this.appVer = appVer;
        this.ln = ln;
        this.token = token;
        this.refCode = refCode;
        this.sdk = sdk;
        this.model = model;
        this.marketName = marketName;
        this.manufacturer = manufacturer;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public SignInGuestRequest(String menu, int appVer, String ln, String token, String refCode, String sdk, String model, String marketName, String manufacturer) {
        this.menu = menu;
        this.appVer = appVer;
        this.ln = ln;
        this.token = token;
        this.refCode = refCode;
        this.sdk = sdk;
        this.model = model;
        this.marketName = marketName;
        this.manufacturer = manufacturer;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public int getAppVer() {
        return appVer;
    }

    public void setAppVer(int appVer) {
        this.appVer = appVer;
    }

    public String getLn() {
        return ln;
    }

    public void setLn(String ln) {
        this.ln = ln;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public String getSdk() {
        return sdk;
    }

    public void setSdk(String sdk) {
        this.sdk = sdk;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
