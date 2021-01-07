package com.example.blackjackgame.rNetwork.request.sign;

import com.google.gson.annotations.SerializedName;

public class SignInEmailCodeRequest {

    private String menu;
    @SerializedName("app_ver")
    private int appVer;
    private String ln;
    private String email;
    private String code;
    private String sdk;
    private String model;
    private String manufacturer;
    @SerializedName("market_name")
    private String marketName;
    private String token;
    private String country;

    public SignInEmailCodeRequest(String menu, int appVer, String ln, String email, String code, String sdk, String model, String manufacturer, String marketName, String token, String country) {
        this.menu = menu;
        this.appVer = appVer;
        this.ln = ln;
        this.email = email;
        this.code = code;
        this.sdk = sdk;
        this.model = model;
        this.manufacturer = manufacturer;
        this.marketName = marketName;
        this.token = token;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public SignInEmailCodeRequest(String menu, int appVer, String ln, String email, String code, String sdk, String model, String manufacturer, String marketName, String token) {
        this.menu = menu;
        this.appVer = appVer;
        this.ln = ln;
        this.email = email;
        this.code = code;
        this.sdk = sdk;
        this.model = model;
        this.manufacturer = manufacturer;
        this.marketName = marketName;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }
}
