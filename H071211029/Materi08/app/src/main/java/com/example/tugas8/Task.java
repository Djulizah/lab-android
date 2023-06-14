package com.example.tugas8;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Task implements Parcelable {
    int id;
    String title, desc;
    public Task(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Task(int id, String title, String desc){
        this.id = id;
        this.title = title;
        this.desc = desc;
    }
    protected Task(Parcel in) {
        id = in.readInt();
        title = in.readString();
        desc = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(desc);
    }
}
