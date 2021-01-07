package com.example.blackjackgame.rModel.coins;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CoinsBody implements Parcelable{

    private String menu;
    private String status;
    @SerializedName("status_text")
    private String statusText;
    private String token;
    @SerializedName("captcha_image_url")
    private String captchaImageUrl;
    private String popup;
    @SerializedName("coins_info")
    private CoinsInfo coinsInfo;
    @SerializedName("money_transfer")
    private List<MoneyTransfer> moneyTransfer;


    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCaptchaImageUrl() {
        return captchaImageUrl;
    }

    public void setCaptchaImageUrl(String captchaImageUrl) {
        this.captchaImageUrl = captchaImageUrl;
    }

    public String getPopup() {
        return popup;
    }

    public void setPopup(String popup) {
        this.popup = popup;
    }

    public CoinsInfo getCoinsInfo() {
        return coinsInfo;
    }

    public void setCoinsInfo(CoinsInfo coinsInfo) {
        this.coinsInfo = coinsInfo;
    }

    public CoinsBody(String menu, String status, String statusText, String token, String captchaImageUrl, String popup, CoinsInfo coinsInfo, List<MoneyTransfer> moneyTransfer) {
        this.menu = menu;
        this.status = status;
        this.statusText = statusText;
        this.token = token;
        this.captchaImageUrl = captchaImageUrl;
        this.popup = popup;
        this.coinsInfo = coinsInfo;
        this.moneyTransfer = moneyTransfer;
    }

    public List<MoneyTransfer> getMoneyTransfer() {
        return moneyTransfer;
    }

    public void setMoneyTransfer(List<MoneyTransfer> moneyTransfer) {
        this.moneyTransfer = moneyTransfer;
    }

    protected CoinsBody(Parcel in) {
        menu = in.readString();
        status = in.readString();
        statusText = in.readString();
        token = in.readString();
        captchaImageUrl = in.readString();
        popup = in.readString();
        coinsInfo = (CoinsInfo) in.readValue(CoinsInfo.class.getClassLoader());
        if (in.readByte() == 0x01) {
            moneyTransfer = new ArrayList<MoneyTransfer>();
            in.readList(moneyTransfer, MoneyTransfer.class.getClassLoader());
        } else {
            moneyTransfer = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(menu);
        dest.writeString(status);
        dest.writeString(statusText);
        dest.writeString(token);
        dest.writeString(captchaImageUrl);
        dest.writeString(popup);
        dest.writeValue(coinsInfo);
        if (moneyTransfer == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(moneyTransfer);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CoinsBody> CREATOR = new Parcelable.Creator<CoinsBody>() {
        @Override
        public CoinsBody createFromParcel(Parcel in) {
            return new CoinsBody(in);
        }

        @Override
        public CoinsBody[] newArray(int size) {
            return new CoinsBody[size];
        }
    };
}


