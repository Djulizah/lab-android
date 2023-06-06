package com.example.prak7_retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityProfile extends AppCompatActivity {
    private ShapeableImageView iv_profile;
    private TextView tv_fullName, tv_email;
    private ProgressBar progressBar;
    private CardView cardView;
    private LinearLayout reload;
    private ImageView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        iv_profile = findViewById(R.id.iv_profile);
        tv_fullName = findViewById(R.id.tvfullName);
        tv_email = findViewById(R.id.tv_email);
        progressBar = findViewById(R.id.progressBar);
        cardView = findViewById(R.id.cardView);
        reload = findViewById(R.id.reload);
        loading = findViewById(R.id.loading);

        String id = getIntent().getStringExtra("id");
        progressBar.setVisibility(View.VISIBLE);
//        loading.setVisibility(View.VISIBLE);
        reload.setVisibility(View.GONE);
        cardView.setVisibility(View.GONE);



        Call<SingleDataResponse> client = ApiConfig.getApiService().getUserById(id);
        client.enqueue(new Callback<SingleDataResponse>() {
            @Override
            public void onResponse(Call<SingleDataResponse> call, Response<SingleDataResponse>
                    response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        UserResponse userResponse = response.body().getData();
                        Glide.with(getApplicationContext()).load(userResponse.getAvatarUrl()).apply(new RequestOptions()
                                .override(350, 550)).into(iv_profile);
                        tv_fullName.setText(userResponse.getFirstName() + " " + userResponse.getLastName());
                        tv_email.setText(userResponse.getEmail());
                        progressBar.setVisibility(View.GONE);
                        cardView.setVisibility(View.VISIBLE);
                        reload.setVisibility(View.GONE);
//                        loading.setVisibility(View.GONE);

                    }
                } else {
                    if (response.body() != null) {
                        reload.setVisibility(View.VISIBLE);
                    }
                }
            }
            @Override
            public void onFailure(Call<SingleDataResponse> call, Throwable t) {
                Log.e("MainActivity", "onFailure: " + t.getMessage());
                        reload.setVisibility(View.VISIBLE);

            }
        });
    }
}