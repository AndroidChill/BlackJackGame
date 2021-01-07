package com.example.blackjackgame.rNetwork.request.inviteGame;

import com.google.gson.annotations.SerializedName;

public class InviteGameApplyRequest {

    private String menu;
    @SerializedName("app_ver")
    private int appVer;
    private String ln;
    private String token;
    @SerializedName("invite_game_id")
    private int gameId;

    public InviteGameApplyRequest(String menu, int appVer, String ln, String token, int gameId) {
        this.menu = menu;
        this.appVer = appVer;
        this.ln = ln;
        this.token = token;
        this.gameId = gameId;
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

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
