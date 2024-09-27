package com.example.networking.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.networking.R;
import com.example.networking.adapters.UserAdapter;
import com.example.networking.models.DataListResponse;
import com.example.networking.models.UserResponse;
import com.example.networking.networks.ApiConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListFragment extends Fragment {
    public UserListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        ProgressBar progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        Call<DataListResponse> client = ApiConfig.getApiService().getUser("12");
        client.enqueue(new Callback<DataListResponse>() {
            @Override
            public void onResponse(Call<DataListResponse> call, Response<DataListResponse>
                    response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<UserResponse> userResponseList = response.body().getData();

                        UserAdapter userAdapter = new UserAdapter(userResponseList, getActivity());

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerView.setAdapter(userAdapter);
                    }
                } else {
                    if (response.body() != null) {
                        Log.e("MainActivity", "onFailure: " + response.message());
                    }
                }
            }
            @Override
            public void onFailure(Call<DataListResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new NoConnectionFragment())
                            .commit();
            }
        });
    }
}