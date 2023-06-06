package com.example.task6_backgroundthread;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity {
    private MeowBottomNavigation meowBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meowBottomNavigation = findViewById(R.id.meowBottom);

        meowBottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.baseline_home_24));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_search_24));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.baseline_add_24));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.baseline_person_24));

        meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

            }
        });

        meowBottomNavigation.show(1, true);
        loadFragment(new HomeFragment());

        meowBottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;

                switch (item.getId()){
                    case 1 :
                        fragment = new HomeFragment();
                        break;
                    case 2 :
                        fragment = new SearchFragment();
                        break;
                    case 3 :
                        fragment = new AddFragment();
                        break;
                    case 4 :
                        fragment = new ProfileFragment();
                        break;
                }

                loadFragment(fragment);
            }
        });

    }

    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();

        TextView appBar = findViewById(R.id.tv_home);

        if (fragment instanceof HomeFragment) {
            appBar.setText("Instagram");
        } else if (fragment instanceof SearchFragment) {
            appBar.setText("Search");
        } else if (fragment instanceof AddFragment) {
            appBar.setText("Upload");
        } else if (fragment instanceof ProfileFragment) {
            appBar.setText("Profile");
        }
    }
}