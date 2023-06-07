package com.example.tp7;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/users")
    Call<DataResponse> getListUser(@Query("per_page") String page);

    @GET("api/users/{id}")
    Call<SingleDataResponse> getUser(@Path("id") String id);


}
