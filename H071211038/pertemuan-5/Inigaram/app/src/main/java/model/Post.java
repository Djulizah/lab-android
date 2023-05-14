package model;

import android.net.Uri;

public class Post {
    private Uri imageUri;
    private String caption;

    public Post(Uri imageUri, String caption) {
        this.imageUri = imageUri;
        this.caption = caption;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public String getCaption() {
        return caption;
    }

}
