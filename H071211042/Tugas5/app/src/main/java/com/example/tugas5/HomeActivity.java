package com.example.tugas5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class HomeActivity extends AppCompatActivity {

    Button btnSubmit;
    ImageView fp;
    EditText name;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        btnSubmit = findViewById(R.id.btnApply);
        fp = findViewById(R.id.fotoProfil);
        name = findViewById(R.id.fullname);


        fp.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, 1);
        });
        btnSubmit.setOnClickListener(view -> {
            if (name.getText().length()==0 && uri==null) {
                Toast.makeText(getApplicationContext(), "Lengkapi data!", Toast.LENGTH_SHORT).show();
            } else if (name.getText().length()<0) {
                Toast.makeText(getApplicationContext(), "Nama lengkap minimal 12 karakter", Toast.LENGTH_SHORT).show();
            } else if (uri==null) {
                Toast.makeText(getApplicationContext(), "Lengkapi foto profil", Toast.LENGTH_SHORT).show();
            }else {
                String namalengkap = name.getText().toString();
                Intent Home = new Intent(this, ProfileActivity.class);
                Home.putExtra("img", uri.toString());
                Home.putExtra("extranamalengkap", namalengkap);
                startActivity(Home);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data!=null) {
            uri = data.getData();
            fp.setImageURI(uri);
        }
    }
}