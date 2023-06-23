package com.example.assignment_7_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    UserAdapter adapter;
    List<UserResponse> userResponse;
    RecyclerView rvCards;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressbar);

        rvCards = findViewById(R.id.rv_container);
        rvCards.setLayoutManager(new LinearLayoutManager(this));
        rvCards.setHasFixedSize(true);

        Call<DataResponse> client = ApiConfig.getApiService().getUser();
        client.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        userResponse = response.body().getData();

                        adapter = new UserAdapter(userResponse);
                        rvCards.setAdapter(adapter);
                        progressBar.setVisibility(View.GONE);

                        adapter.setClickListener(new UserAdapter.ClickListener() {
                            @Override
                            public void onItemClicked(UserResponse userResponse) {
                                Intent toView = new Intent(MainActivity.this, ViewActivity.class);

                                toView.putExtra("firstName", userResponse.getFirstName());
                                toView.putExtra("lastName", userResponse.getLastName());
                                toView.putExtra("email", userResponse.getEmail());
                                toView.putExtra("avatarUrl", userResponse.getAvatarUrl());

                                startActivity(toView);
                            }
                        });
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