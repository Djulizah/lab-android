package com.example.assignment_4_1;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Contacts implements Parcelable {
    String name, number, status, statusTime;
    int img;
    ArrayList<Chats> chats;

    public Contacts(String name, String number, String status, String statusTime, int img, ArrayList<Chats> chats) {
        this.name = name;
        this.number = number;
        this.status = status;
        this.statusTime = statusTime;
        this.img = img;
        this.chats = chats;
    }


    public Contacts(String name, String number, String status, String statusTime, int img) {
        this.name = name;
        this.number = number;
        this.status = status;
        this.statusTime = statusTime;
        this.img = img;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(String statusTime) {
        this.statusTime = statusTime;
    }

    public Contacts(String name, int img, ArrayList<Chats> chats) {
        this.name = name;
        this.img = img;
        this.chats = chats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public ArrayList<Chats> getChats() {
        return chats;
    }

    public void setChats(ArrayList<Chats> chats) {
        this.chats = chats;
    }

    protected Contacts(Parcel in) {
        name = in.readString();
        img = in.readInt();
        chats = in.createTypedArrayList(Chats.CREATOR);
        number = in.readString();
        status = in.readString();
        statusTime = in.readString();
    }

    public static final Creator<Contacts> CREATOR = new Creator<Contacts>() {
        @Override
        public Contacts createFromParcel(Parcel in) {
            return new Contacts(in);
        }

        @Override
        public Contacts[] newArray(int size) {
            return new Contacts[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(img);
        parcel.writeTypedList(chats);
        parcel.writeString(number);
        parcel.writeString(status);
        parcel.writeString(statusTime);
    }
}
