package com.example.blackjackgame.model.friend;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FriendBody {

    @SerializedName("menu")
    private String menu;

    @SerializedName("status")
    private String status;

    @SerializedName("toast")
    private String toast;

    @SerializedName("friends")
    private List<Friend> friends;

    @SerializedName("error_text")
    private String error_text;

    @SerializedName("time_server")
    private long timeServer;

    public FriendBody(String menu, String status, String toast, List<Friend> friends, String error_text, long timeServer) {
        this.menu = menu;
        this.status = status;
        this.toast = toast;
        this.friends = friends;
        this.error_text = error_text;
        this.timeServer = timeServer;
    }

    public String getError_text() {
        return error_text;
    }

    public void setError_text(String error_text) {
        this.error_text = error_text;
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

    public String getToast() {
        return toast;
    }

    public void setToast(String toast) {
        this.toast = toast;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public long getTimeServer() {
        return timeServer;
    }

    public void setTimeServer(long timeServer) {
        this.timeServer = timeServer;
    }

}
