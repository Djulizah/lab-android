package com.example.tugas7fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());
        if(!(fragment instanceof HomeFragment)) {
            navigateFragment(new HomeFragment());
        }
        navigationListener();
    }

    private void navigationListener(){
        ImageView navHome = findViewById(R.id.nav_home);
        ImageView navAdd = findViewById(R.id.nav_add);
        ImageView navProfile = findViewById(R.id.nav_profile);

        navHome.setOnClickListener(v->{
            navigateFragment(new HomeFragment());
        });
        navProfile.setOnClickListener(v->{
            navigateFragment(new ProfileFragment());
        });
        navAdd.setOnClickListener(v->{
            navigateFragment(new UploadFragment());
        });
    }

    public void navigateFragment(Fragment fragment) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.frame_container, fragment, fragment.getClass().getSimpleName())
                .commit();

        TextView tvToolbar = findViewById(R.id.tv_toolbar);
        if(fragment instanceof HomeFragment){
            tvToolbar.setText(getString(R.string.inigaram));
        }else if(fragment instanceof ProfileFragment){
            tvToolbar.setText(getString(R.string.profile));
        }else if(fragment instanceof UploadFragment){
            tvToolbar.setText(getString(R.string.upload));
        }
    }
}