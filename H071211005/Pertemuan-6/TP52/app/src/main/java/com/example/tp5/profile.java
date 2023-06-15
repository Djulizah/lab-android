package com.example.tp5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new ProfileFragment()).commit();

    }
}