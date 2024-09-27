package com.example.tugas5;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Add implements Parcelable {
    String caption;

    protected Add(Parcel in) {
        caption = in.readString();
        image = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Add> CREATOR = new Creator<Add>() {
        @Override
        public Add createFromParcel(Parcel in) {
            return new Add(in);
        }

        @Override
        public Add[] newArray(int size) {
            return new Add[size];
        }
    };

    public Add() {

    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    Uri image;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(caption);
        parcel.writeParcelable(image, i);
    }
}
