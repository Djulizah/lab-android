package com.example.fragmenttask.models;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String username;
    private String fullname;
    private Uri imageUrl;

    protected User(Parcel in) {
        username = in.readString();
        fullname = in.readString();
        imageUrl = in.readParcelable(Uri.class.getClassLoader());
    }

    public User(String username, String fullname, Uri imageUrl) {
        this.username = username;
        this.fullname = fullname;
        this.imageUrl = imageUrl;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(fullname);
        dest.writeParcelable(imageUrl, flags);
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
}
