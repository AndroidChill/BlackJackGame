package com.example.blackjackgame.model.news;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class NewsBody {

    @SerializedName("menu")
    private String menu;

    @SerializedName("status")
    private String status;

    @SerializedName("token")
    private String token;

    @SerializedName("news")
    private List<News> news = new ArrayList<>();

    @SerializedName("time_server")
    private long time_server;


    public NewsBody(String menu, String status, String token, List<News> news, Long time_server) {
        this.menu = menu;
        this.status = status;
        this.token = token;
        this.news = news;
        this.time_server = time_server;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public Long getTime_server() {
        return time_server;
    }

    public void setTime_server(Long time_server) {
        this.time_server = time_server;
    }


}
