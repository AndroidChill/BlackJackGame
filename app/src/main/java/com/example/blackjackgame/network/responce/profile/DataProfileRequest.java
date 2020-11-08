package com.example.blackjackgame.network.responce.profile;

public class DataProfileRequest {

    private String menu;
    private int app_ver;
    private String ln;
    private String token;

    public DataProfileRequest(String menu, int app_ver, String ln, String token) {
        this.menu = menu;
        this.app_ver = app_ver;
        this.ln = ln;
        this.token = token;
    }

    public int getApp_ver() {
        return app_ver;
    }

    public void setApp_ver(int app_ver) {
        this.app_ver = app_ver;
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

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
}
