package com.example.blackjackgame.rModel.profileAvatar;

import com.google.gson.annotations.SerializedName;

public class Avatar {

    @SerializedName("image")
    private String image;

    @SerializedName("coast")
    private int coast;

    public Avatar(String image, int coast) {
        this.image = image;
        this.coast = coast;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCoast() {
        return coast;
    }

    public void setCoast(int coast) {
        this.coast = coast;
    }
}
