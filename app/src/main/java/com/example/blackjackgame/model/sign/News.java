package com.example.blackjackgame.model.sign;

import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("type")
    private Integer type;

    @SerializedName("time")
    private Long time;

    @SerializedName("header")
    private String header;

    @SerializedName("text")
    private String text;

    @SerializedName("url")
    private String url;

    @SerializedName("image")
    private String image;

    @SerializedName("time_game")
    private Long time_game;

    @SerializedName("user_nickname")
    private String user_nickname;

    @SerializedName("user_avatar")
    private String user_avatar;

    @SerializedName("game_tournament_id")
    private String game_tournament_id;

    @SerializedName("game_friends_id")
    private String game_friends_id;

    public News(){}

    public News(Integer type, Long time, String header, String text, String url, String image, Long time_game, String user_nickname, String user_avatar, String game_tournament_id, String game_friends_id) {
        this.type = type;
        this.time = time;
        this.header = header;
        this.text = text;
        this.url = url;
        this.image = image;
        this.time_game = time_game;
        this.user_nickname = user_nickname;
        this.user_avatar = user_avatar;
        this.game_tournament_id = game_tournament_id;
        this.game_friends_id = game_friends_id;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getTime_game() {
        return time_game;
    }

    public void setTime_game(Long time_game) {
        this.time_game = time_game;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String getGame_tournament_id() {
        return game_tournament_id;
    }

    public void setGame_tournament_id(String game_tournament_id) {
        this.game_tournament_id = game_tournament_id;
    }

    public String getGame_friends_id() {
        return game_friends_id;
    }

    public void setGame_friends_id(String game_friends_id) {
        this.game_friends_id = game_friends_id;
    }


}
