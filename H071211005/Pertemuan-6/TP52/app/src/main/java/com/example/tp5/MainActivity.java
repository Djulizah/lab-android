package com.example.tp5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    private TextView tv_toolbar;

    private BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment = new HomeFragment();
    private ProfileFragment profileFragment = new ProfileFragment();
    private AddFragment addFragment = new AddFragment();

    private SearchFragment searchFragment = new SearchFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_toolbar = findViewById(R.id.tv_toolbar);
        bottomNavigationView = findViewById(R.id.bottomView);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();
                tv_toolbar.setText("Inigaram");
                return true;
            case R.id.search:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, searchFragment).commit();
                tv_toolbar.setText("Search");
                return true;
            case R.id.add:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, addFragment).commit();
                tv_toolbar.setText("Add");
                return true;
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, profileFragment).commit();
                tv_toolbar.setText("Profil");
                return true;
        }
        return false;
    }
}