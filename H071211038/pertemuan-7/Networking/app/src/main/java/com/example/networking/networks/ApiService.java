package com.example.networking.networks;

import com.example.networking.models.DataListResponse;
import com.example.networking.models.DataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/users")
    Call<DataListResponse> getUser(@Query("per_page") String itemsPerPage);

    @GET("api/users/{id}")
    Call<DataListResponse> getUserId(@Path("id") int id);

    @GET("api/users/{id}")
    Call<DataResponse> getUserById(@Path("id") int id);
}
