package com.example.tp7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {
    LinearLayout llLoading ;
    CardView cardView;
    TextView tvName, tvEmail;
    ShapeableImageView svProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        llLoading = findViewById(R.id.llloading);
        cardView = findViewById(R.id.Carditem);
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        svProfile = findViewById(R.id.ivProfil);
        String id = String.valueOf(getIntent().getIntExtra("id",0));
        loadData(id);
    }
    private void loadData (String id) {
        llLoading.setVisibility(View.VISIBLE);
        cardView.setVisibility(View.GONE);
        Call<SingleDataResponse> client = ApiConfig.getApiService().getUser(id);
        client.enqueue(new Callback<SingleDataResponse>() {
            @Override
            public void onResponse(Call<SingleDataResponse> call, Response<SingleDataResponse>
                    response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        UserResponse user = response.body().getData();
                        tvName.setText(user.getFirstName()+ " " + user.getLastName());
                        tvEmail.setText(user.getEmail());
                        Glide.with(getApplicationContext())
                                .load(user.getAvatarUrl())
                                .into(svProfile);

                        llLoading.setVisibility(View.GONE);
                        cardView.setVisibility(View.VISIBLE);

                    }
                } else {
                    if (response.body() != null) {
                        Log.e("MainActivity", "onFailure: " + response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<SingleDataResponse> call, Throwable t) {
                Log.e("MainActivity", "onFailure: " + t.getMessage());
            }
        });
    }
}