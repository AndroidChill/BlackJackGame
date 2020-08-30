package com.example.blackjackgame.model.rating.ratingLuck;

import com.google.gson.annotations.SerializedName;

public class RatingUser {

    @SerializedName("coins_period")
    private String coins;

    @SerializedName("position")
    private int position;

    public RatingUser(String coins, int position) {
        this.coins = coins;
        this.position = position;
    }

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
