package com.example.blackjackgame.rModel.startGame;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GameSettings {

    @SerializedName("coins")
    private List<Integer> coins;

    @SerializedName("game_friends")
    private int gameFriends;

    @SerializedName("game_friends_text")
    private String gameFriendsText;

    public GameSettings(List<Integer> coins, int gameFriends, String gameFriendsText) {
        this.coins = coins;
        this.gameFriends = gameFriends;
        this.gameFriendsText = gameFriendsText;
    }

    public List<Integer> getCoins() {
        return coins;
    }

    public void setCoins(List<Integer> coins) {
        this.coins = coins;
    }

    public int getGameFriends() {
        return gameFriends;
    }

    public void setGameFriends(int gameFriends) {
        this.gameFriends = gameFriends;
    }

    public String getGameFriendsText() {
        return gameFriendsText;
    }

    public void setGameFriendsText(String gameFriendsText) {
        this.gameFriendsText = gameFriendsText;
    }
}
