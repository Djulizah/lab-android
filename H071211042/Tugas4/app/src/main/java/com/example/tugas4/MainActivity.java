package com.example.tugas4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import android.net.Uri;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tugas4.databinding.ActivityCaptionBinding;
import com.example.tugas4.databinding.ActivityFinalBinding;
import com.example.tugas4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

        ImageView iv_profile;
        EditText et_namalengkap, et_username;
        Button btn_submit;
        Uri uri;

    private ActivityMainBinding binding;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            iv_profile = findViewById(R.id.fotoProfil);
            et_namalengkap = findViewById(R.id.fullname);
            et_username = findViewById(R.id.username);
            btn_submit = findViewById(R.id.btnSubmit);

            iv_profile.setOnClickListener(v -> {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            });

            btn_submit.setOnClickListener(v -> {
                if (et_namalengkap.getText().length()==0 && et_username.getText().length()==0 && uri==null) {
                    Toast.makeText(getApplicationContext(), "Lengkapi data!", Toast.LENGTH_SHORT).show();
                } else if (et_namalengkap.getText().length()<12) {
                    Toast.makeText(getApplicationContext(), "Nama lengkap minimal 12 karakter", Toast.LENGTH_SHORT).show();
                } else if (et_username.getText().length()<4) {
                    Toast.makeText(getApplicationContext(), "Nama pengguna minimal 4 karakter", Toast.LENGTH_SHORT).show();
                } else if (uri==null) {
                    Toast.makeText(getApplicationContext(), "Lengkapi foto profil", Toast.LENGTH_SHORT).show();
                }else {
                    String namalengkap = et_namalengkap.getText().toString();
                    String username = et_username.getText().toString();
                    Intent intent = new Intent(this, CaptionActivity.class);
                    Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent1.setType("image/*");
                    intent.putExtra("img", uri.toString());
                    intent.putExtra("extranamalengkap", namalengkap);
                    intent.putExtra("extrausername", username);
                    startActivity(intent);
                }
            });
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 1 && data!=null) {
                uri = data.getData();
                iv_profile.setImageURI(uri);
            }
        }
    }