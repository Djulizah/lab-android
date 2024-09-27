package com.example.tugas7;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("users")
    Call<DataResponse> getData(@Query("per_page") String per_page);

    @GET("users/{id}")
    Call<ProfileResponse> getUser(@Path("id") String id);
}
