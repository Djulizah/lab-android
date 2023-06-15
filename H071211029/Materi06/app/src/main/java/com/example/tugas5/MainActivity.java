package com.example.tugas5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView iv_home, iv_add, iv_profile, iv_search;
    TextView tv_tollbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_home = findViewById(R.id.iv_home);
        iv_add = findViewById(R.id.iv_add);
        iv_profile = findViewById(R.id.iv_profile);
        tv_tollbar = findViewById(R.id.tv_Toolbar);
        iv_search = findViewById(R.id.iv_search);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());

        HomeFragment homeFragment = new HomeFragment();
        SearchFragment searchFragment = new SearchFragment();
        AddFragment addFragment = new AddFragment();
        ProfileFragment profileFragment = new ProfileFragment();

        if (!(fragment instanceof HomeFragment)) {
            fragmentManager.beginTransaction().add(R.id.frame_container, homeFragment,
                    HomeFragment.class.getSimpleName()).commit();
        }

        iv_home.setOnClickListener(v -> {
            fragmentManager.beginTransaction().replace(R.id.frame_container, homeFragment,
                    HomeFragment.class.getSimpleName()).commit();
            tv_tollbar.setText("Inigaram");
        });

        iv_search.setOnClickListener(v -> {
            fragmentManager.beginTransaction().replace(R.id.frame_container, searchFragment,
                    SearchFragment.class.getSimpleName()).commit();
            tv_tollbar.setText("Search");
        });

        iv_add.setOnClickListener(v -> {
            fragmentManager.beginTransaction().replace(R.id.frame_container, addFragment,
                    AddFragment.class.getSimpleName()).commit();
            tv_tollbar.setText("Upload");
        });

        iv_profile.setOnClickListener(v -> {
            fragmentManager.beginTransaction().replace(R.id.frame_container, profileFragment,
                    ProfileFragment.class.getSimpleName()).commit();
            tv_tollbar.setText("Profile");
        });
    }
}