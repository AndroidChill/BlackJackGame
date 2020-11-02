package com.example.blackjackgame.network.responce.stattics;

public class CaptchaRequest {

    private String menu;
    private String app_ver;
    private String captcha_value;

    public CaptchaRequest(String menu, String app_ver) {
        this.menu = menu;
        this.app_ver = app_ver;
    }

    public CaptchaRequest(String menu, String app_ver, String captcha_value) {
        this.menu = menu;
        this.app_ver = app_ver;
        this.captcha_value = captcha_value;
    }

    public String getApp_ver() {
        return app_ver;
    }

    public void setApp_ver(String app_ver) {
        this.app_ver = app_ver;
    }

    public String getCaptcha_value() {
        return captcha_value;
    }

    public void setCaptcha_value(String captcha_value) {
        this.captcha_value = captcha_value;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
}
