package com.example.blackjackgame.network.responce.profile.change;

import com.example.blackjackgame.model.profile.Profile;

public class ProfileChangeDataRequest {

    private String menu;
    private int app_ver;
    private String ln;
    private String token;
    private com.example.blackjackgame.model.profile.Profile profile;

    public ProfileChangeDataRequest(String menu, int app_ver, String ln, String token, Profile profile) {
        this.menu = menu;
        this.app_ver = app_ver;
        this.ln = ln;
        this.token = token;
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
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

}
