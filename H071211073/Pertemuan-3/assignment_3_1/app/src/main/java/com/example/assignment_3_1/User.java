package com.example.assignment_3_1;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {
    String fullname, username, imguri, caption, posturi;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImguri() {
        return imguri;
    }

    public void setImguri(String imguri) {
        this.imguri = imguri;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPosturi() {
        return posturi;
    }

    public void setPosturi(String posturi) {
        this.posturi = posturi;
    }

    //CONSTRUCTOR
    public User() {
    }

    protected User(Parcel in) {
        fullname = in.readString();
        username = in.readString();
        imguri = in.readString();
        caption = in.readString();
        posturi = in.readString();
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
        parcel.writeString(fullname);
        parcel.writeString(username);
        parcel.writeString(imguri);
        parcel.writeString(caption);
        parcel.writeString(posturi);
    }
}
