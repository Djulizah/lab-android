package com.example.networking.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.networking.R;
import com.example.networking.activities.MainActivity;

public class NoConnectionFragment extends Fragment {

    private int userId;
    public NoConnectionFragment() {
    }

    public NoConnectionFragment(int userId) {
        this.userId = userId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_no_connection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageView = view.findViewById(R.id.refresh_button);

        imageView.setOnClickListener(view1 -> {
            if (getActivity() instanceof MainActivity) {
                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new UserListFragment())
                        .commit();
            } else {
                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new UserDetailFragment(userId))
                        .commit();
            }
        });
    }
}