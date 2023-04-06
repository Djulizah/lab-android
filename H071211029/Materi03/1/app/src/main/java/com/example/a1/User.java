package com.example.a1;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {
    String fullName, username, caption, profilUri, postUri;

    protected User(Parcel in) {
        fullName = in.readString();
        username = in.readString();
        caption = in.readString();
        profilUri = in.readString();
        postUri = in.readString();
    }

    User(){

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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getProfilUri() {
        return profilUri;
    }

    public void setProfilUri(String profilUri) {
        this.profilUri = profilUri;
    }

    public String getPostUri() {
        return postUri;
    }

    public void setPostUri(String postUri) {
        this.postUri = postUri;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(fullName);
        parcel.writeString(username);
        parcel.writeString(caption);
        parcel.writeString(profilUri);
        parcel.writeString(postUri);
    }
}
