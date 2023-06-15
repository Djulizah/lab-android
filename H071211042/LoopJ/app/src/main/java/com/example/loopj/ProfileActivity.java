package com.example.loopj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    ImageView iv_foto;

    TextView tv_nama, tv_email;

    ProgressBar pgLoading;

    CardView cardView, cvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        pgLoading = findViewById(R.id.pg_loading);
        cardView = findViewById(R.id.cv_profile);
        cvError = findViewById(R.id.cv_Error);

        pgLoading.setVisibility(View.VISIBLE);
        cardView.setVisibility(View.GONE);
        cvError.setVisibility(View.GONE);

        cvError.setOnClickListener(view ->
                loadData());
        loadData();

    }

    private void loadData(){
        String id = getIntent().getStringExtra("id");
        Call<SingleDataResponse> client = ApiConfig.getApiService().getUserProfile(id);
        client.enqueue(new Callback<SingleDataResponse>() {
            @Override
            public void onResponse(Call<SingleDataResponse> call, Response<SingleDataResponse>
                    response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        UserResponse user = response.body().getData();
                        iv_foto = findViewById(R.id.iv_foto);
                        tv_nama = findViewById(R.id.tv_nama);
                        tv_email = findViewById(R.id.tv_email);

                        tv_email.setText(user.getEmail());
                        tv_nama.setText(user.getFirstName()+ " " + user.getLastName());
                        Glide.with(ProfileActivity.this)
                                .load(user.getAvatarUrl())
                                .into(iv_foto);
                        pgLoading.setVisibility(View.GONE);
                        cardView.setVisibility(View.VISIBLE);
                        cvError.setVisibility(View.GONE);
                    }
                } else {
                    if (response.body() != null) {
                        showNetworkErrorInfo();
                    }
                }
            }
            @Override
            public void onFailure(Call<SingleDataResponse> call, Throwable t) {
                Log.e("MainActivity", "onFailure: " + t.getMessage());
                showNetworkErrorInfo();
            }
        });
    }

    private void showNetworkErrorInfo() {
        cvError.setVisibility(View.VISIBLE);
        pgLoading.setVisibility(View.GONE);
    }
}