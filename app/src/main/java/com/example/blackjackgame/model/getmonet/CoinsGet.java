package com.example.blackjackgame.model.getmonet;

import com.google.gson.annotations.SerializedName;

public class CoinsGet {

    @SerializedName("type")
    private int type;

    @SerializedName("text")
    private String text;

    @SerializedName("coins")
    private long coins;

    public CoinsGet(int type, String text, long coins) {
        this.type = type;
        this.text = text;
        this.coins = coins;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getCoins() {
        return coins;
    }

    public void setCoins(long coins) {
        this.coins = coins;
    }
}
