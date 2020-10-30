package com.example.blackjackgame.model.statics;

import com.google.gson.annotations.SerializedName;

public class ReviewBody {

    @SerializedName("status")
    private String status;

    public ReviewBody(String success) {
        this.status = success;
    }

    public String getSuccess() {
        return status;
    }

    public void setSuccess(String success) {
        this.status = success;
    }
}
