package com.example.prak7_retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("users")
    Call<DataResponse> getUser(@Query("per_page") String per_page);

    @POST("users")
    Call<UserJob> postUser(@Body UserJob userJob);

    @GET("users/{id}")
    Call<SingleDataResponse> getUserById(@Path("id") String id);
}

