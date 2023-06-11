package com.example.inigaram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inigaram.datasources.PostDataSource;
import com.example.inigaram.fragment.HomeFragment;
import com.example.inigaram.fragment.ProfileFragment;
import com.example.inigaram.fragment.SearchFragment;
import com.example.inigaram.fragment.UploadFragment;
import com.example.inigaram.model.Post;

public class MainActivity extends AppCompatActivity implements OnPostAddedListener {
    private TextView headerTextView;
    private HomeFragment homeFragment;
    private PostDataSource postDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView btnProfile = findViewById(R.id.profile_button);
        ImageView btnHome = findViewById(R.id.home_button);
        ImageView btnSearch = findViewById(R.id.search_button);
        ImageView btnUpload = findViewById(R.id.upload_button);
        headerTextView = findViewById(R.id.header_text_view);

        FragmentManager fragmentManager = getSupportFragmentManager();

        postDataSource = new PostDataSource();
        homeFragment = new HomeFragment();

        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, homeFragment, HomeFragment.class.getSimpleName())
                .commit();

        btnHome.setOnClickListener(view -> showFragment(new HomeFragment(), "Inigaram"));

        btnSearch.setOnClickListener(view -> showFragment(new SearchFragment(), "Search"));

        btnUpload.setOnClickListener(view -> showFragment(new UploadFragment(), "Upload"));

        btnProfile.setOnClickListener(view -> showFragment(new ProfileFragment(), "Profile"));

    }

    public void showFragment(Fragment fragment, String title) {
        if (fragment instanceof UploadFragment) {
            UploadFragment uploadFragment = (UploadFragment) fragment;
            uploadFragment.setOnPostAddedListener(this);
        }

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (currentFragment == null ||
                !currentFragment.getClass().getSimpleName().equals(fragment.getClass().getSimpleName())) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            headerTextView.setText(title);
        }
    }

    @Override
    public void onPostAdded(Post post) {
        postDataSource.addPost(post);
        showFragment(homeFragment, "Inigaram");
    }

}
