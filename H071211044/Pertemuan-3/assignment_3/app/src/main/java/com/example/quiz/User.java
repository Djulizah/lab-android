package com.example.quiz;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String username;
    private int bestscore;
    private String Imageuri;

    public User() {
    }

    protected User(Parcel in) {
        username = in.readString();
        bestscore = in.readInt();
        Imageuri = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeInt(bestscore);
        parcel.writeString(Imageuri);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBestscore() {
        return bestscore;
    }

    public void setBestscore(int bestscore) {
        this.bestscore = bestscore;
    }

    public void setImageUri(String imageUri) {
        this.Imageuri = imageUri;
    }

    public String getImageuri() {
        return Imageuri;
    }
}

//    public void setSetImageUri(String setImageUri) {
//        this.Imageuri = setImageUri;
//    }




