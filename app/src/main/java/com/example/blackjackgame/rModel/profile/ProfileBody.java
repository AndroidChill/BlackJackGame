package com.example.blackjackgame.rModel.profile;

import com.google.gson.annotations.SerializedName;

public class ProfileBody {

    @SerializedName("menu")
    private String menu;

    @SerializedName("status")
    private String status;

    @SerializedName("status_text")
    private String statusText;

    @SerializedName("token")
    private String token;

    @SerializedName("captcha_image_url")
    private String captchaImageUrl;

    @SerializedName("popup")
    private String popup;

    @SerializedName("profile")
    private Profile profile;

    public ProfileBody(String menu, String status, String statusText, String token, String captchaImageUrl, String popup, Profile profile) {
        this.menu = menu;
        this.status = status;
        this.statusText = statusText;
        this.token = token;
        this.captchaImageUrl = captchaImageUrl;
        this.popup = popup;
        this.profile = profile;
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

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
