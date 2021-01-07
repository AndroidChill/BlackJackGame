package com.example.blackjackgame.rNetwork.request.friends.request;

import com.google.gson.annotations.SerializedName;

public class FriendsRequestRequest {

    private String menu;
    @SerializedName("app_ver")
    private int appVer;
    private String ln;
    private String token;

    public FriendsRequestRequest(String menu, int appVer, String ln, String token) {
        this.menu = menu;
        this.appVer = appVer;
        this.ln = ln;
        this.token = token;
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

}
