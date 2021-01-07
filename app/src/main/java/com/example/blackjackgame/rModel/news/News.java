package com.example.blackjackgame.rModel.news;

import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("type")
    private int type;

    @SerializedName("time")
    private long time;

    @SerializedName("header")
    private String header;

    @SerializedName("text")
    private String text;

    @SerializedName("url")
    private String url;

    @SerializedName("image")
    private String image;

    @SerializedName("time_game")
    private String timeGame;

    @SerializedName("user_nickname")
    private String nickname;

    @SerializedName("user_avatar")
    private String avatar;

    @SerializedName("game_tournament_id")
    private int gameTournamentId;

    @SerializedName("game_friends_id")
    private int gameFriendsId;

    @SerializedName("user_id")
    private int userId;

    public News(int type, long time, String header, String text, String url, String image, String timeGame, String nickname, String avatar, int gameTournamentId, int gameFriendsId, int userId) {
        this.type = type;
        this.time = time;
        this.header = header;
        this.text = text;
        this.url = url;
        this.image = image;
        this.timeGame = timeGame;
        this.nickname = nickname;
        this.avatar = avatar;
        this.gameTournamentId = gameTournamentId;
        this.gameFriendsId = gameFriendsId;
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
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

    public String getTimeGame() {
        return timeGame;
    }

    public void setTimeGame(String timeGame) {
        this.timeGame = timeGame;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getGameTournamentId() {
        return gameTournamentId;
    }

    public void setGameTournamentId(int gameTournamentId) {
        this.gameTournamentId = gameTournamentId;
    }

    public int getGameFriendsId() {
        return gameFriendsId;
    }

    public void setGameFriendsId(int gameFriendsId) {
        this.gameFriendsId = gameFriendsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
