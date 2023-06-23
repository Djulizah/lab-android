package com.example.assignment_6_1;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Post implements Parcelable {
    String name, caption, imgPerson, imgPost;

    public Post(String name, String caption, String imgPerson, String imgPost) {
        this.name = name;
        this.caption = caption;
        this.imgPerson = imgPerson;
        this.imgPost = imgPost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImgPerson() {
        return imgPerson;
    }

    public void setImgPerson(String imgPerson) {
        this.imgPerson = imgPerson;
    }

    public String getImgPost() {
        return imgPost;
    }

    public void setImgPost(String imgPost) {
        this.imgPost = imgPost;
    }

    protected Post(Parcel in) {
        name = in.readString();
        caption = in.readString();
        imgPerson = in.readString();
        imgPost = in.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(caption);
        parcel.writeString(imgPerson);
        parcel.writeString(imgPost);
    }
}

