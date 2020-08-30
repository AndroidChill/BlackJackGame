package com.example.blackjackgame.network.responce.friend.request;

public class FriendsDelRequest {

    private String menu;
    private String app_ver;
    private String ln;
    private String token;
    private long friends_id;

    public FriendsDelRequest(String menu, String app_ver, String ln, String token, long friends_id) {
        this.menu = menu;
        this.app_ver = app_ver;
        this.ln = ln;
        this.token = token;
        this.friends_id = friends_id;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getApp_ver() {
        return app_ver;
    }

    public void setApp_ver(String app_ver) {
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

    public long getFriends_id() {
        return friends_id;
    }

    public void setFriends_id(long friends_id) {
        this.friends_id = friends_id;
    }

}
