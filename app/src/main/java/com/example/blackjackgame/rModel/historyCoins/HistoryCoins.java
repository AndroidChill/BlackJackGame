package com.example.blackjackgame.rModel.historyCoins;

import com.google.gson.annotations.SerializedName;

public class HistoryCoins {

    private String text;
    private int coins;
    private int money;
    private String avatar;
    private String header;
    private int type;
    private long time;
    private int id;
    @SerializedName("user_avatar")
    private String userAvatar;
    @SerializedName("user_nickname")
    private String userNick;
    @SerializedName("user_id_sender")
    private String userId;

    public HistoryCoins(String text, int coins, int money, String avatar, String header, int type, long time, int id, String userAvatar, String userNick, String userId) {
        this.text = text;
        this.coins = coins;
        this.money = money;
        this.avatar = avatar;
        this.header = header;
        this.type = type;
        this.time = time;
        this.id = id;
        this.userAvatar = userAvatar;
        this.userNick = userNick;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HistoryCoins(String text, int coins, int money, String avatar, String header, int type, long time, String userAvatar, String userNick, String userId) {
        this.text = text;
        this.coins = coins;
        this.money = money;
        this.avatar = avatar;
        this.header = header;
        this.type = type;
        this.time = time;
        this.userAvatar = userAvatar;
        this.userNick = userNick;
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
