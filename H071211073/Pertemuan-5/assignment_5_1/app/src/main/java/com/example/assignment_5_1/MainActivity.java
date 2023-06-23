package com.example.assignment_5_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //buttons access
        ImageView btnHome = findViewById(R.id.btn_home);
        ImageView btnPost = findViewById(R.id.btn_add);
        ImageView btnPerson = findViewById(R.id.btn_person);

        navigateFragmen(new HomeFragment());

        btnHome.setOnClickListener(btn -> navigateFragmen(new HomeFragment()) );
        btnPost.setOnClickListener(btn -> navigateFragmen(new PostFragment()));
        btnPerson.setOnClickListener(btn -> navigateFragmen(new PersonFragment()));

    }

    public void navigateFragmen(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment)
                .addToBackStack(null)
                .commit();

        TextView tvTitle = findViewById(R.id.tv_title);
        if(fragment instanceof HomeFragment){
            tvTitle.setText("Inigram");
        } else if (fragment instanceof PostFragment) {
            tvTitle.setText("Add Post");
        } else if (fragment instanceof PersonFragment) {
            tvTitle.setText("Profile");
        }
    }
}