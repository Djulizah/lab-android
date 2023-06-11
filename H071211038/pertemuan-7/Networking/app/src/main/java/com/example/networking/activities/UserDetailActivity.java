package com.example.networking.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.networking.R;
import com.example.networking.fragments.UserDetailFragment;
import com.example.networking.fragments.UserListFragment;

public class UserDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        int id = getIntent().getIntExtra("KEY", 0);

        Fragment fragment = new UserDetailFragment(id);

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment, UserListFragment.class.getSimpleName())
                .commit();
    }

}