package com.example.inigaram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.inigaram.fragment.ProfileFragment;
import com.example.inigaram.model.User;

public class DetailProfileActivity extends AppCompatActivity {
    public static final String KEY_PROFILE = "KEY_PROFILE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_profile);

        FragmentManager fragmentManager = getSupportFragmentManager();

        ProfileFragment profileFragment = new ProfileFragment();

        User user = getIntent().getParcelableExtra(KEY_PROFILE);
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_PROFILE, user);
        profileFragment.setArguments(bundle);

        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, profileFragment, ProfileFragment.class.getSimpleName())
                .commit();
    }
}