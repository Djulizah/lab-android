package com.example.tp4;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Room implements Parcelable {
    private String tvMess;
    private String tvDate;
    private String tvmess2;
    private String tvDate2;
    private String tvName;


    protected Room(Parcel in) {
        tvMess = in.readString();
        tvDate = in.readString();
        tvmess2 = in.readString();
        tvDate2 = in.readString();
        tvName = in.readString();
    }

    public Room(String tvMess, String tvDate, String tvmess2, String tvDate2, String tvName) {
        this.tvMess = tvMess;
        this.tvDate = tvDate;
        this.tvmess2 = tvmess2;
        this.tvDate2 = tvDate2;
        this.tvName = tvName;
    }

    public String getTvMess() {
        return tvMess;
    }

    public void setTvMess(String tvMess) {
        this.tvMess = tvMess;
    }

    public String getTvDate() {
        return tvDate;
    }

    public void setTvDate(String tvDate) {
        this.tvDate = tvDate;
    }

    public String getTvmess2() {
        return tvmess2;
    }

    public void setTvmess2(String tvmess2) {
        this.tvmess2 = tvmess2;
    }

    public String getTvDate2() {
        return tvDate2;
    }

    public void setTvDate2(String tvDate2) {
        this.tvDate2 = tvDate2;
    }

    public String getTvName() {
        return tvName;
    }

    public void setTvName(String tvName) {
        this.tvName = tvName;
    }

    public static final Creator<Room> CREATOR = new Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel in) {
            return new Room(in);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(tvMess);
        parcel.writeString(tvDate);
        parcel.writeString(tvmess2);
        parcel.writeString(tvDate2);
        parcel.writeString(tvName);
    }
}
