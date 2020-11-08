package com.example.blackjackgame.rModel.profile;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Profile implements Parcelable {

    @SerializedName("user_id")
    private String id;

    @SerializedName("user_coins")
    private int coins;

    @SerializedName("user_nickname")
    private String nickname;

    @SerializedName("user_name")
    private String name;

    @SerializedName("user_surname")
    private String surname;

    @SerializedName("user_email")
    private String email;

    @SerializedName("user_info")
    private String info;

    @SerializedName("user_rating")
    private int rating;

    @SerializedName("user_rating_position")
    private int ratingPosition;

    @SerializedName("user_avatar")
    private String avatar;

    @SerializedName("ref")
    private List<Ref> refs;

    @SerializedName("ref_faq")
    private String refFaq;

    @SerializedName("progress")
    private List<Progress> progresses;

    public Profile(String id, int coins, String nickname, String name, String surname, String email, String info, int rating, int ratingPosition, String avatar, List<Ref> refs, String refFaq, List<Progress> progresses) {
        this.id = id;
        this.coins = coins;
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.info = info;
        this.rating = rating;
        this.ratingPosition = ratingPosition;
        this.avatar = avatar;
        this.refs = refs;
        this.refFaq = refFaq;
        this.progresses = progresses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRatingPosition() {
        return ratingPosition;
    }

    public void setRatingPosition(int ratingPosition) {
        this.ratingPosition = ratingPosition;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Ref> getRefs() {
        return refs;
    }

    public void setRefs(List<Ref> refs) {
        this.refs = refs;
    }

    public String getRefFaq() {
        return refFaq;
    }

    public void setRefFaq(String refFaq) {
        this.refFaq = refFaq;
    }

    public List<Progress> getProgresses() {
        return progresses;
    }

    public void setProgresses(List<Progress> progresses) {
        this.progresses = progresses;
    }

    protected Profile(Parcel in) {
        id = in.readString();
        coins = in.readInt();
        nickname = in.readString();
        name = in.readString();
        surname = in.readString();
        email = in.readString();
        info = in.readString();
        rating = in.readInt();
        ratingPosition = in.readInt();
        avatar = in.readString();
        if (in.readByte() == 0x01) {
            refs = new ArrayList<Ref>();
            in.readList(refs, Ref.class.getClassLoader());
        } else {
            refs = null;
        }
        refFaq = in.readString();
        if (in.readByte() == 0x01) {
            progresses = new ArrayList<Progress>();
            in.readList(progresses, Progress.class.getClassLoader());
        } else {
            progresses = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(coins);
        dest.writeString(nickname);
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeString(email);
        dest.writeString(info);
        dest.writeInt(rating);
        dest.writeInt(ratingPosition);
        dest.writeString(avatar);
        if (refs == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(refs);
        }
        dest.writeString(refFaq);
        if (progresses == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(progresses);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Profile> CREATOR = new Parcelable.Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };
}
