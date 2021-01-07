package com.example.blackjackgame.rModel;


import android.os.Parcel;
import android.os.Parcelable;

public class Avatar implements Parcelable {

    private String image;
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


    protected Avatar(Parcel in) {
        image = in.readString();
        coast = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
        dest.writeInt(coast);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Avatar> CREATOR = new Parcelable.Creator<Avatar>() {
        @Override
        public Avatar createFromParcel(Parcel in) {
            return new Avatar(in);
        }

        @Override
        public Avatar[] newArray(int size) {
            return new Avatar[size];
        }
    };

}
