package com.example.blackjackgame.rModel.profileAny;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileAny{

    @SerializedName("user_id")
    private int id;

    @SerializedName("time_active")
    private long time;

    @SerializedName("user_coins")
    private int coins;

    @SerializedName("user_nickname")
    private String nickname;

    @SerializedName("user_name")
    private String name;

    @SerializedName("user_surname")
    private String surname;

    @SerializedName("user_info")
    private String info;

    @SerializedName("user_rating")
    private int rating;

    @SerializedName("user_avatar")
    private String avatar;

    @SerializedName("coins_transfer")
    private int coinsTransfer;

    @SerializedName("friend_status")
    private int friendStatus;

    @SerializedName("progress")
    private List<Progress> progresses;

    public ProfileAny(int id, long time, int coins, String nickname, String name, String surname, String info, int rating, String avatar, int coinsTransfer, int friendStatus, List<Progress> progresses) {
        this.id = id;
        this.time = time;
        this.coins = coins;
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
        this.info = info;
        this.rating = rating;
        this.avatar = avatar;
        this.coinsTransfer = coinsTransfer;
        this.friendStatus = friendStatus;
        this.progresses = progresses;
    }

    public int getFriendStatus() {
        return friendStatus;
    }

    public void setFriendStatus(int friendStatus) {
        this.friendStatus = friendStatus;
    }

    public ProfileAny(int id, long time, int coins, String nickname, String name, String surname, String info, int rating, String avatar, int coinsTransfer, List<Progress> progresses) {
        this.id = id;
        this.time = time;
        this.coins = coins;
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
        this.info = info;
        this.rating = rating;
        this.avatar = avatar;
        this.coinsTransfer = coinsTransfer;
        this.progresses = progresses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getCoinsTransfer() {
        return coinsTransfer;
    }

    public void setCoinsTransfer(int coinsTransfer) {
        this.coinsTransfer = coinsTransfer;
    }

    public List<Progress> getProgresses() {
        return progresses;
    }

    public void setProgresses(List<Progress> progresses) {
        this.progresses = progresses;
    }
}
