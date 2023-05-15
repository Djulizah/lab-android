package com.example.fragmenttask;

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

import com.example.fragmenttask.models.Post;
import com.google.android.material.textfield.TextInputEditText;

public class PostFragment extends Fragment {

    private ActivityResultLauncher<Intent> launcherIntentPhotos;
    private ImageView ivPhoto;
    private TextInputEditText etCaption;
    private Button btnUpload;
    private Post post;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //buat post baru
        post = new Post();

        //handle kmbalian input gmbr
        launcherIntentPhotos = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent resultIntent = result.getData();
                        if (resultIntent != null) {
                            Uri selectedImageUri = resultIntent.getData();
                            post.setImageUrl(selectedImageUri);

                            //tmbh uri ke object post
                            ivPhoto.setImageURI(selectedImageUri);
                        }
                    }
                }
        );
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivPhoto = view.findViewById(R.id.iv_foto);
        etCaption = view.findViewById(R.id.tv_caption);
        btnUpload = view.findViewById(R.id.btn_upload);

        //tmbah onclik ke fto
        ivPhoto.setOnClickListener((v -> {
            Intent intentPickPhotos = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            launcherIntentPhotos.launch(Intent.createChooser(intentPickPhotos, "Choose a photo"));

        } ));

        //tmbh onclik te tmbol up
        btnUpload.setOnClickListener(v -> {
            post.setCaption(etCaption.getText().toString());

            if (post.getImageUrl()!= null) {
                //tmpilkan toast
                HomeFragment.adapter.addItem(post);
                //redirect ke home
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.navigateFragment(new HomeFragment());

                Toast.makeText(getActivity(), "Post Berhasil", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(), "Upload gambar terlebih dahulu!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}