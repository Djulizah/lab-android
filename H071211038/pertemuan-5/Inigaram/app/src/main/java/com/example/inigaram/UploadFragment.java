package com.example.inigaram;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import model.Post;

public class UploadFragment extends Fragment {

    private final int REQUEST_CODE = 1;
    private Button uploadButton;
    private EditText postCaption;
    private ImageView postImage;
    private Uri imageUri;

    private PostArrayListSingleton.OnPostAddedListener onPostAddedListener;

    public void setOnPostAddedListener(PostArrayListSingleton.OnPostAddedListener listener) {
        this.onPostAddedListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_upload, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        uploadButton = view.findViewById(R.id.btn_upload);
        postImage = view.findViewById(R.id.post_image);
        postCaption = view.findViewById(R.id.post_caption);

        postImage.setOnClickListener(v -> pickImage());

        uploadButton.setOnClickListener(v -> uploadPost());
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE);
    }

    private void uploadPost() {
        if (imageUri != null && onPostAddedListener != null) {

            Post post = new Post(imageUri, postCaption.getText().toString());

            onPostAddedListener.onPostAdded(post);

            Toast.makeText(requireContext(), "Berhasil Mengupload", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), "Please insert a post image first!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            postImage.setImageURI(imageUri);
        } else {
            Toast.makeText(requireContext(), "Image picker canceled or failed", Toast.LENGTH_SHORT).show();
        }
    }

    public interface PostListener {
        void onPostAdded(Post post);
    }
}
