package com.example.inigaram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class DetailProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_profile);

        FragmentManager fragmentManager = getSupportFragmentManager();

        ProfileFragment profileFragment = new ProfileFragment();

        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, profileFragment, ProfileFragment.class.getSimpleName())
                .commit();
    }
}