package com.example.loopj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;

    private ProgressBar pgLoading;
    private CardView cvError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        pgLoading = findViewById(R.id.pg_loading);
        cvError = findViewById(R.id.cv_Error);

        cvError.setOnClickListener(view ->
                loadData()
                );
        loadData();
        cvError.setVisibility(View.GONE);

    }

    private void loadData(){
        Call<DataResponse> client = ApiConfig.getApiService().getUser(12);
        client.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse>
                    response) {
                pgLoading.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<UserResponse> userResponseList = response.body().getData();

                        userAdapter = new UserAdapter(userResponseList, MainActivity.this);

                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        recyclerView.setAdapter(userAdapter);
                        pgLoading.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        cvError.setVisibility(View.GONE);
                    }
                } else {
                    if (response.body() != null) {
                        showNetworkErrorInfo();
                    }
                }
            }
            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                System.out.println(t.getLocalizedMessage());
                showNetworkErrorInfo();
            }
        });
    }
    private void showNetworkErrorInfo() {
        cvError.setVisibility(View.VISIBLE);
        pgLoading.setVisibility(View.GONE);
    }
}