package com.example.tp5;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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

import com.example.tp5.models.Post;
import com.example.tp5.models.User;

public class AddFragment extends Fragment {
    private ImageView ivFoto;
    private EditText etCapt;
    Button btnUpload;
    private Post post;

    private ActivityResultLauncher<Intent> launcherIntentPhotos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        post = new Post();
        post.setUser(new User("Tantan","Kamado Tanjiro",R.drawable.tanjiro));
        launcherIntentPhotos = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent resultIntent = result.getData();
                        if (resultIntent != null) {
                            Uri selectedImageUri = resultIntent.getData();
                            ivFoto.setImageURI(selectedImageUri);
                            post.setImageUrl(selectedImageUri);
                        }
                    }
                }
        );
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         ivFoto = view.findViewById(R.id.ivFoto);
         etCapt = view.findViewById(R.id.etCapt);
         btnUpload = view.findViewById(R.id.btnUpload);

        ivFoto.setOnClickListener(view1 -> {
            Intent intentPickPhotos = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            launcherIntentPhotos.launch(Intent.createChooser(intentPickPhotos, "Choose a photo"));
        });
        btnUpload.setOnClickListener(view1 -> {
            post.setCaption(etCapt.getText().toString());
            if(post.getImageUrl() != null){
                HomeFragment.data.addPost(post);
                Toast.makeText(getActivity(), "Post Sukses", Toast.LENGTH_SHORT).show();
                etCapt.setText(null);
            }
        });

    }
}