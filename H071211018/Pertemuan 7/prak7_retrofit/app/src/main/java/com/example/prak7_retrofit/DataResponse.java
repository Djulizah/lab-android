package com.example.prak7_retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataResponse {

    @SerializedName("data")
    private ArrayList<UserResponse> data;

    public ArrayList<UserResponse> getData() {
        return data;
    }
//    private UserResponse data;
//    public UserResponse getData() {
//        return data;
//    }
}

