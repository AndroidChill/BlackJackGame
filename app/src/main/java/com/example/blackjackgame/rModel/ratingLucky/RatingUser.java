package com.example.blackjackgame.rModel.ratingLucky;

import com.google.gson.annotations.SerializedName;

public class RatingUser {

    @SerializedName("coins_period")
    private int rating;

    @SerializedName("position")
    private int position;

    @SerializedName("user_nickname")
    private String nick;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("user_id")
    private int id;

    public RatingUser(int rating, int position) {
        this.rating = rating;
        this.position = position;
    }

    public RatingUser(int rating, int position, String nick, String avatar) {
        this.rating = rating;
        this.position = position;
        this.nick = nick;
        this.avatar = avatar;
    }

    public RatingUser(int rating, int position, String nick, String avatar, int id) {
        this.rating = rating;
        this.position = position;
        this.nick = nick;
        this.avatar = avatar;
        this.id = id;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
