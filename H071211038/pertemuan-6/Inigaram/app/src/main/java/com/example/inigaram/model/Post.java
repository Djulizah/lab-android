package com.example.inigaram.model;

import android.net.Uri;

public class Post {
    private int userId;
    private Uri imageUri;
    private int imageInt;
    private String caption;

    public Post(int userId, Uri imageUri, String caption) {
        this.userId = userId;
        this.imageUri = imageUri;
        this.caption = caption;
    }

    public Post(int userId, int imageInt, String caption) {
        this.userId = userId;
        this.imageInt = imageInt;
        this.caption = caption;
    }

    public int getUserId() {
        return userId;
    }

    public int getImageInt() {
        return imageInt;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public String getCaption() {
        return caption;
    }
}
