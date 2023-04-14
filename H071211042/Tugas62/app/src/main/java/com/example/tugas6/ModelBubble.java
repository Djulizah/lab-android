package com.example.tugas6;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ModelBubble implements Parcelable {
    private String message;
    private String time;
    private boolean isSender;

    public ModelBubble(String message, String time, boolean isSender) {
        this.message = message;
        this.time = time;
        this.isSender = isSender;
    }

    protected ModelBubble(Parcel in) {
        message = in.readString();
        time = in.readString();
        isSender = in.readByte() != 0;
    }

    public static final Creator<ModelBubble> CREATOR = new Creator<ModelBubble>() {
        @Override
        public ModelBubble createFromParcel(Parcel in) {
            return new ModelBubble(in);
        }

        @Override
        public ModelBubble[] newArray(int size) {
            return new ModelBubble[size];
        }
    };

    public String getMessage() {return message;}

    public void setMessage(String message) {this.message = message;}

    public String getTime() {return time;}

    public void setTime(String time) {this.time = time;}

    public boolean isSender() {return isSender;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(message);
        parcel.writeString(time);
        parcel.writeByte((byte) (isSender ? 1 : 0));
    }
}
