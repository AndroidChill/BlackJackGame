package com.example.blackjackgame.model.profile.any;

import com.google.gson.annotations.SerializedName;

public class ProfileAnyBody {

    @SerializedName("menu")
    private String menu;

    @SerializedName("status")
    private String status;

    @SerializedName("toast")
    private String toast;

    @SerializedName("profile_any")
    private ProfileAny profile_any;

    @SerializedName("time_server")
    private String time_server;

    @SerializedName("error_text")
    private String error_text;

    public ProfileAnyBody(String menu, String status, String toast, ProfileAny profile_any, String time_server, String error_text) {
        this.menu = menu;
        this.status = status;
        this.toast = toast;
        this.profile_any = profile_any;
        this.time_server = time_server;
        this.error_text = error_text;
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

    public ProfileAny getProfile_any() {
        return profile_any;
    }

    public void setProfile_any(ProfileAny profile_any) {
        this.profile_any = profile_any;
    }

    public String getTime_server() {
        return time_server;
    }

    public void setTime_server(String time_server) {
        this.time_server = time_server;
    }
}
