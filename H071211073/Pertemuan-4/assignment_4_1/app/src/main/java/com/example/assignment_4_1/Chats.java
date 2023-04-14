package com.example.assignment_4_1;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Chats implements Parcelable {
    String chat, time;

    protected Chats(Parcel in) {
        chat = in.readString();
        time = in.readString();
    }

    public static final Creator<Chats> CREATOR = new Creator<Chats>() {
        @Override
        public Chats createFromParcel(Parcel in) {
            return new Chats(in);
        }

        @Override
        public Chats[] newArray(int size) {
            return new Chats[size];
        }
    };

    public Chats(String chat, String time) {
        this.chat = chat;
        this.time = time;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(chat);
        parcel.writeString(time);
    }
}
