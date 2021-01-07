package com.example.blackjackgame.rNetwork.request.friends.all;

import com.google.gson.annotations.SerializedName;

public class FriendsCreateRequest {

    private String menu;
    private int appVer;
    private String ln;
    private String token;
    @SerializedName("friends_id")
    private int userId;

    public FriendsCreateRequest(String menu, int appVer, String ln, String token, int userId) {
        this.menu = menu;
        this.appVer = appVer;
        this.ln = ln;
        this.token = token;
        this.userId = userId;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public int getAppVer() {
        return appVer;
    }

    public void setAppVer(int appVer) {
        this.appVer = appVer;
    }

    public String getLn() {
        return ln;
    }

    public void setLn(String ln) {
        this.ln = ln;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
