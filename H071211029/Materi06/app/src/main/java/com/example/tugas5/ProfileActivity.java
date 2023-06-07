package com.example.tugas5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(ProfileFragment.class.getSimpleName());
        ProfileFragment profileFragment = new ProfileFragment();
        if (!(fragment instanceof ProfileFragment)) {
            fragmentManager.beginTransaction().add(R.id.frame_container, profileFragment,
                    ProfileFragment.class.getSimpleName()).commit();
        }
    }
}