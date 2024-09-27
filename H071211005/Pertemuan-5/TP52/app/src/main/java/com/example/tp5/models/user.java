package com.example.tp5.models;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class user implements Parcelable {
    private String username;
    private String fullname;
    private Uri imageUrl;

    public user(String username, String fullname, Uri imageUrl) {
        this.username = username;
        this.fullname = fullname;
        this.imageUrl = imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Uri getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Uri imageUrl) {
        this.imageUrl = imageUrl;
    }

    protected user(Parcel in) {
        username = in.readString();
        fullname = in.readString();
        imageUrl = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<user> CREATOR = new Creator<user>() {
        @Override
        public user createFromParcel(Parcel in) {
            return new user(in);
        }

        @Override
        public user[] newArray(int size) {
            return new user[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeString(fullname);
        parcel.writeParcelable(imageUrl, i);
    }
}
