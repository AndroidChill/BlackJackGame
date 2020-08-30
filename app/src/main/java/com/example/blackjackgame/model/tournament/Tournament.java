package com.example.blackjackgame.model.tournament;

import com.google.gson.annotations.SerializedName;

public class Tournament {

    @SerializedName("id")
    private int id;

    @SerializedName("type")
    private int type;

    @SerializedName("classic")
    private int classic;

    @SerializedName("coins_bet")
    private int coins_bet;

    @SerializedName("coins_prize")
    private int coins_prize;

    @SerializedName("money")
    private int money;

    @SerializedName("money_type")
    private String money_type;

    @SerializedName("time_start")
    private long time_start;

    @SerializedName("limit_time")
    private long limit_time;

    @SerializedName("users_now")
    private int users_now;

    @SerializedName("users_max")
    private int users_max;

    @SerializedName("users_count_win")
    private int users_count_win;

    @SerializedName("name")
    private String name;

    @SerializedName("join")
    private int join;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getClassic() {
        return classic;
    }

    public void setClassic(int classic) {
        this.classic = classic;
    }

    public int getCoins_bet() {
        return coins_bet;
    }

    public void setCoins_bet(int coins_bet) {
        this.coins_bet = coins_bet;
    }

    public int getCoins_prize() {
        return coins_prize;
    }

    public void setCoins_prize(int coins_prize) {
        this.coins_prize = coins_prize;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getMoney_type() {
        return money_type;
    }

    public void setMoney_type(String money_type) {
        this.money_type = money_type;
    }

    public long getTime_start() {
        return time_start;
    }

    public void setTime_start(long time_start) {
        this.time_start = time_start;
    }

    public long getLimit_time() {
        return limit_time;
    }

    public void setLimit_time(long limit_time) {
        this.limit_time = limit_time;
    }

    public int getUsers_now() {
        return users_now;
    }

    public void setUsers_now(int users_now) {
        this.users_now = users_now;
    }

    public int getUsers_max() {
        return users_max;
    }

    public void setUsers_max(int users_max) {
        this.users_max = users_max;
    }

    public int getUsers_count_win() {
        return users_count_win;
    }

    public void setUsers_count_win(int users_count_win) {
        this.users_count_win = users_count_win;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getJoin() {
        return join;
    }

    public void setJoin(int join) {
        this.join = join;
    }

    public Tournament(int id, int type, int classic, int coins_bet, int coins_prize, int money, String money_type, long time_start, long limit_time, int users_now, int users_max, int users_count_win, String name, int join) {
        this.id = id;
        this.type = type;
        this.classic = classic;
        this.coins_bet = coins_bet;
        this.coins_prize = coins_prize;
        this.money = money;
        this.money_type = money_type;
        this.time_start = time_start;
        this.limit_time = limit_time;
        this.users_now = users_now;
        this.users_max = users_max;
        this.users_count_win = users_count_win;
        this.name = name;
        this.join = join;
    }
}
