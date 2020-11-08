package com.example.blackjackgame.rModel.sign;

import com.google.gson.annotations.SerializedName;

public class SignInEmailCodeBody {

    @SerializedName("menu")
    private String menu;

    @SerializedName("status")
    private String status;

    @SerializedName("captcha_image_url")
    private String captchaImageUrl;

    @SerializedName("status_text")
    private String errorText;

    @SerializedName("token")
    private String token;

    public SignInEmailCodeBody(String menu, String status, String captchaImageUrl, String errorText, String token) {
        this.menu = menu;
        this.status = status;
        this.captchaImageUrl = captchaImageUrl;
        this.errorText = errorText;
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

    public String getCaptchaImageUrl() {
        return captchaImageUrl;
    }

    public void setCaptchaImageUrl(String captchaImageUrl) {
        this.captchaImageUrl = captchaImageUrl;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
