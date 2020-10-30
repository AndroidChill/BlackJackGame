package com.example.blackjackgame.model.game;

import java.util.List;

public class Game {

    private List<User> users;
    private int coins;
    private int users_positon;
    private int users_all;

    public Game(List<User> users, int coins, int users_positon, int users_all) {
        this.users = users;
        this.coins = coins;
        this.users_positon = users_positon;
        this.users_all = users_all;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getUsers_positon() {
        return users_positon;
    }

    public void setUsers_positon(int users_positon) {
        this.users_positon = users_positon;
    }

    public int getUsers_all() {
        return users_all;
    }

    public void setUsers_all(int users_all) {
        this.users_all = users_all;
    }
}
