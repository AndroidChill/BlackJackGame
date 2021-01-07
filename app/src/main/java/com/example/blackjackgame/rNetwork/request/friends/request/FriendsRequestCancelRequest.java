package com.example.blackjackgame.rNetwork.request.friends.request;

import com.google.gson.annotations.SerializedName;

public class FriendsRequestCancelRequest {

    private String menu;
    private int appVer;
    private String ln;
    private String token;
    @SerializedName("friends_id")
    private int friendsId;

    public FriendsRequestCancelRequest(String menu, int appVer, String ln, String token, int friendsId) {
        this.menu = menu;
        this.appVer = appVer;
        this.ln = ln;
        this.token = token;
        this.friendsId = friendsId;
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

    public int getFriendsId() {
        return friendsId;
    }

    public void setFriendsId(int friendsId) {
        this.friendsId = friendsId;
    }
}
