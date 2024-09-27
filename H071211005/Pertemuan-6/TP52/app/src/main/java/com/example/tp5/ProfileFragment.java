package com.example.tp5;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tp5.models.User;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ProfileFragment extends Fragment {
    private ShapeableImageView ivProfil;
    private TextView tvName;
    private TextView tvUser;
    private LinearLayout llLoading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        llLoading = view.findViewById(R.id.llloading);
        User user = null;
        if (getActivity().getIntent().getParcelableExtra("user") == null) {
            user = new User("Tantan","Kamado Tanjiro", R.drawable.tanjiro);
        }else {
            user = getActivity().getIntent().getParcelableExtra("user");
            llLoading.setVisibility(View.VISIBLE);
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executor.execute(() -> {
                try {
                    //simulate process in background thread
                    Thread.sleep(500);

                    handler.post(() -> {
                        //update ui in main thread
                        llLoading.setVisibility(View.GONE);

                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }});
        }
        ivProfil = view.findViewById(R.id.ivProfil);
        tvName = view.findViewById(R.id.tvName);
        tvUser = view.findViewById(R.id.tvUser);


        tvName.setText(user.getFullname());
        tvUser.setText(user.getUsername());
        ivProfil.setImageResource(user.getImageUrl());


    }
}