package com.example.tugas4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import android.net.Uri;
import android.widget.Toast;

import com.example.tugas4.databinding.ActivityFinalBinding;

public class FinalActivity extends AppCompatActivity {

    ImageView fotoProfil, fotoCapt;
    TextView fullname,username,capt;

    private ActivityFinalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        binding = ActivityFinalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String uriCaption = getIntent().getStringExtra("imgcapt");
        String uriProfile = getIntent().getStringExtra("img");
        binding.fotoProfil.setImageURI(Uri.parse(uriProfile));
        binding.fotoCapt.setImageURI(Uri.parse(uriCaption));
        binding.capt.setText(getIntent().getExtras().getString("extracaption"));
        binding.fullname.setText(getIntent().getExtras().getString("extranamalengkap"));
        binding.username.setText(getIntent().getExtras().getString("extrausername"));
        }
    }