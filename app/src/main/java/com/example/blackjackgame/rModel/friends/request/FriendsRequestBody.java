package com.example.blackjackgame.rModel.friends.request;

import com.example.blackjackgame.rModel.friends.request.Friends;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FriendsRequestBody {

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

    @SerializedName("friends_request")
    private List<Friends> friends;

    public FriendsRequestBody(String menu, String status, String statusText, String token, String captchaImageUrl, String popup, List<Friends> friends) {
        this.menu = menu;
        this.status = status;
        this.statusText = statusText;
        this.token = token;
        this.captchaImageUrl = captchaImageUrl;
        this.popup = popup;
        this.friends = friends;
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

    public List<Friends> getFriends() {
        return friends;
    }

    public void setFriends(List<Friends> friends) {
        this.friends = friends;
    }
}
