package com.example.blackjackgame.model.profile;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Profile {

    @SerializedName("user_id")
    private long user_id;

    @SerializedName("user_coins")
    private long user_coins;

    @SerializedName("user_nickname")
    private  String user_nickname;

    @SerializedName("user_name")
    private String user_name;

    @SerializedName("user_surname")
    private String user_surname;

    @SerializedName("user_email")
    private String user_email;

    @SerializedName("user_info")
    private String user_info;

    @SerializedName("user_rating")
    private long user_rating;

    @SerializedName("user_rating_position")
    private long user_rating_position;

    @SerializedName("user_avatar")
    private String user_avatar;

    @SerializedName("ref")
    private List<Ref> ref;

    @SerializedName("progress")
    private List<Progress> progress = new ArrayList<>();

    public Profile(){
        this.user_id = 0;
        this.user_coins = 0;
        this.user_nickname = "";
        this.user_name = "";
        this.user_surname = "";
        this.user_email = "";
        this.user_info = "";
        this.user_rating = 0;
        this.user_rating_position = 0;
        this.user_avatar = "";
        this.ref = new ArrayList<>();
        this.progress = null;
    }

    public Profile(long user_id, long user_coins, String user_nickname, String user_name, String user_surname, String user_email, String user_info, long user_rating, long user_rating_position, String user_avatar, List<Ref> ref, List<Progress> progress) {
        this.user_id = user_id;
        this.user_coins = user_coins;
        this.user_nickname = user_nickname;
        this.user_name = user_name;
        this.user_surname = user_surname;
        this.user_email = user_email;
        this.user_info = user_info;
        this.user_rating = user_rating;
        this.user_rating_position = user_rating_position;
        this.user_avatar = user_avatar;
        this.ref = ref;
        this.progress = progress;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getUser_coins() {
        return user_coins;
    }

    public void setUser_coins(long user_coins) {
        this.user_coins = user_coins;
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

    public long getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(long user_rating) {
        this.user_rating = user_rating;
    }

    public long getUser_rating_position() {
        return user_rating_position;
    }

    public void setUser_rating_position(long user_rating_position) {
        this.user_rating_position = user_rating_position;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public List<Ref> getRef() {
        return ref;
    }

    public void setRef_url(List<Ref> ref) {
        this.ref = ref;
    }

    public List<Progress> getProgress() {
        return progress;
    }

    public void setProgress(List<Progress> progress) {
        this.progress = progress;
    }
}
