package com.example.blackjackgame.rNetwork.request.sign;

public class SignInEmailStartRequest {

    private String menu;
    private int appVer;
    private String ln;
    private String token;

    public SignInEmailStartRequest(String menu, int appVer, String ln, String token) {
        this.menu = menu;
        this.appVer = appVer;
        this.ln = ln;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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

}
