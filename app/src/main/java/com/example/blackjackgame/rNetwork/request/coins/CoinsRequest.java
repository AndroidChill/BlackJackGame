package com.example.blackjackgame.rNetwork.request.coins;

import com.google.gson.annotations.SerializedName;

public class CoinsRequest {

    private String menu;
    @SerializedName("app_ver")
    private int appVer;
    private String ln;
    private String token;
    private String id;

    public CoinsRequest(String menu, int appVer, String ln, String token) {
        this.menu = menu;
        this.appVer = appVer;
        this.ln = ln;
        this.token = token;
    }

    public CoinsRequest(String menu, int appVer, String ln, String token, String id) {
        this.menu = menu;
        this.appVer = appVer;
        this.ln = ln;
        this.token = token;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
