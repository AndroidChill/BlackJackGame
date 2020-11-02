package com.example.blackjackgame.network.responce.registration;

public class RegistrationRequest {

    private String menu;
    private String app_ver;
    private String email;
    private String captcha_value;
    private String ref_code;
    private String user_nickname;
    private String sdk;
    private String model;
    private String manufacturer;
    private String market_name;

    public RegistrationRequest(String menu, String app_ver, String email, String captcha_value, String ref_code, String user_nickname, String sdk, String model, String manufacturer, String market_name) {
        this.menu = menu;
        this.app_ver = app_ver;
        this.email = email;
        this.captcha_value = captcha_value;
        this.ref_code = ref_code;
        this.user_nickname = user_nickname;
        this.sdk = sdk;
        this.model = model;
        this.manufacturer = manufacturer;
        this.market_name = market_name;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getApp_ver() {
        return app_ver;
    }

    public void setApp_ver(String app_ver) {
        this.app_ver = app_ver;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCaptcha_value() {
        return captcha_value;
    }

    public void setCaptcha_value(String captcha_value) {
        this.captcha_value = captcha_value;
    }

    public String getRef_code() {
        return ref_code;
    }

    public void setRef_code(String ref_code) {
        this.ref_code = ref_code;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
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

    public String getMarket_name() {
        return market_name;
    }

    public void setMarket_name(String market_name) {
        this.market_name = market_name;
    }
}
