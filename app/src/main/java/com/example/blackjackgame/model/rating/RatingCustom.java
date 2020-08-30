package com.example.blackjackgame.model.rating;

import com.example.blackjackgame.model.rating.ratingLuck.RatingUser;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RatingCustom {

    @SerializedName("menu")
    private String menu;

    @SerializedName("status")
    private String status;

    @SerializedName("toast")
    private String toast;

    @SerializedName("rating_user")
    private RatingUser ratingUser;

    @SerializedName("rating")
    private List<RatingUserList> rating;

    @SerializedName("error_text")
    private String error_text;

    @SerializedName("time_server")
    private long timeServer;

    public RatingCustom(String menu, String status, String toast, RatingUser ratingUser, List<RatingUserList> rating, String error_text, long timeServer) {
        this.menu = menu;
        this.status = status;
        this.toast = toast;
        this.ratingUser = ratingUser;
        this.rating = rating;
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

    public RatingUser getRatingUser() {
        return ratingUser;
    }

    public void setRatingUser(RatingUser ratingUser) {
        this.ratingUser = ratingUser;
    }

    public List<RatingUserList> getRating() {
        return rating;
    }

    public void setRating(List<RatingUserList> rating) {
        this.rating = rating;
    }

    public long getTimeServer() {
        return timeServer;
    }

    public void setTimeServer(long timeServer) {
        this.timeServer = timeServer;
    }
}
