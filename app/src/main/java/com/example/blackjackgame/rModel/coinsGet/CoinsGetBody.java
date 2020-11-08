package com.example.blackjackgame.rModel.coinsGet;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CoinsGetBody {

    @SerializedName("menu")
    private String menu;

    @SerializedName("status")
    private String status;

    @SerializedName("status_text")
    private String statusText;

    @SerializedName("token")
    private String token;

    @SerializedName("captcha_image_url")
    private String imageCaptchaUrl;

    @SerializedName("popup")
    private String popup;

    @SerializedName("coins_get")
    private List<CoinsGet> coinsGets;

    public CoinsGetBody(String menu, String status, String statusText, String token, String imageCaptchaUrl, String popup, List<CoinsGet> coinsGets) {
        this.menu = menu;
        this.status = status;
        this.statusText = statusText;
        this.token = token;
        this.imageCaptchaUrl = imageCaptchaUrl;
        this.popup = popup;
        this.coinsGets = coinsGets;
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

    public String getImageCaptchaUrl() {
        return imageCaptchaUrl;
    }

    public void setImageCaptchaUrl(String imageCaptchaUrl) {
        this.imageCaptchaUrl = imageCaptchaUrl;
    }

    public String getPopup() {
        return popup;
    }

    public void setPopup(String popup) {
        this.popup = popup;
    }

    public List<CoinsGet> getCoinsGets() {
        return coinsGets;
    }

    public void setCoinsGets(List<CoinsGet> coinsGets) {
        this.coinsGets = coinsGets;
    }
}
