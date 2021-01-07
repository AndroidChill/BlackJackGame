package com.example.blackjackgame.rNetwork.request.moneyTransfer;

import com.google.gson.annotations.SerializedName;

public class MoneyTransferRequest {

    private String menu;
    @SerializedName("app_ver")
    private int appVer;
    private String ln;
    private String token;
    private int id;

    @SerializedName("money_transfer_bank")
    private String moneyTransferBank;
    @SerializedName("money_transfer_account")
    private String moneyTransferAccount;

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public int getAppVer() {
        return appVer;
    }

    public void setAppVer(int appVer) {
        this.appVer = appVer;
    }

    public String getLn() {
        return ln;
    }

    public void setLn(String ln) {
        this.ln = ln;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoneyTransferBank() {
        return moneyTransferBank;
    }

    public void setMoneyTransferBank(String moneyTransferBank) {
        this.moneyTransferBank = moneyTransferBank;
    }

    public String getMoneyTransferAccount() {
        return moneyTransferAccount;
    }

    public void setMoneyTransferAccount(String moneyTransferAccount) {
        this.moneyTransferAccount = moneyTransferAccount;
    }

    public MoneyTransferRequest(String menu, int appVer, String ln, String token, int id, String moneyTransferBank, String moneyTransferAccount) {
        this.menu = menu;
        this.appVer = appVer;
        this.ln = ln;
        this.token = token;
        this.id = id;
        this.moneyTransferBank = moneyTransferBank;
        this.moneyTransferAccount = moneyTransferAccount;
    }
}
