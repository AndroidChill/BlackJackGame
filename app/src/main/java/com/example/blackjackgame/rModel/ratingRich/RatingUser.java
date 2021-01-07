package com.example.blackjackgame.rModel.ratingRich;

import com.google.gson.annotations.SerializedName;

public class RatingUser {

    @SerializedName("coins")
    private int rating;

    @SerializedName("position")
    private int position;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("user_nickname")
    private String nick;

    @SerializedName("user_id")
    private int id;

    public RatingUser(int rating, int position) {
        this.rating = rating;
        this.position = position;
    }

    public RatingUser(int rating, int position, String avatar, String nick, int id) {
        this.rating = rating;
        this.position = position;
        this.avatar = avatar;
        this.nick = nick;
        this.id = id;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
