package com.example.blackjackgame.network.responce.tournament;

public class TournamentListRequest {

    private String menu;
    private String app_ver;
    private String ln;
    private String token;
    private int tournament_type;
    private int tournament_classic;

    public TournamentListRequest(String menu, String app_ver, String ln, String token, int tournament_type, int tournament_classic) {
        this.menu = menu;
        this.app_ver = app_ver;
        this.ln = ln;
        this.token = token;
        this.tournament_type = tournament_type;
        this.tournament_classic = tournament_classic;
    }

    public int getTournament_classic() {
        return tournament_classic;
    }

    public void setTournament_classic(int tournament_classic) {
        this.tournament_classic = tournament_classic;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getApp_ver() {
        return app_ver;
    }

    public void setApp_ver(String app_ver) {
        this.app_ver = app_ver;
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

    public int getTournament_type() {
        return tournament_type;
    }

    public void setTournament_type(int tournament_type) {
        this.tournament_type = tournament_type;
    }
}
