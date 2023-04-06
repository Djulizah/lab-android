package com.example.a1;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

public class DescActivity extends AppCompatActivity {
    final static String EXTRA_USER = "extra_user";
    private ActivityResultLauncher<Intent> imageCaptureLauncher;
    User user;
    ImageView post_foto;
    EditText desc;
    Button upload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);

        desc = findViewById(R.id.desc);
        upload = findViewById(R.id.upload);
        post_foto = findViewById(R.id.post_foto);

        user = getIntent().getParcelableExtra(EXTRA_USER);

        imageCaptureLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK){
                        Intent data = result.getData();
                        assert data != null;
                        Bitmap bitmap = null;
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                            user.setPostUri(data.getData().toString());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        post_foto.setImageBitmap(bitmap);
                    }
                });


        post_foto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            imageCaptureLauncher.launch(Intent.createChooser(intent, "Pilih Gambar"));
        });

        upload.setOnClickListener(v -> {
            user.setCaption(desc.getText().toString());
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra(EXTRA_USER, user);
            setResult(RESULT_OK, intent);
            startActivity(intent);
        });
    }
}