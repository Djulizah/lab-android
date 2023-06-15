package com.example.tuprak7networking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {

    private TextView tvNama, tvEmail;
    private ShapeableImageView ivProfile;
    private CardView card_view;
    private ProgressBar progressBar;
    private LinearLayout ll_reload;
    private ImageView iv_loading;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvNama = findViewById(R.id.tv_nama);
        tvEmail = findViewById(R.id.tv_email);
        ivProfile = findViewById(R.id.iv_profile);
        card_view= findViewById(R.id.cardView);
        progressBar = findViewById(R.id.progressBar);
        ll_reload = findViewById(R.id.reload);
        iv_loading = findViewById(R.id.loading);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        progressBar.setVisibility(View.VISIBLE);
        ll_reload.setVisibility(View.GONE);
        card_view.setVisibility(View.GONE);

        fetchAPI(id);
        iv_loading.setOnClickListener(view -> {
            fetchAPI(id);

        });


    }

    private void fetchAPI(String id) {
        if (NetworkUtil.isNetworkAvailable(this)) {
            APIConfig.getApiService().getUserById(id).enqueue(new Callback<ProfileResponse>() {
                @Override
                public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        System.out.println(response.body().getUser());

                        UserModel user = response.body().getUser();
                        tvNama.setText(user.getFirst_name() + " " + user.getLast_name());
                        tvEmail.setText(user.getEmail());
                        Glide.with(getApplicationContext()). load(user.getProfileImage()).into(ivProfile);

                        progressBar.setVisibility(View.GONE);
                        card_view.setVisibility(View.VISIBLE);
                        ll_reload.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<ProfileResponse> call, Throwable t) {

                }
            });
        }
        else {
            ll_reload.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            card_view.setVisibility(View.GONE);
        }

    }
}