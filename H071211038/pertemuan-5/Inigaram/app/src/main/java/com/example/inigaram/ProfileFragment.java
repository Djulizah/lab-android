package com.example.inigaram;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import model.User;

public class ProfileFragment extends Fragment {
    private final User user = User.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView fullNameTextView = view.findViewById(R.id.fullname);
        TextView userNameTextView = view.findViewById(R.id.username);
        ImageView profilePictureImageView = view.findViewById(R.id.profile_picture);

        String fullName = user.getFullName();
        String userName = user.getUserName();
        int profilePictureId = user.getProfilePicture();

        fullNameTextView.setText(fullName);
        userNameTextView.setText(userName);
        profilePictureImageView.setImageResource(profilePictureId);

    }
}