package com.example.inigaram.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {
    private int userId;
    private String fullName;
    private String userName;
    private int profilePicture;

    public User(int userId, String fullName, String userName, int profilePicture) {
        this.userId = userId;
        this.fullName = fullName;
        this.userName = userName;
        this.profilePicture = profilePicture;
    }

    protected User(Parcel in) {
        userId = in.readInt();
        fullName = in.readString();
        userName = in.readString();
        profilePicture = in.readInt();
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

    public int getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserName() {
        return userName;
    }

    public int getProfilePicture() {
        return profilePicture;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(userId);
        parcel.writeString(fullName);
        parcel.writeString(userName);
        parcel.writeInt(profilePicture);
    }
}
