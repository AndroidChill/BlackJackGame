package com.example.blackjackgame.model.profile.changeData;

import com.google.gson.annotations.SerializedName;

public class ProfileChangeBody {

    @SerializedName("menu")
    private String menu;

    @SerializedName("status")
    private String status;

    @SerializedName("time_server")
    private long time_server;

    public ProfileChangeBody(String menu, String status, long time_server) {
        this.menu = menu;
        this.status = status;
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

    public long getTime_server() {
        return time_server;
    }

    public void setTime_server(long time_server) {
        this.time_server = time_server;
    }
}
