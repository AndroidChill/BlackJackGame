package com.example.blackjackgame.model.friend.referrals;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReferralsBody {

    @SerializedName("menu")
    private String menu;

    @SerializedName("status")
    private String status;

    @SerializedName("toast")
    private String toast;

    @SerializedName("referals")
    private List<Referrals> referals;

    @SerializedName("time_server")
    private long time_server;

    @SerializedName("error_text")
    private String error_text;

    public ReferralsBody(String menu, String status, String toast, List<Referrals> referals, long time_server, String error_text) {
        this.menu = menu;
        this.status = status;
        this.toast = toast;
        this.referals = referals;
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

    public List<Referrals> getReferals() {
        return referals;
    }

    public void setReferals(List<Referrals> referals) {
        this.referals = referals;
    }

    public long getTime_server() {
        return time_server;
    }

    public void setTime_server(long time_server) {
        this.time_server = time_server;
    }
}
