package com.example.tuprak7networking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvUser;
    private AdapterUser adapter_user;
    private LinearLayout ll_reload;
    private ImageView iv_loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvUser = findViewById(R.id.rv_user);
        ll_reload = findViewById(R.id.reload);
        iv_loading = findViewById(R.id.loading);
        adapter_user = new AdapterUser();

        ll_reload.setVisibility(View.GONE);

        fetchAPI();
        iv_loading.setOnClickListener(view -> {
            fetchAPI();
        });
    }

    private void fetchAPI() {
        if (NetworkUtil.isNetworkAvailable(this)) {
            APIConfig.getApiService().getUsers("12").enqueue(new Callback<UsersResponse>() {
                @Override
                public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {

                        ll_reload.setVisibility(View.GONE);
                        adapter_user.addUser(response.body().getUser());
                        rvUser.setAdapter(adapter_user);
                        rvUser.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        Log.d("users", response.body().toString());
                    }
                }

                @Override
                public void onFailure(Call<UsersResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "OnFailure" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            ll_reload.setVisibility(View.VISIBLE);
//            iv_loading.setVisibility(View.GONE);
            rvUser.setVisibility(View.VISIBLE);
        }
    }
}