package com.example.blackjackgame.network.responce.stattics;

public class CaptchaRequest {

    private String menu;
    private String captcha_value;

    public CaptchaRequest(String menu, String captcha_value) {
        this.menu = menu;
        this.captcha_value = captcha_value;
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
