package com.example.blackjackgame.model.rating.ratingRich;

import com.example.blackjackgame.model.rating.RatingUserList;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RatingRichBody {

    @SerializedName("menu")
    private String menu;

    @SerializedName("status")
    private String status;

    @SerializedName("toast")
    private String toast;

    @SerializedName("rating_user")
    private RatingUser ratingUser;

    @SerializedName("rating_rich")
    private List<RatingRich> ratingRich;

    @SerializedName("error_text")
    private String error_text;

    @SerializedName("time_server")
    private long timeServer;

    public RatingRichBody(String menu, String status, String toast, RatingUser ratingUser, List<RatingRich> ratingRich, String error_text, long timeServer) {
        this.menu = menu;
        this.status = status;
        this.toast = toast;
        this.ratingUser = ratingUser;
        this.ratingRich = ratingRich;
        this.error_text = error_text;
        this.timeServer = timeServer;
    }

    public void setError_text(String error_text) {
        this.error_text = error_text;
    }

    public String getError_text() {
        return error_text;
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

    public RatingUser getRatingUser() {
        return ratingUser;
    }

    public void setRatingUser(RatingUser ratingUser) {
        this.ratingUser = ratingUser;
    }

    public List<RatingRich> getRatingRich() {
        return ratingRich;
    }

    public void setRatingRich(List<RatingRich> ratingRich) {
        this.ratingRich = ratingRich;
    }

    public long getTimeServer() {
        return timeServer;
    }

    public void setTimeServer(long timeServer) {
        this.timeServer = timeServer;
    }
}
