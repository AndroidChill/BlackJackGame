package com.example.blackjackgame.model.profile.changeData;

import com.google.gson.annotations.SerializedName;

public class ProfileChangeBody {

    @SerializedName("menu")
    private String menu;

    @SerializedName("status_text")
    private String status_text;

    @SerializedName("status")
    private String status;

    @SerializedName("time_server")
    private long time_server;

    @SerializedName("token")
    private String token;

    @SerializedName("popup")
    private String popup;

    @SerializedName("captcha_image_url")
    private String captchaImageUrl;

    public ProfileChangeBody(String menu, String status, long time_server) {
        this.menu = menu;
        this.status = status;
        this.time_server = time_server;
    }

    public ProfileChangeBody(String menu, String status_text, String status, long time_server) {
        this.menu = menu;
        this.status_text = status_text;
        this.status = status;
        this.time_server = time_server;
    }

    public ProfileChangeBody(String menu, String status_text, String status, long time_server, String token) {
        this.menu = menu;
        this.status_text = status_text;
        this.status = status;
        this.time_server = time_server;
        this.token = token;
    }

    public ProfileChangeBody(String menu, String status_text, String status, long time_server, String token, String popup) {
        this.menu = menu;
        this.status_text = status_text;
        this.status = status;
        this.time_server = time_server;
        this.token = token;
        this.popup = popup;
    }

    public ProfileChangeBody(String menu, String status_text, String status, long time_server, String token, String popup, String captchaImageUrl) {
        this.menu = menu;
        this.status_text = status_text;
        this.status = status;
        this.time_server = time_server;
        this.token = token;
        this.popup = popup;
        this.captchaImageUrl = captchaImageUrl;
    }

    public String getCaptchaImageUrl() {
        return captchaImageUrl;
    }

    public void setCaptchaImageUrl(String captchaImageUrl) {
        this.captchaImageUrl = captchaImageUrl;
    }

    public String getPopup() {
        return popup;
    }

    public void setPopup(String popup) {
        this.popup = popup;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus_text() {
        return status_text;
    }

    public void setStatus_text(String status_text) {
        this.status_text = status_text;
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

    public long getTime_server() {
        return time_server;
    }

    public void setTime_server(long time_server) {
        this.time_server = time_server;
    }
}
