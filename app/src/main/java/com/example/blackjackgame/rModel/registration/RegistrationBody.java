package com.example.blackjackgame.rModel.registration;

import com.google.gson.annotations.SerializedName;

public class RegistrationBody {

    @SerializedName("menu")
    private String menu;

    @SerializedName("status")
    private String status;

    @SerializedName("status_text")
    private String statusText;

    @SerializedName("captcha_image_url")
    private String captchaImageUrl;

    @SerializedName("token")
    private String token;

    public RegistrationBody(String menu, String status, String statusText, String captchaImageUrl, String token) {
        this.menu = menu;
        this.status = status;
        this.statusText = statusText;
        this.captchaImageUrl = captchaImageUrl;
        this.token = token;
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

    public String getCaptchaImageUrl() {
        return captchaImageUrl;
    }

    public void setCaptchaImageUrl(String captchaImageUrl) {
        this.captchaImageUrl = captchaImageUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
