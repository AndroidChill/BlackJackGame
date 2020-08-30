package com.example.blackjackgame.model.friend.referrals;

import com.google.gson.annotations.SerializedName;

public class Referrals {

    @SerializedName("nick")
    private String nick;

    @SerializedName("id")
    private long id;

    @SerializedName("user_avatar")
    private String user_avatar;

    public Referrals(String nick, long id, String user_avatar) {
        this.nick = nick;
        this.id = id;
        this.user_avatar = user_avatar;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }
}
