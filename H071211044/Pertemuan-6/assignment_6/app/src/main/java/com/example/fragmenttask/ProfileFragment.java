package com.example.fragmenttask;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fragmenttask.models.User;
import com.google.android.material.imageview.ShapeableImageView;


public class ProfileFragment extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity().getIntent().getParcelableExtra("user" ) != null) {
            User user = getActivity().getIntent().getParcelableExtra("user");

            TextView tvUsername = view.findViewById(R.id.tv_username);
            TextView name = view.findViewById(R.id.tv_nama);
            ShapeableImageView ivProfile = view.findViewById(R.id.iv_profil);

            tvUsername.setText(user.getUsername());
            name.setText(user.getFullname());
            ivProfile.setImageResource(user.getImageUrl());
        }
    }
}