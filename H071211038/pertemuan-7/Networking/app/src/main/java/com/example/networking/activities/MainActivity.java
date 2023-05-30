package com.example.networking.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.networking.R;
import com.example.networking.fragments.UserListFragment;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        UserListFragment userListFragment = new UserListFragment();

        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, userListFragment, UserListFragment.class.getSimpleName())
                .commit();

    }

}