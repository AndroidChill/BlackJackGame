package com.example.blackjackgame.model.friend.request.output;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FriendsZaprosBody {

    @SerializedName("menu")
    private String menu;

    @SerializedName("status")
    private String status;

    @SerializedName("toast")
    private String toast;

    @SerializedName("friends_request")
    private List<FriendsZapros> friends_request;

    @SerializedName("time_server")
    private long time_server;

    @SerializedName("error_text")
    private String error_text;

    public FriendsZaprosBody(String menu, String status, String toast, List<FriendsZapros> friends_request, long time_server, String error_text) {
        this.menu = menu;
        this.status = status;
        this.toast = toast;
        this.friends_request = friends_request;
        this.time_server = time_server;
        this.error_text = error_text;
    }

    public String getError_text() {
        return error_text;
    }

    public void setError_text(String error_text) {
        this.error_text = error_text;
    }

//    public FriendsZaprosBody(String menu, String status, String toast, List<FriendsZapros> friends_request, long time_server) {
//        this.menu = menu;
//        this.status = status;
//        this.toast = toast;
//        this.friends_request = friends_request;
//        this.time_server = time_server;
//    }

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

    public List<FriendsZapros> getFriends_request() {
        return friends_request;
    }

    public void setFriends_request(List<FriendsZapros> friends_request) {
        this.friends_request = friends_request;
    }

    public long getTime_server() {
        return time_server;
    }

    public void setTime_server(long time_server) {
        this.time_server = time_server;
    }
}
