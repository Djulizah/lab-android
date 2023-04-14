package com.example.tugas3_1;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {
    private String profileImg;
    private String fullname;
    private String username;
    private String postImg;
    private String postCaption;

    public User(String profileImg, String fullname, String username, String postImg, String postCaption) {
        this.profileImg = profileImg;
        this.fullname = fullname;
        this.username = username;
        this.postImg = postImg;
        this.postCaption = postCaption;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getPostImg() {
        return postImg;
    }

    public String getPostCaption() {
        return postCaption;
    }

    protected User(Parcel in) {
        profileImg = in.readString();
        fullname = in.readString();
        username = in.readString();
        postImg = in.readString();
        postCaption = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(profileImg);
        parcel.writeString(fullname);
        parcel.writeString(username);
        parcel.writeString(postImg);
        parcel.writeString(postCaption);
    }
}
