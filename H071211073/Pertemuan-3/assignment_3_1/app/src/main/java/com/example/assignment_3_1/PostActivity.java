package com.example.assignment_3_1;

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

public class PostActivity extends AppCompatActivity {

    ImageView imgpost;
    EditText capt;
    Button btnup;
    User user;
    ActivityResultLauncher<Intent> imageCaptureLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        imgpost = findViewById(R.id.imgpost);
        capt = findViewById(R.id.capt);
        btnup = findViewById(R.id.btnup);

        //recieve the parcel
        user = getIntent().getParcelableExtra("user");

        imageCaptureLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri uri = result.getData().getData();
                        user.setPosturi(uri.toString());
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            imgpost.setImageBitmap(bitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setCaption(String.valueOf(capt.getText()));

                Intent toPosted = new Intent(PostActivity.this, PostedActivity.class);
                toPosted.putExtra("user", user);
                startActivity(toPosted);
            }
        });

        imgpost.setOnClickListener(view -> onBtnSelectImageClicked());
    }

    private void onBtnSelectImageClicked(){
        //Implicit Intent to Choice Image
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        imageCaptureLauncher.launch(Intent.createChooser(intent, "Pilih Gambar"));
    }
}