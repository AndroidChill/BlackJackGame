package com.example.blackjackgame.model.rating.ratingLuck;

import com.example.blackjackgame.model.rating.RatingUserList;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RatingLuckBody {

    @SerializedName("menu")
    private String menu;

    @SerializedName("status")
    private String status;

    @SerializedName("toast")
    private String toast;

    @SerializedName("rating_user")
    private RatingUser rating_user;

    @SerializedName("rating_coins_period")
    private List<RatingUserItem> rating_luck;

    @SerializedName("error_text")
    private String error_text;

    @SerializedName("time_server")
    private long timeServer;

    public RatingLuckBody(String menu, String status, String toast, RatingUser rating_user, List<RatingUserItem> rating_luck, String error_text, long timeServer) {
        this.menu = menu;
        this.status = status;
        this.toast = toast;
        this.rating_user = rating_user;
        this.rating_luck = rating_luck;
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

    public RatingUser getRating_user() {
        return rating_user;
    }

    public void setRating_user(RatingUser rating_user) {
        this.rating_user = rating_user;
    }

    public List<RatingUserItem> getRating_luck() {
        return rating_luck;
    }

    public void setRating_luck(List<RatingUserItem> rating_luck) {
        this.rating_luck = rating_luck;
    }

    public long getTimeServer() {
        return timeServer;
    }

    public void setTimeServer(long timeServer) {
        this.timeServer = timeServer;
    }
}
