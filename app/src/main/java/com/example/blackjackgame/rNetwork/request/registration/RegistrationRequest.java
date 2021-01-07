package com.example.blackjackgame.rNetwork.request.registration;

import com.google.gson.annotations.SerializedName;

public class RegistrationRequest {

    private String menu;
    @SerializedName("app_ver")
    private int appVer;
    private String ln;
    private String token;
    private int sdk;
    private String model;
    @SerializedName("market_name")
    private String marketName;
    private String manufacturer;
    private String email;
    @SerializedName("user_nickname")
    private String nickname;
    @SerializedName("ref_code")
    private String refCode;
    @SerializedName("captcha_value")
    private String captchaValue;
    private String country;

    public RegistrationRequest(String menu, int appVer, String ln, String token, int sdk, String model, String marketName, String manufacturer, String email, String nickname, String refCode, String captchaValue, String country) {
        this.menu = menu;
        this.appVer = appVer;
        this.ln = ln;
        this.token = token;
        this.sdk = sdk;
        this.model = model;
        this.marketName = marketName;
        this.manufacturer = manufacturer;
        this.email = email;
        this.nickname = nickname;
        this.refCode = refCode;
        this.captchaValue = captchaValue;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public RegistrationRequest(String menu, int appVer, String ln, String token, int sdk, String model, String marketName, String manufacturer, String email, String nickname) {
        this.menu = menu;
        this.appVer = appVer;
        this.ln = ln;
        this.token = token;
        this.sdk = sdk;
        this.model = model;
        this.marketName = marketName;
        this.manufacturer = manufacturer;
        this.email = email;
        this.nickname = nickname;
    }

    public RegistrationRequest(String menu, int appVer, String ln, String token, int sdk, String model, String marketName, String manufacturer, String email, String nickname, String refCode, String captchaValue) {
        this.menu = menu;
        this.appVer = appVer;
        this.ln = ln;
        this.token = token;
        this.sdk = sdk;
        this.model = model;
        this.marketName = marketName;
        this.manufacturer = manufacturer;
        this.email = email;
        this.nickname = nickname;
        this.refCode = refCode;
        this.captchaValue = captchaValue;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public String getCaptchaValue() {
        return captchaValue;
    }

    public void setCaptchaValue(String captchaValue) {
        this.captchaValue = captchaValue;
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

    public int getSdk() {
        return sdk;
    }

    public void setSdk(int sdk) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
