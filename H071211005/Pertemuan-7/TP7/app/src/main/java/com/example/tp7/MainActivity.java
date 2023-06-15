package com.example.tp7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvUser;
    private AdapterRV adapterRV;
    private LinearLayout llLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvUser = findViewById(R.id.rvItem);
        adapterRV = new AdapterRV();
        llLoading = findViewById(R.id.llloading);
        loadData();
    }

    private void loadData (){
        llLoading.setVisibility(View.VISIBLE);
        rvUser.setVisibility(View.GONE);
        Call<DataResponse> client = ApiConfig.getApiService().getListUser("14");
        client.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse>
                    response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        System.out.println(response.body().getData().size());
                        adapterRV.addUser(response.body().getData());
                        rvUser.setAdapter(adapterRV);
                        rvUser.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        llLoading.setVisibility(View.GONE);
                        rvUser.setVisibility(View.VISIBLE);

                    }
                } else {
                    if (response.body() != null) {
                        Log.e("MainActivity", "onFailure: " + response.message());
                    }
                }
            }
            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Log.e("MainActivity", "onFailure: " + t.getMessage());
            }
        });
    }
}