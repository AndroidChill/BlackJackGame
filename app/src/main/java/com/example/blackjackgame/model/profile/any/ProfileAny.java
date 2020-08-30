package com.example.blackjackgame.model.profile.any;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileAny {

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("time_active")
    private long time_active;

    @SerializedName("user_coins")
    private long user_coins;

    @SerializedName("user_nickname")
    private String user_nickname;

    @SerializedName("user_name")
    private String user_name;

    @SerializedName("user_surname")
    private String user_surname;

    @SerializedName("user_info")
    private String user_info;

    @SerializedName("user_rating")
    private long user_rating;

    @SerializedName("user_avatar")
    private String user_avatar;

    @SerializedName("coins_transfer")
    private int coins_transfer;

    @SerializedName("progress")
    private List<Progress> progress;

    public ProfileAny(String user_id, long time_active, long user_coins, String user_nickname, String user_name, String user_surname, String user_info, long user_rating, String user_avatar, int coins_transfer, List<Progress> progress) {
        this.user_id = user_id;
        this.time_active = time_active;
        this.user_coins = user_coins;
        this.user_nickname = user_nickname;
        this.user_name = user_name;
        this.user_surname = user_surname;
        this.user_info = user_info;
        this.user_rating = user_rating;
        this.user_avatar = user_avatar;
        this.coins_transfer = coins_transfer;
        this.progress = progress;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public long getTime_active() {
        return time_active;
    }

    public void setTime_active(long time_active) {
        this.time_active = time_active;
    }

    public long getUser_coins() {
        return user_coins;
    }

    public void setUser_coins(long user_coins) {
        this.user_coins = user_coins;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_surname() {
        return user_surname;
    }

    public void setUser_surname(String user_surname) {
        this.user_surname = user_surname;
    }

    public String getUser_info() {
        return user_info;
    }

    public void setUser_info(String user_info) {
        this.user_info = user_info;
    }

    public long getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(long user_rating) {
        this.user_rating = user_rating;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public int getCoins_transfer() {
        return coins_transfer;
    }

    public void setCoins_transfer(int coins_transfer) {
        this.coins_transfer = coins_transfer;
    }

    public List<Progress> getProgress() {
        return progress;
    }

    public void setProgress(List<Progress> progress) {
        this.progress = progress;
    }

}

