package com.example.blackjackgame.model.statics;

import com.google.gson.annotations.SerializedName;

public class CaptchaBody {

    @SerializedName("menu")
    private String menu;

    @SerializedName("status")
    private String status;

    @SerializedName("toast")
    private String toast;

    @SerializedName("captcha_image_url")
    private String captcha_image_url;

    public CaptchaBody(String menu, String status, String toast, String captcha_image_url) {
        this.menu = menu;
        this.status = status;
        this.toast = toast;
        this.captcha_image_url = captcha_image_url;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToast() {
        return toast;
    }

    public void setToast(String toast) {
        this.toast = toast;
    }

    public String getCaptcha_image_url() {
        return captcha_image_url;
    }

    public void setCaptcha_image_url(String captcha_image_url) {
        this.captcha_image_url = captcha_image_url;
    }
}
