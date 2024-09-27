package com.example.networking.models;

import com.google.gson.annotations.SerializedName;

public class DataResponse {
    @SerializedName("data")
    private UserResponse data;

    public UserResponse getData() {
        return data;
    }
}

