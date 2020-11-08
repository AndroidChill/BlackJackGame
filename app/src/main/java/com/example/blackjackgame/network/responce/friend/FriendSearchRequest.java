package com.example.blackjackgame.network.responce.friend;

public class FriendSearchRequest {

    private String menu;
    private int app_ver;
    private String ln;
    private String token;
    private String search;

    public FriendSearchRequest(String menu, int app_ver, String ln, String token, String search) {
        this.menu = menu;
        this.app_ver = app_ver;
        this.ln = ln;
        this.token = token;
        this.search = search;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
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

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
