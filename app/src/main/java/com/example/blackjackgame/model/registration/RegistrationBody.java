package com.example.blackjackgame.model.registration;

import com.google.gson.annotations.SerializedName;

public class RegistrationBody {

    @SerializedName("manu")
    private String menu;

    @SerializedName("status")
    private String status;

    @SerializedName("error_text")
    private String errorText;

    @SerializedName("captcha_image_url")
    private String captchaImageUrl;

    @SerializedName("token")
    private String token;

    @SerializedName("popup")
    private String popup;

    public RegistrationBody(String menu, String status, String errorText, String captchaImageUrl, String token, String popup) {
        this.menu = menu;
        this.status = status;
        this.errorText = errorText;
        this.captchaImageUrl = captchaImageUrl;
        this.token = token;
        this.popup = popup;
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

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
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

    public String getPopup() {
        return popup;
    }

    public void setPopup(String popup) {
        this.popup = popup;
    }
}
