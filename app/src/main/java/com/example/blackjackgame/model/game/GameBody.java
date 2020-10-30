package com.example.blackjackgame.model.game;

public class GameBody {

    private String menu;
    private Game game;
    private long time_request_next ;
    private String status;

    public GameBody(String menu, Game game, long time_request_next, String status) {
        this.menu = menu;
        this.game = game;
        this.time_request_next = time_request_next;
        this.status = status;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public long getTime_request_next() {
        return time_request_next;
    }

    public void setTime_request_next(long time_request_next) {
        this.time_request_next = time_request_next;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
