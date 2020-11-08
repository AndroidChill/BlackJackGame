package com.example.blackjackgame.rNetwork.request.profileSend;

import com.example.blackjackgame.rModel.profile.Profile;
import com.google.gson.annotations.SerializedName;

public class ProfileSendRequest {

    private String menu;
    @SerializedName("app_ver")
    private int appVer;
    private String ln;
    private String token;
    private Profile profile;

    public ProfileSendRequest(String menu, int appVer, String ln, String token, Profile profile) {
        this.menu = menu;
        this.appVer = appVer;
        this.ln = ln;
        this.token = token;
        this.profile = profile;
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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
