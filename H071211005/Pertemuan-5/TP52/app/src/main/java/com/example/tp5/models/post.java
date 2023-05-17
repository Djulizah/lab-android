package com.example.tp5.models;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class post implements Parcelable {
    private Uri imageUrl;
    private String caption;

    public post() {
    }

    public Uri getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Uri imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }


    protected post(Parcel in) {
        imageUrl = in.readParcelable(Uri.class.getClassLoader());
        caption = in.readString();
    }

    public static final Creator<post> CREATOR = new Creator<post>() {
        @Override
        public post createFromParcel(Parcel in) {
            return new post(in);
        }

        @Override
        public post[] newArray(int size) {
            return new post[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeParcelable(imageUrl, i);
        parcel.writeString(caption);
    }
}
