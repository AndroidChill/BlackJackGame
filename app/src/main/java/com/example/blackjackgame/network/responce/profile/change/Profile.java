package com.example.blackjackgame.network.responce.profile.change;

public class Profile {

    private String user_nickname;
    private String user_name;
    private String user_surname;
    private String user_email;
    private String user_info;
    private String user_avatar;

    public Profile(String user_nickname, String user_name, String user_surname, String user_email, String user_info, String user_avatar) {
        this.user_nickname = user_nickname;
        this.user_name = user_name;
        this.user_surname = user_surname;
        this.user_email = user_email;
        this.user_info = user_info;
        this.user_avatar = user_avatar;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_surname() {
        return user_surname;
    }

    public void setUser_surname(String user_surname) {
        this.user_surname = user_surname;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_info() {
        return user_info;
    }

    public void setUser_info(String user_info) {
        this.user_info = user_info;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }
}
