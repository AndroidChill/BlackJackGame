package com.example.blackjackgame.rModel.friends.request;

import com.google.gson.annotations.SerializedName;

public class Friends {

    @SerializedName("type")
    private int type;

    @SerializedName("nick")
    private String nick;

    @SerializedName("id")
    private int id;

    @SerializedName("user_avatar")
    private String avatar;

    public Friends(int type, String nick, int id, String avatar) {
        this.type = type;
        this.nick = nick;
        this.id = id;
        this.avatar = avatar;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
