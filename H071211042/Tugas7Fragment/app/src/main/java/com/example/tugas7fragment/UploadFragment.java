package com.example.tugas7fragment;

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

import com.example.tugas7fragment.models.Post;
import com.example.tugas7fragment.models.User;


public class UploadFragment extends Fragment {
    private ActivityResultLauncher<Intent> launcherIntentPhotos;
    private User user;
    private ImageView ivPhoto;
    private Button btnUpload;
    private EditText etCaption;
    private MainActivity mainActivity;





    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivPhoto = view.findViewById(R.id.iv_photo);
        btnUpload = view.findViewById(R.id.btn_upload);
        etCaption = view.findViewById(R.id.et_caption);

        mainActivity = ((MainActivity) getActivity());

        user = new User(
                getString(R.string.full_name),
                getString(R.string.username),
                R.drawable.id,
                new Post()
        );

        ivPhoto.setOnClickListener(v->{
            Intent intentPickPhotos = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            launcherIntentPhotos.launch(Intent.createChooser(intentPickPhotos, "Choose a photo"));
        });


        btnUpload.setOnClickListener(v->upload());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        launcherIntentPhotos = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent resultIntent = result.getData();
                        if (resultIntent != null) {
                            Uri selectedImageUri = resultIntent.getData();
                            user.getPost().setImageUrl(selectedImageUri);
                            ivPhoto.setImageURI(selectedImageUri);
                        }
                    }
                }
        );
        return inflater.inflate(R.layout.fragment_upload, container, false);
    }

    private void upload(){

        if (user.getPost().getImageUrl() == null) {
            Toast.makeText(getActivity(), "Please pick a photo first", Toast.LENGTH_SHORT).show();
            return;
        }

        String caption = etCaption.getText().toString().trim();
        user.getPost().setCaption(caption);

        etCaption.setText("");
        ivPhoto.setImageURI(null);

        HomeFragment.adapter.addItem(user);

        mainActivity.navigateFragment(new HomeFragment());
        Toast.makeText(mainActivity, "Post success", Toast.LENGTH_SHORT).show();
    }
}