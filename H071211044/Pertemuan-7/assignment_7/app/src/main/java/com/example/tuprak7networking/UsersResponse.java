package com.example.tuprak7networking;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsersResponse {

    @SerializedName("data")
    private List<UserModel> user;


    public List<UserModel> getUser() {
        return user;
    }
}
