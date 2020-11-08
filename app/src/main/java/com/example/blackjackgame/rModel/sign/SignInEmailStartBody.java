package com.example.blackjackgame.rModel.sign;

import com.google.gson.annotations.SerializedName;

public class SignInEmailStartBody {

    @SerializedName("captcha_image_url")
    private String captchaImageUrl;

    @SerializedName("status")
    private String status;

    @SerializedName("status_text")
    private String errorText;

    @SerializedName("popup")
    private String popup;

    @SerializedName("token")
    private String token;

    public SignInEmailStartBody(String captchaImageUrl, String status, String errorText, String popup) {
        this.captchaImageUrl = captchaImageUrl;
        this.status = status;
        this.errorText = errorText;
        this.popup = popup;
    }

    public SignInEmailStartBody(String captchaImageUrl, String status, String errorText, String popup, String token) {
        this.captchaImageUrl = captchaImageUrl;
        this.status = status;
        this.errorText = errorText;
        this.popup = popup;
        this.token = token;
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

    public String getPopup() {
        return popup;
    }

    public void setPopup(String popup) {
        this.popup = popup;
    }

}
