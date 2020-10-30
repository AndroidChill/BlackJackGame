package com.example.blackjackgame.model.statics;

import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("comment_star")
    private String comment_star;

    @SerializedName("comment_text")
    private String comment_text;

    @SerializedName("comment_status")
    private String comment_status;

    public Review(String comment_star, String comment_text, String comment_status) {
        this.comment_star = comment_star;
        this.comment_text = comment_text;
        this.comment_status = comment_status;
    }

    public String getComment_star() {
        return comment_star;
    }

    public void setComment_star(String comment_star) {
        this.comment_star = comment_star;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public String getComment_status() {
        return comment_status;
    }

    public void setComment_status(String comment_status) {
        this.comment_status = comment_status;
    }
}
