package com.example.fragmenttask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView ivHome, ivPost, ivProfile;
    TextView tvToolbar;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivHome = findViewById(R.id.iv_home);
        ivPost = findViewById(R.id.iv_post);
        ivProfile = findViewById(R.id.iv_profile);
        tvToolbar = findViewById(R.id.tv_toolbar);

        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());
        if (!(fragment instanceof  HomeFragment)) {
           navigateFragment(new HomeFragment());
        }

        ivHome.setOnClickListener(v -> {
            navigateFragment(new HomeFragment());
        });

        ivPost.setOnClickListener(v -> {
            navigateFragment(new PostFragment());
        });

        ivProfile.setOnClickListener(v -> {
            navigateFragment(new ProfileFragment());
        });


    }

    public void navigateFragment (Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_main,fragment, fragment.getClass().getSimpleName())
                .commit();

        //komdisi utk cek fragment skrg
        if (fragment instanceof HomeFragment) {
            tvToolbar.setText("InstagramKu");
        }
        if (fragment instanceof ProfileFragment) {
            tvToolbar.setText("Profile");
        }
        if (fragment instanceof PostFragment) {
            tvToolbar.setText("Post");
        }
    }
}