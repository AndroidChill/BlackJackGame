package com.example.blackjackgame.model.friend.request.output;

import com.google.gson.annotations.SerializedName;

public class FriendsZapros {

    @SerializedName("type")
    private int type;

    @SerializedName("nick")
    private String nick;

    @SerializedName("id")
    private long id;

    @SerializedName("user_avatar")
    private String user_avatar;

    public FriendsZapros(int type, String nick, long id, String user_avatar) {
        this.type = type;
        this.nick = nick;
        this.id = id;
        this.user_avatar = user_avatar;
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
