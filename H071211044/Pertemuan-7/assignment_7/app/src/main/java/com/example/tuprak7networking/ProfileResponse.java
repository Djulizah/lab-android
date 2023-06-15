package com.example.tuprak7networking;

import com.google.gson.annotations.SerializedName;

public class ProfileResponse {

    @SerializedName("data")
    private UserModel user;

    public UserModel getUser() {
        return user;
    }
}
