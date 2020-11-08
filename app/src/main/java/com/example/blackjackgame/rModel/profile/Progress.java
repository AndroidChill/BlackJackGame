package com.example.blackjackgame.rModel.profile;

public class Progress {

    private String icon;
    private int amount;

    public Progress(String icon, int amount) {
        this.icon = icon;
        this.amount = amount;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
