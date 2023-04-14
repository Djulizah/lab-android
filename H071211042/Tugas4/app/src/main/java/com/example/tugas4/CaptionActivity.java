package com.example.tugas4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import android.net.Uri;
import android.widget.Toast;

public class CaptionActivity extends AppCompatActivity {

    Button btnUpload;
    ImageView fotoCapt;
    EditText caption;

    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caption);

        fotoCapt = findViewById(R.id.fotoCapt);
        caption = findViewById(R.id.capt);

        btnUpload = findViewById(R.id.btnUpload);


        fotoCapt.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, 1);
        });
        btnUpload.setOnClickListener(v -> {
            if (caption.getText().length()==0 && uri==null) {
                Toast.makeText(getApplicationContext(), "Lengkapi data!", Toast.LENGTH_SHORT).show();
            } else if (caption.getText().length() == 0) {
                Toast.makeText(getApplicationContext(), "Isi Caption", Toast.LENGTH_SHORT).show();
            } else if (uri==null) {
                Toast.makeText(getApplicationContext(), "Lengkapi foto profil", Toast.LENGTH_SHORT).show();
            }else {
                String name = getIntent().getStringExtra("extranamalengkap");
                String user = getIntent().getStringExtra("extrausername");
                String capt = caption.getText().toString();
                Intent intent = new Intent(this, FinalActivity.class);
                intent.putExtra("imgcapt", uri.toString());
                intent.putExtra("extracaption", capt);
                intent.putExtra("img",getIntent().getStringExtra("img"));
                intent.putExtra("extranamalengkap", getIntent().getStringExtra("extranamalengkap"));
                intent.putExtra("extrausername", getIntent().getStringExtra("extrausername"));
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data!=null) {
            uri = data.getData();
            fotoCapt.setImageURI(uri);
        }
    }
}