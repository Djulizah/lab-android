package com.example.tuprak4chat;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class RoomChat implements Parcelable {

    private String name, messageLeft, messageRight, timeLeft, timeRight;

    protected RoomChat(Parcel in) {
        name = in.readString();
        messageLeft = in.readString();
        messageRight = in.readString();
        timeLeft = in.readString();
        timeRight = in.readString();
    }

    public static final Creator<RoomChat> CREATOR = new Creator<RoomChat>() {
        @Override
        public RoomChat createFromParcel(Parcel in) {
            return new RoomChat(in);
        }

        @Override
        public RoomChat[] newArray(int size) {
            return new RoomChat[size];
        }
    };

    public RoomChat(String name, String messageLeft, String messageRight, String timeLeft, String timeRight) {
        this.name = name;
        this.messageLeft = messageLeft;
        this.messageRight = messageRight;
        this.timeLeft = timeLeft;
        this.timeRight = timeRight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(messageLeft);
        parcel.writeString(messageRight);
        parcel.writeString(timeLeft);
        parcel.writeString(timeRight);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessageLeft() {
        return messageLeft;
    }

    public void setMessageLeft(String messageLeft) {
        this.messageLeft = messageLeft;
    }

    public String getMessageRight() {
        return messageRight;
    }

    public void setMessageRight(String messageRight) {
        this.messageRight = messageRight;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }

    public String getTimeRight() {
        return timeRight;
    }

    public void setTimeRight(String timeRight) {
        this.timeRight = timeRight;
    }
}
