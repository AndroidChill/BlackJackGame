package com.example.blackjackgame.model.profile.avatar;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AvatarBody {

    @SerializedName("menu")
    private String menu;

    @SerializedName("status")
    private String status;

    @SerializedName("toast")
    private String toast;

    @SerializedName("avatar")
    private List<Avatar> avatar;

    @SerializedName("error_text")
    private String error_text;

    @SerializedName("time_server")
    private long time_server;

    public AvatarBody(String menu, String status, String toast, List<Avatar> avatar, String error_text, long time_server) {
        this.menu = menu;
        this.status = status;
        this.toast = toast;
        this.avatar = avatar;
        this.error_text = error_text;
        this.time_server = time_server;
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

    public List<Avatar> getAvatar() {
        return avatar;
    }

    public void setAvatar(List<Avatar> avatar) {
        this.avatar = avatar;
    }

    public long getTime_server() {
        return time_server;
    }

    public void setTime_server(long time_server) {
        this.time_server = time_server;
    }
}
