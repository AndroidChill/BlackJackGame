package com.example.blackjackgame.model.getmonet;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetMonetBody {

    @SerializedName("name")
    private String menu;

    @SerializedName("status")
    private String status;

    @SerializedName("toast")
    private String toast;

    @SerializedName("coins_get")
    private List<CoinsGet> coins_get;

    @SerializedName("error_text")
    private String error_text;

    @SerializedName("time_server")
    private long time_server;

    @SerializedName("image_captcha_url")
    private String image_captcha_url;

    @SerializedName("popup")
    private String popup;

    @SerializedName("token")
    private String token;

    public GetMonetBody(String menu, String status, String toast, List<CoinsGet> coins_get, String error_text, long time_server) {
        this.menu = menu;
        this.status = status;
        this.toast = toast;
        this.coins_get = coins_get;
        this.error_text = error_text;
        this.time_server = time_server;
    }

    public GetMonetBody(String menu, String status, String toast, List<CoinsGet> coins_get, String error_text, long time_server, String image_captcha_url, String popup) {
        this.menu = menu;
        this.status = status;
        this.toast = toast;
        this.coins_get = coins_get;
        this.error_text = error_text;
        this.time_server = time_server;
        this.image_captcha_url = image_captcha_url;
        this.popup = popup;
    }

    public GetMonetBody(String menu, String status, String toast, List<CoinsGet> coins_get, String error_text, long time_server, String image_captcha_url, String popup, String token) {
        this.menu = menu;
        this.status = status;
        this.toast = toast;
        this.coins_get = coins_get;
        this.error_text = error_text;
        this.time_server = time_server;
        this.image_captcha_url = image_captcha_url;
        this.popup = popup;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getImage_captcha_url() {
        return image_captcha_url;
    }

    public void setImage_captcha_url(String image_captcha_url) {
        this.image_captcha_url = image_captcha_url;
    }

    public String getPopup() {
        return popup;
    }

    public void setPopup(String popup) {
        this.popup = popup;
    }

    public void setError_text(String error_text) {
        this.error_text = error_text;
    }

    public String getError_text() {
        return error_text;
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

    public List<CoinsGet> getCoins_get() {
        return coins_get;
    }

    public void setCoins_get(List<CoinsGet> coins_get) {
        this.coins_get = coins_get;
    }

    public long getTime_server() {
        return time_server;
    }

    public void setTime_server(long time_server) {
        this.time_server = time_server;
    }
}
