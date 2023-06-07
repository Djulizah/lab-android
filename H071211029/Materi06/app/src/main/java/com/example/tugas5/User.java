package com.example.tugas5;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {
    String fullName;
    int image;
    int profilImage;

    protected User(Parcel in) {
        fullName = in.readString();
        image = in.readInt();
        profilImage = in.readInt();
        username = in.readString();
        add = in.readParcelable(Add.class.getClassLoader());
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

    public User(String fullName, int image, int profilImage, String username) {
        this.fullName = fullName;
        this.image = image;
        this.profilImage = profilImage;
        this.username = username;
    }

    public User(String fullName, int profilImage, String username, Add add) {
        this.fullName = fullName;
        this.profilImage = profilImage;
        this.username = username;
        this.add = add;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Add getAdd() {
        return add;
    }

    public void setAdd(Add add) {
        this.add = add;
    }

    String username;
    Add add;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

        parcel.writeString(fullName);
        parcel.writeInt(image);
        parcel.writeInt(profilImage);
        parcel.writeString(username);
        parcel.writeParcelable(add, i);
    }

    public int getProfilImage() {
        return profilImage;
    }

    public void setProfilImage(int profilImage) {
        this.profilImage = profilImage;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
