package com.example.blackjackgame.network.responce.sign;

public class SignInEmailStartRequest {

    private String menu;
    private String appVer;
    private String ln;

    public SignInEmailStartRequest(String menu, String appVer, String ln) {
        this.menu = menu;
        this.appVer = appVer;
        this.ln = ln;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getAppVer() {
        return appVer;
    }

    public void setAppVer(String appVer) {
        this.appVer = appVer;
    }

    public String getLn() {
        return ln;
    }

    public void setLn(String ln) {
        this.ln = ln;
    }
}
