package com.example.blackjackgame.rModel.profile;

import com.google.gson.annotations.SerializedName;

public class Ref {

    @SerializedName("ref_url")
    private String url;
    @SerializedName("ref_text")
    private String text;

    public Ref(String url, String text) {
        this.url = url;
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
