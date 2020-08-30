package com.example.blackjackgame.network.responce.sign;

public class SignGuestRequest {

    private String menu;

    public SignGuestRequest(String menu){
        this.menu = menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getMenu() {
        return menu;
    }

}
