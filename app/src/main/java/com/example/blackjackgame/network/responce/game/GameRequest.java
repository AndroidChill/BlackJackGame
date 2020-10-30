package com.example.blackjackgame.network.responce.game;

public class GameRequest {

    private String menu;

    public GameRequest(String menu) {
        this.menu = menu;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
}
