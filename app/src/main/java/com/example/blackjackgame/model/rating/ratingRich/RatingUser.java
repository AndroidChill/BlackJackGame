package com.example.blackjackgame.model.rating.ratingRich;

import com.google.gson.annotations.SerializedName;

public class RatingUser {

    @SerializedName("coins_period")
    private long coinsPeriod;

    @SerializedName("position")
    private int position;

    public RatingUser(long coinsPeriod) {
        this.coinsPeriod = coinsPeriod;
    }

    public RatingUser(long coinsPeriod, int position) {
        this.coinsPeriod = coinsPeriod;
        this.position = position;
    }

    public long getCoinsPeriod() {
        return coinsPeriod;
    }

    public void setCoinsPeriod(long coinsPeriod) {
        this.coinsPeriod = coinsPeriod;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
