package com.example.blackjackgame.rNetwork.request.profile;

import com.google.gson.annotations.SerializedName;

public class ProfileRequest {

    private String menu;
    @SerializedName("app_ver")
    private int appVer;
    private String ln;
    private String token;

    public ProfileRequest(String menu, int appVer, String ln, String token) {
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

    public int getApp_ver() {
        return appVer;
    }

    public void setApp_ver(int appVer) {
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
