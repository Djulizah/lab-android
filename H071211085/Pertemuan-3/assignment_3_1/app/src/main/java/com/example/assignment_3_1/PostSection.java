package com.example.assignment_3_1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.assignment_3_1.databinding.ActivityPostSectionBinding;

public class PostSection extends AppCompatActivity {

    private ActivityPostSectionBinding binding;
    ImageAccess profileImage, postImage;

    private ActivityResultLauncher<Intent> PictureLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() !=null) {
                        Uri ChooseImage = result.getData().getData();
                        binding.ivPostImage.setImageURI(ChooseImage);
                        postImage.setImageUri(ChooseImage);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostSectionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        String fulllname = intent.getStringExtra("fullname");
        String username = intent.getStringExtra("username");

        profileImage = intent.getParcelableExtra("profile");
        postImage = new ImageAccess();

        binding.ivPostImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentImage = new Intent(Intent.ACTION_PICK);
                intentImage.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                PictureLauncher.launch(Intent.createChooser(intentImage, "Choose your Photo!"));

            }
        });

        binding.uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isEmpty = false;
                String caption = binding.caption.getText().toString();

                if (postImage.getImageUri() == null) {
                    Toast.makeText(PostSection.this, "Choose your photo first", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isEmpty) {
                    Intent upload = new Intent(PostSection.this, FeedSection.class);
                    upload.putExtra("fullname", fulllname);
                    upload.putExtra("username", username);
                    upload.putExtra("caption", caption);
                    upload.putExtra("profile", profileImage);
                    upload.putExtra("picture", postImage);
                    startActivity(upload);
                }
            }
        });
    }
}