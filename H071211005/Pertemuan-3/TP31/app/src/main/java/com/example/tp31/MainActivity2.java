package com.example.tp31;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    ImageView ivFoto;
    EditText etCapt;
    Button btnUpload;
    boolean isEmpty = false;
    User user;

    private ActivityResultLauncher<Intent> imageCaptureLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri uri = result.getData().getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            user.setPostUri(uri.toString());
                            ivFoto.setImageBitmap(bitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ivFoto = findViewById(R.id.ivFoto);
        etCapt = findViewById(R.id.etCapt);
        btnUpload = findViewById(R.id.btnUpload);


        Intent intent = getIntent();
        user = intent.getParcelableExtra("user");

        ivFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                imageCaptureLauncher.launch(Intent.createChooser(intent, "Pilih Gambar"));
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setCaption(String.valueOf(etCapt.getText()));

                if(user.getPostUri() == null) {
                    Toast.makeText(MainActivity2.this, "Pick your photo to post", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!isEmpty) {
                    Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
                }
            }
        });
    }


}