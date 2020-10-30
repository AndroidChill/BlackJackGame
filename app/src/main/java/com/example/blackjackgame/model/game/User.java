package com.example.blackjackgame.model.game;

public class User {

    private String name;
    private String avatar;
    private int points;
    private int coins;
    private int coins_get;
    private int coins_set;
    private int status;
    private int[] cards = new int[3];
    private int card_new;

    public User(String name, String avatar, int points, int coins, int coins_get, int coins_set, int status, int[] cards, int card_new) {
        this.name = name;
        this.avatar = avatar;
        this.points = points;
        this.coins = coins;
        this.coins_get = coins_get;
        this.coins_set = coins_set;
        this.status = status;
        this.cards = cards;
        this.card_new = card_new;
    }

    public int getCard_new() {
        return card_new;
    }

    public void setCard_new(int card_new) {
        this.card_new = card_new;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getCoins_get() {
        return coins_get;
    }

    public void setCoins_get(int coins_get) {
        this.coins_get = coins_get;
    }

    public int getCoins_set() {
        return coins_set;
    }

    public void setCoins_set(int coins_set) {
        this.coins_set = coins_set;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int[] getCards() {
        return cards;
    }

    public void setCards(int[] cards) {
        this.cards = cards;
    }
}
