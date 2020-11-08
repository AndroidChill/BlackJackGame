package com.example.blackjackgame.network.responce.stattics;

public class CaptchaRequest {

    private String menu;
    private int app_ver;
    private String captcha_value;

    public CaptchaRequest(String menu, int app_ver, String captcha_value) {
        this.menu = menu;
        this.app_ver = app_ver;
        this.captcha_value = captcha_value;
    }

    public CaptchaRequest(String menu, int app_ver) {
        this.menu = menu;
        this.app_ver = app_ver;
    }

    public int getApp_ver() {
        return app_ver;
    }

    public void setApp_ver(int app_ver) {
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
