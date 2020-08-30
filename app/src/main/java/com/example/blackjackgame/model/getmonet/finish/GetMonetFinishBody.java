package com.example.blackjackgame.model.getmonet.finish;

import com.google.gson.annotations.SerializedName;

public class GetMonetFinishBody {

    @SerializedName("menu")
    private String menu;

    @SerializedName("status")
    private String status;

    @SerializedName("error_text")
    private String error_text;

    @SerializedName("toast")
    private String toast;

    @SerializedName("time_server")
    private String time_server;

    public GetMonetFinishBody(String menu, String status, String error_text, String toast, String time_server) {
        this.menu = menu;
        this.status = status;
        this.error_text = error_text;
        this.toast = toast;
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

    public String getError_text() {
        return error_text;
    }

    public void setError_text(String error_text) {
        this.error_text = error_text;
    }

    public String getToast() {
        return toast;
    }

    public void setToast(String toast) {
        this.toast = toast;
    }

    public String getTime_server() {
        return time_server;
    }

    public void setTime_server(String time_server) {
        this.time_server = time_server;
    }
}
