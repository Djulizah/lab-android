package com.example.tugas4;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class User {
    private int id;
    private String name;
    private String phone;
    private String status;
    private String dateJoined;
    private int profilePicture;

    public User(int id, String name, String phone, String status, String dateJoined, int profilePicture) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.dateJoined = dateJoined;
        this.profilePicture = profilePicture;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getStatus() {
        return status;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public int getProfilePicture() {
        return profilePicture;
    }
}
