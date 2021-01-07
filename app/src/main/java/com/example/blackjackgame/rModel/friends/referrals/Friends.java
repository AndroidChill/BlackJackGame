package com.example.blackjackgame.rModel.friends.referrals;

import com.google.gson.annotations.SerializedName;

public class Friends implements Comparable<Friends>{

    @SerializedName("nick")
    private String nick;

    @SerializedName("user_avatar")
    private String avatar;

    @SerializedName("id")
    private int id;

    public Friends(String nick, String avatar, int id) {
        this.nick = nick;
        this.avatar = avatar;
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Friends o) {
        if (o.getNick().compareTo(o.nick) > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
