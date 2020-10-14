package com.example.blackjackgame.data;

public class Card {

    private int id;
    private int count;
    private boolean ace = false;

    public Card(int id, int count) {
        this.id = id;
        this.count = count;
    }

    public Card(int id, int count, boolean ace) {
        this.id = id;
        this.count = count;
        this.ace = ace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isAce() {
        return ace;
    }

    public void setAce(boolean ace) {
        this.ace = ace;
    }
}
