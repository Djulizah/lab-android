package com.example.tp31;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {
  private String username, fullname, caption, imageUri, postUri;

  User(){

  }

    protected User(Parcel in) {
        username = in.readString();
        fullname = in.readString();
        caption = in.readString();
        imageUri = in.readString();
        postUri = in.readString();
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

    public String getFullname() {
        return fullname;
    }

    public String getCaption() {
        return caption;
    }

    public String getImageUri() {
        return imageUri;
    }

    public String getPostUri() {
        return postUri;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
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
        parcel.writeString(username);
        parcel.writeString(fullname);
        parcel.writeString(caption);
        parcel.writeString(imageUri);
        parcel.writeString(postUri);
    }
}
