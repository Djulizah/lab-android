package com.example.assignment_8_1;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class NoteModel implements Parcelable {
    int id;
    String title, createdAt, description;

    public NoteModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public NoteModel(int id, String title, String createdAt, String description) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.description = description;
    }

    public NoteModel() {
    }

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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected NoteModel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        createdAt = in.readString();
        description = in.readString();
    }

    public static final Creator<NoteModel> CREATOR = new Creator<NoteModel>() {
        @Override
        public NoteModel createFromParcel(Parcel in) {
            return new NoteModel(in);
        }

        @Override
        public NoteModel[] newArray(int size) {
            return new NoteModel[size];
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
        parcel.writeString(createdAt);
        parcel.writeString(description);
    }
}

