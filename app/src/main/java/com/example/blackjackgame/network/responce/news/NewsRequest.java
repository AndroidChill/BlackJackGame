package com.example.blackjackgame.network.responce.news;

public class NewsRequest {

    private String menu;
    private int app_ver;
    private String ln;
    private String token;
    private String news_types;

    public NewsRequest(String menu, int app_ver, String ln, String token, String news_types) {
        this.menu = menu;
        this.app_ver = app_ver;
        this.ln = ln;
        this.token = token;
        this.news_types = news_types;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public void setApp_ver(int app_ver) {
        this.app_ver = app_ver;
    }

    public int getApp_ver() {
        return app_ver;
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

    public String getNews_types() {
        return news_types;
    }

    public void setNews_types(String news_types) {
        this.news_types = news_types;
    }
}
