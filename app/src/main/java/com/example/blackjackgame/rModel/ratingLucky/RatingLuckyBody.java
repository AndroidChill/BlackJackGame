package com.example.blackjackgame.rModel.ratingLucky;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RatingLuckyBody {

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

    @SerializedName("rating_user")
    private RatingUser user;

    @SerializedName("rating_coins_period")
    private List<RatingOtherUser> users;

    public RatingLuckyBody(String menu, String status, String statusText, String token, String captchaImageUrl, String popup, RatingUser user, List<RatingOtherUser> users) {
        this.menu = menu;
        this.status = status;
        this.statusText = statusText;
        this.token = token;
        this.captchaImageUrl = captchaImageUrl;
        this.popup = popup;
        this.user = user;
        this.users = users;
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

    public RatingUser getUser() {
        return user;
    }

    public void setUser(RatingUser user) {
        this.user = user;
    }

    public List<RatingOtherUser> getUsers() {
        return users;
    }

    public void setUsers(List<RatingOtherUser> users) {
        this.users = users;
    }

}
