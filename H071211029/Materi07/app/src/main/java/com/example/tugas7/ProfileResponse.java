package com.example.tugas7;

import com.google.gson.annotations.SerializedName;

public class ProfileResponse {
    @SerializedName("data")
    private UserResponse data;

    public UserResponse getData(){
        return data;
    }
}
