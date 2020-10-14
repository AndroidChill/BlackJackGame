package com.example.blackjackgame.model.tournament;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TournamentBody {

    @SerializedName("menu")
    private String menu;

    @SerializedName("status")
    private String status;

    @SerializedName("toast")
    private String toast;

    @SerializedName("tournament_menu")
    private List<Tournament> tournament_menu = new ArrayList<>();

    @SerializedName("time_server")
    private long time_server;

    public TournamentBody(String menu, String status, String toast, List<Tournament> tournament_menu, long time_server) {
        this.menu = menu;
        this.status = status;
        this.toast = toast;
        this.tournament_menu = tournament_menu;
        this.time_server = time_server;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToast() {
        return toast;
    }

    public void setToast(String toast) {
        this.toast = toast;
    }

    public List<Tournament> getTournament_menu() {
        return tournament_menu;
    }

    public void setTournament_menu(List<Tournament> tournament_menu) {
        this.tournament_menu = tournament_menu;
    }

    public long getTime_server() {
        return time_server;
    }

    public void setTime_server(long time_server) {
        this.time_server = time_server;
    }
}
