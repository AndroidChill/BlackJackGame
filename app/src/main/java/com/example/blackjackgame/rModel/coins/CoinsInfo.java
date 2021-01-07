package com.example.blackjackgame.rModel.coins;

public class CoinsInfo {

    private String header;
    private int coins;
    private int money;
    private int id;

    public CoinsInfo(String header, int coins, int money, int id) {
        this.header = header;
        this.coins = coins;
        this.money = money;
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
