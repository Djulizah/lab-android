package com.example.task5_fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(ProfileFragment.class.getSimpleName());
        if (!(fragment instanceof ProfileFragment)) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_main, new ProfileFragment(), ProfileFragment.class.getSimpleName())
                    .commit();
        }
    }
}