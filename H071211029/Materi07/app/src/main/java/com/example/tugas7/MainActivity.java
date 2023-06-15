package com.example.tugas7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    TextView tv_titleError;
    ProgressBar pb;
    ImageView iv_refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);
        tv_titleError = findViewById(R.id.tv_titleError);
        pb = findViewById(R.id.pb);
        iv_refresh = findViewById(R.id.iv_refresh);

        summon();
    }

    public void summon(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        tv_titleError.setVisibility(View.GONE);
        iv_refresh.setVisibility(View.GONE);
        pb.setVisibility(View.VISIBLE);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Call<DataResponse> client = ApiConfig.getApiService().getData("12");
                client.enqueue(new Callback<DataResponse>() {
                    @Override
                    public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null){
                                ArrayList<UserResponse> userResponses = response.body().getData();
                                Adapter adapter = new Adapter(userResponses);

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                        rv.setAdapter(adapter);
                                        pb.setVisibility(View.GONE);
                                    }
                                });
                            }
                        } else {
                            if (response.body() != null){
                                Log.e("MainActivity", "onFailure: " + response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DataResponse> call, Throwable t) {
                        pb.setVisibility(View.GONE);
                        tv_titleError.setVisibility(View.VISIBLE);
                        iv_refresh.setVisibility(View.VISIBLE);

                        iv_refresh.setOnClickListener(v -> {
                            pb.setVisibility(View.VISIBLE);
                            tv_titleError.setVisibility(View.GONE);
                            iv_refresh.setVisibility(View.GONE);
                            summon();
                        });
                    }
                });
            }
        });
    }
}