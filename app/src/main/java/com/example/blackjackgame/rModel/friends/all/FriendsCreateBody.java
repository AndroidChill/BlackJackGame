package com.example.blackjackgame.rModel.friends.all;

public class FriendsCreateBody {

    private String menu;
    private String status;
    private String statusText;
    private String token;
    private String captchaImageUrl;
    private String popup;

    public FriendsCreateBody(String menu, String status, String statusText, String token) {
        this.menu = menu;
        this.status = status;
        this.statusText = statusText;
        this.token = token;
    }

    public FriendsCreateBody(String menu, String status, String statusText, String token, String captchaImageUrl, String popup) {
        this.menu = menu;
        this.status = status;
        this.statusText = statusText;
        this.token = token;
        this.captchaImageUrl = captchaImageUrl;
        this.popup = popup;
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
}
