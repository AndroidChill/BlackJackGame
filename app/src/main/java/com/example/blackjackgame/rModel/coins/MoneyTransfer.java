package com.example.blackjackgame.rModel.coins;

public class MoneyTransfer {

    private String bank;
    private String info;

    public MoneyTransfer(String bank, String info) {
        this.bank = bank;
        this.info = info;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
