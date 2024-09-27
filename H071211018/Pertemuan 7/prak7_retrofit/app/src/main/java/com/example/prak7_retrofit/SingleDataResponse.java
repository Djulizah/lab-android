package com.example.prak7_retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SingleDataResponse {
    @SerializedName("data")
    private UserResponse data;

    public UserResponse getData() {
        return data;
    }


}
