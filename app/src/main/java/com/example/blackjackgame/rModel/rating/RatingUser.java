package com.example.blackjackgame.rModel.rating;

import com.google.gson.annotations.SerializedName;

public class RatingUser {

    @SerializedName("user_nickname")
    private String nick;

    @SerializedName("rating")
    private int rating;

    @SerializedName("position")
    private int position;

    @SerializedName("avatar")
    private String avatar;

    public RatingUser(int rating, int position) {
        this.rating = rating;
        this.position = position;
    }

    public RatingUser(String nick, int rating, int position) {
        this.nick = nick;
        this.rating = rating;
        this.position = position;
    }

    public RatingUser(String nick, int rating, int position, String avatar) {
        this.nick = nick;
        this.rating = rating;
        this.position = position;
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
