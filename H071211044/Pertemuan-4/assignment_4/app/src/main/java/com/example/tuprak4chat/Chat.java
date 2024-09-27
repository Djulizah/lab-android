package com.example.tuprak4chat;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Chat implements Parcelable {
    private String name, time, phone, status, date;
    private int photo;
    private ArrayList<RoomChat> listChat;

    protected Chat(Parcel in) {
        name = in.readString();
        time = in.readString();
        phone = in.readString();
        status = in.readString();
        date = in.readString();
        photo = in.readInt();
        listChat = in.createTypedArrayList(RoomChat.CREATOR);
    }

    public Chat(String name, String time, String phone, String status, String date, int photo) {
        this.name = name;
        this.time = time;
        this.phone = phone;
        this.status = status;
        this.date = date;
        this.photo = photo;
        this.listChat = new ArrayList<>();
    }

    public void addChat(RoomChat roomChat){
        listChat.add(roomChat);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(time);
        dest.writeString(phone);
        dest.writeString(status);
        dest.writeString(date);
        dest.writeInt(photo);
        dest.writeTypedList(listChat);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public ArrayList<RoomChat> getListChat() {
        return listChat;
    }

    public void setListChat(ArrayList<RoomChat> listChat) {
        this.listChat = listChat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}