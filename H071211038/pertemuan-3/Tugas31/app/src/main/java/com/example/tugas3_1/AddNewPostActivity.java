package com.example.tugas3_1;

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

import java.io.IOException;

public class AddNewPostActivity extends AppCompatActivity {
    private Button btnUpload;
    private EditText caption;
    private ImageView addImg;
    private Uri imageUri;
    private final String KEY_PROFILE = "KEY_PROFILE";
    private final String KEY_POST= "KEY_POST";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_post);
        btnUpload = findViewById(R.id.btn_upload);
        addImg = findViewById(R.id.post_image);
        caption = findViewById(R.id.caption);

        addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageUri!=null) {
                    User user = getIntent().getParcelableExtra(KEY_PROFILE);
                    User post = new User(user.getProfileImg(), user.getFullname(), user.getUsername(), imageUri.toString(), caption.getText().toString());

                    Intent setPost = new Intent(AddNewPostActivity.this, MyPostActivity.class);
                    setPost.putExtra(KEY_POST, post);
                    startActivity(setPost);
                } else {
                    Toast.makeText(AddNewPostActivity.this, "Please insert a post image first!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                addImg.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}