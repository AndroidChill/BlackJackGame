package com.example.blackjackgame.network.responce.friend;

public class GiveMonetRequest {

    private String menu;
    private int app_ver;
    private String ln;
    private String token;
    private int coins_transfer_user_id;
    private int coins;

    public GiveMonetRequest(String menu, int app_ver, String ln, String token, int coins_transfer_user_id, int coins) {
        this.menu = menu;
        this.app_ver = app_ver;
        this.ln = ln;
        this.token = token;
        this.coins_transfer_user_id = coins_transfer_user_id;
        this.coins = coins;
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

    public int getCoins_transfer_user_id() {
        return coins_transfer_user_id;
    }

    public void setCoins_transfer_user_id(int coins_transfer_user_id) {
        this.coins_transfer_user_id = coins_transfer_user_id;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
