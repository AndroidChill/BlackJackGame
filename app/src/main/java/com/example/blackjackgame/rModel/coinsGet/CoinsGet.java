package com.example.blackjackgame.rModel.coinsGet;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CoinsGet implements Parcelable {

    @SerializedName("type")
    private int type;

    @SerializedName("text")
    private String text;

    @SerializedName("coins")
    private int coins;

    @SerializedName("id")
    private int id;

    public CoinsGet(int type, String text, int coins, int id) {
        this.type = type;
        this.text = text;
        this.coins = coins;
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected CoinsGet(Parcel in) {
        type = in.readInt();
        text = in.readString();
        coins = in.readInt();
        id = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type);
        dest.writeString(text);
        dest.writeInt(coins);
        dest.writeInt(id);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CoinsGet> CREATOR = new Parcelable.Creator<CoinsGet>() {
        @Override
        public CoinsGet createFromParcel(Parcel in) {
            return new CoinsGet(in);
        }

        @Override
        public CoinsGet[] newArray(int size) {
            return new CoinsGet[size];
        }
    };
}
