package com.example.blackjackgame.model.sign.signin;

import com.google.gson.annotations.SerializedName;

public class SignInBody {

    @SerializedName("menu")
    private String menu;

    @SerializedName("status")
    private String status;

    @SerializedName("toast")
    private String toast;

    @SerializedName("captcha_image_url")
    private String captcha_image_url;

    @SerializedName("error_text")
    private String error_text;

    @SerializedName("time_server")
    private long time_server;

    public SignInBody(String menu, String status, String toast, String captcha_image_url, String error_text, long time_server) {
        this.menu = menu;
        this.status = status;
        this.toast = toast;
        this.captcha_image_url = captcha_image_url;
        this.error_text = error_text;
        this.time_server = time_server;
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

    public String getError_text() {
        return error_text;
    }

    public void setError_text(String error_text) {
        this.error_text = error_text;
    }

    public long getTime_server() {
        return time_server;
    }

    public void setTime_server(long time_server) {
        this.time_server = time_server;
    }
}
