package com.example.tp4;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

class Chat implements Parcelable {
    public Chat(String tvName, String tvTime, int im1, String tvNumber, String tvStatus, String tvDate) {
        this.tvName = tvName;
        this.tvTime = tvTime;
        this.im1 = im1;
        this.tvNumber = tvNumber;
        this.tvStatus = tvStatus;
        this.tvDate = tvDate;
        this.listMsg = new ArrayList<>();
    }

    public void addChat(Room roomChat){
        listMsg.add(roomChat);
    }

    private String tvTime;
    private int im1;
    private String tvNumber;
    private String tvStatus;
    private String tvDate;
    private ArrayList<Room> listMsg;
    private String tvName;
    public String getTvName() {
        return tvName;
    }

    public void setTvName(String tvName) {
        this.tvName = tvName;
    }

    public String getTvTime() {
        return tvTime;
    }

    public void setTvTime(String tvTime) {
        this.tvTime = tvTime;
    }

    public int getIm1() {
        return im1;
    }

    public void setIm1(int im1) {
        this.im1 = im1;
    }

    public String getTvNumber() {
        return tvNumber;
    }

    public void setTvNumber(String tvNumber) {
        this.tvNumber = tvNumber;
    }

    public String getTvStatus() {
        return tvStatus;
    }

    public void setTvStatus(String tvStatus) {
        this.tvStatus = tvStatus;
    }

    public String getTvDate() {
        return tvDate;
    }

    public void setTvDate(String tvDate) {
        this.tvDate = tvDate;
    }

    public ArrayList<Room> getListMsg() {
        return listMsg;
    }

    public void setListMsg(ArrayList<Room> listMsg) {
        this.listMsg = listMsg;
    }

    protected Chat(Parcel in) {
        tvName = in.readString();
        tvTime = in.readString();
        im1 = in.readInt();
        tvNumber = in.readString();
        tvStatus = in.readString();
        tvDate = in.readString();
        listMsg = in.createTypedArrayList(Room.CREATOR);
    }

    public static final Creator<Chat> CREATOR = new Creator<Chat>() {
        @Override
        public Chat createFromParcel(Parcel in) {
            return new Chat(in);
        }

        @Override
        public Chat[] newArray(int size) {
            return new Chat[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(tvName);
        parcel.writeString(tvTime);
        parcel.writeInt(im1);
        parcel.writeString(tvNumber);
        parcel.writeString(tvStatus);
        parcel.writeString(tvDate);
        parcel.writeTypedList(listMsg);
    }
}
