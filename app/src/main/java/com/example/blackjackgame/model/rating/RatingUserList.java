package com.example.blackjackgame.model.rating;

import com.google.gson.annotations.SerializedName;

public class RatingUserList {

    @SerializedName("nick")
    private String nick;

    @SerializedName("user_id")
    private int user_id;

    @SerializedName("rating")
    private long coins;

    @SerializedName("avatar")
    private String avatar;

    public RatingUserList(String nick, long coins, String avatar) {
        this.nick = nick;
        this.coins = coins;
        this.avatar = avatar;
    }

    public RatingUserList(String nick, int user_id, long coins, String avatar) {
        this.nick = nick;
        this.user_id = user_id;
        this.coins = coins;
        this.avatar = avatar;
    }


    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public long getCoins() {
        return coins;
    }

    public void setCoins(long coins) {
        this.coins = coins;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
