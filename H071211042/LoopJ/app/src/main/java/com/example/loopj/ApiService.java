package com.example.loopj;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/users")
    Call<DataResponse> getUser(
            @Query("per_page")int page
    );

    @GET("api/users/{id}")
    Call<SingleDataResponse> getUserProfile(@Path("id") String id);
}


