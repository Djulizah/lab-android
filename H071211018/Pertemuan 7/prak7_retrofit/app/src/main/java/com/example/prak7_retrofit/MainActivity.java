package com.example.prak7_retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv_user;
    private LinearLayout ll_reload;
    private UserAdapter userAdapter;
    private ImageView iv_loading;
    private ProgressBar progressBar;
    private ArrayList<UserResponse> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_user = findViewById(R.id.rv_user);
        ll_reload = findViewById(R.id.reload);
        iv_loading = findViewById(R.id.loading);
        progressBar = findViewById(R.id.progressBar);
        userAdapter  = new UserAdapter();

        ll_reload.setVisibility(View.GONE);

        try{
            fetchAPI();
        }catch (Exception e){
            ll_reload.setVisibility(View.VISIBLE);
            iv_loading.setVisibility(View.VISIBLE);
        }
        iv_loading.setOnClickListener(view -> {
            fetchAPI();
        });
    }

        private void fetchAPI() {
            progressBar.setVisibility(View.VISIBLE);
            if (NetworkUtil.isNetworkAvailable(this)) {
                ApiConfig.getApiService().getUser("12").enqueue(new Callback<DataResponse>() {
                    @Override
                    public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            ll_reload.setVisibility(View.GONE);
                            userAdapter.addUser(response.body().getData());
                            progressBar.setVisibility(View.GONE);
                            rv_user.setAdapter(userAdapter);
                            rv_user.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            Log.d("users", response.body().toString());
                        }else{
                            ll_reload.setVisibility(View.VISIBLE);
                            iv_loading.setVisibility(View.VISIBLE);

                        }
                    }

                    @Override
                    public void onFailure(Call<DataResponse> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        ll_reload.setVisibility(View.VISIBLE);
                        iv_loading.setVisibility(View.VISIBLE);

                        Toast.makeText(MainActivity.this, "OnFailure" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else {
                progressBar.setVisibility(View.GONE);

                ll_reload.setVisibility(View.VISIBLE);
                iv_loading.setVisibility(View.VISIBLE);
//            iv_loading.setVisibility(View.GONE);
//                rv_user.setVisibility(View.VISIBLE);
            }
        }

//        Call<DataResponse> client = ApiConfig.getApiService().getUser("20");
//        client.enqueue(new Callback<DataResponse>() {
//            @Override
//            public void onResponse(Call<DataResponse> call, Response<DataResponse>
//                    response) {
//                if (response.isSuccessful()) {
//                    if (response.body() != null) {
//                        ArrayList<UserResponse> userResponse = response.body().getData();
//                        UserAdapter adkhCard = new UserAdapter(userResponse);
//                        rv_user.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                        rv_user.setAdapter(adkhCard);
//                        System.out.println(adkhCard.getItemCount());
//                    }
//                    } else {
//                        if (response.body() != null) {
//                            Log.e("MainActivity", "onFailure1: " + response.message());
//                        }
//                    }
//                }
//            @Override
//            public void onFailure(Call<DataResponse> call, Throwable t) {
//                Log.e("MainActivity", "onFailure: " + t.getMessage());
//            }
//        });
//    }


}
