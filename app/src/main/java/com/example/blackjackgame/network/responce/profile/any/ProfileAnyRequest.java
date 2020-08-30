package com.example.blackjackgame.network.responce.profile.any;

public class ProfileAnyRequest {

    private String menu;
    private String app_ver;
    private String ln;
    private String token;
    private int profile_any_id;

    public ProfileAnyRequest(String menu, String app_ver, String ln, String token, int profile_any_id) {
        this.menu = menu;
        this.app_ver = app_ver;
        this.ln = ln;
        this.token = token;
        this.profile_any_id = profile_any_id;
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

    public int getProfile_any_id() {
        return profile_any_id;
    }

    public void setProfile_any_id(int profile_any_id) {
        this.profile_any_id = profile_any_id;
    }
}
