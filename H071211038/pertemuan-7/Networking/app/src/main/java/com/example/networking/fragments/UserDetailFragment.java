package com.example.networking.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.networking.R;
import com.example.networking.models.DataResponse;
import com.example.networking.models.UserResponse;
import com.example.networking.networks.ApiConfig;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailFragment extends Fragment {
    private int userId;


    public UserDetailFragment() {
    }

    public UserDetailFragment(int userId) {
        this.userId = userId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView avatarImageView = view.findViewById(R.id.avatar);
        TextView emailTextView = view.findViewById(R.id.email);
        TextView fullNameTexView = view.findViewById(R.id.fullname);
        CardView cardView = view.findViewById(R.id.card_view);
        ProgressBar progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        Call<DataResponse> client = ApiConfig.getApiService().getUserById(userId);

        client.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse>
                    response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        UserResponse userResponse = response.body().getData();
                        String fullName = userResponse.getFirstName() + " " + userResponse.getLastName();

                        fullNameTexView.setText(fullName);
                        emailTextView.setText(userResponse.getEmail());
                        Glide.with(getActivity())
                                .load(userResponse.getAvatarUrl())
                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                        progressBar.setVisibility(View.GONE);
                                        cardView.setVisibility(View.VISIBLE);

                                        return false;
                                    }
                                })
                                .into(avatarImageView);

                    }
                } else {
                    if (response.body() != null) {
                        Log.e("MainActivity", "onFailure: " + response.message());
                    }
                }
            }
            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new NoConnectionFragment(userId))
                        .commit();
            }
        });
    }
}