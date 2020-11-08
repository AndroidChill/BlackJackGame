package com.example.blackjackgame.rNetwork.request.sign;

import com.google.gson.annotations.SerializedName;

public class SignInEmailRequest {

    private String menu;
    private int appVer;
    private String ln;
    private String email;
    @SerializedName("captcha_value")
    private String captchaValue;
    private String sdk;
    private String model;
    private String manufacturer;
    private String marketName;
    private String token;

    public SignInEmailRequest(String menu, int appVer, String ln, String email, String captchaValue, String sdk, String model, String manufacturer, String marketName, String token) {
        this.menu = menu;
        this.appVer = appVer;
        this.ln = ln;
        this.email = email;
        this.captchaValue = captchaValue;
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

    public String getCaptchaValue() {
        return captchaValue;
    }

    public void setCaptchaValue(String captchaValue) {
        this.captchaValue = captchaValue;
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
