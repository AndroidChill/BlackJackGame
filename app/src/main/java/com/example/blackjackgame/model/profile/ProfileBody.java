package com.example.blackjackgame.model.profile;

import com.google.gson.annotations.SerializedName;

public class ProfileBody {

    @SerializedName("menu")
    private String menu;

    @SerializedName("token")
    private String token;

    @SerializedName("status")
    private String status;

    @SerializedName("toast")
    private String toast;

    @SerializedName("profile")
    private Profile profile;

    @SerializedName("time_server")
    private long time_server;

    @SerializedName("error_text")
    private String error_text;

    @SerializedName("popup")
    private String popup;

    @SerializedName("captcha_image_url")
    private String captcha_image_url;

    public ProfileBody(String menu, String status, String toast, Profile profile, long time_server, String error_text) {
        this.menu = menu;
        this.status = status;
        this.toast = toast;
        this.profile = profile;
        this.time_server = time_server;
        this.error_text = error_text;
    }

    public ProfileBody(String menu, String status, String toast, Profile profile, long time_server, String error_text, String captcha_image_url) {
        this.menu = menu;
        this.status = status;
        this.toast = toast;
        this.profile = profile;
        this.time_server = time_server;
        this.error_text = error_text;
        this.captcha_image_url = captcha_image_url;
    }

    public ProfileBody(String menu, String status, String toast, Profile profile, long time_server, String error_text, String popup, String captcha_image_url) {
        this.menu = menu;
        this.status = status;
        this.toast = toast;
        this.profile = profile;
        this.time_server = time_server;
        this.error_text = error_text;
        this.popup = popup;
        this.captcha_image_url = captcha_image_url;
    }

    public ProfileBody(String menu, String token, String status, String toast, Profile profile, long time_server, String error_text, String popup, String captcha_image_url) {
        this.menu = menu;
        this.token = token;
        this.status = status;
        this.toast = toast;
        this.profile = profile;
        this.time_server = time_server;
        this.error_text = error_text;
        this.popup = popup;
        this.captcha_image_url = captcha_image_url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPopup() {
        return popup;
    }

    public void setPopup(String popup) {
        this.popup = popup;
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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public long getTime_server() {
        return time_server;
    }

    public void setTime_server(long time_server) {
        this.time_server = time_server;
    }
}
