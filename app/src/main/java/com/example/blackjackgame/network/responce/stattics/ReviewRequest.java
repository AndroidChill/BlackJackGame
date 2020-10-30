package com.example.blackjackgame.network.responce.stattics;

import com.example.blackjackgame.model.statics.Review;
import com.google.gson.annotations.SerializedName;

public class ReviewRequest {

    @SerializedName("menu")
    private String menu;

    @SerializedName("review")
    private Review review;

    public ReviewRequest(String menu, Review review) {
        this.menu = menu;
        this.review = review;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

}
