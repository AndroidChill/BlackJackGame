package com.example.blackjackgame.model.friend;

import com.google.gson.annotations.SerializedName;

public class Friend {

    @SerializedName("id")
    private int id;

    @SerializedName("nick")
    private String nick;

    @SerializedName("user_avatar")
    private String userAvatar;


    public Friend(int id, String nick, String userAvatar) {
        this.id = id;
        this.nick = nick;
        this.userAvatar = userAvatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
}
