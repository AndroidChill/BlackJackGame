package com.example.blackjackgame.rModel.ratingRich;

import com.google.gson.annotations.SerializedName;

public class RatingOtherUser {

    @SerializedName("nick")
    private String nick;

    @SerializedName("user_id")
    private int id;

    @SerializedName("coins")
    private int rating;

    @SerializedName("avatar")
    private String avatar;

    public RatingOtherUser(String nick, int id, int rating, String avatar) {
        this.nick = nick;
        this.id = id;
        this.rating = rating;
        this.avatar = avatar;
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

}
