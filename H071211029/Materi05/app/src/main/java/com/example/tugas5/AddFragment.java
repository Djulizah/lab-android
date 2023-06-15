package com.example.tugas5;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class AddFragment extends Fragment {
    ImageView iv_addPhoto;
    EditText et_caption;
    Button btn_upload;
    Add add;
    ActivityResultLauncher<Intent> imageCaptureLauncher;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        iv_addPhoto = view.findViewById(R.id.iv_addPhoto);
        et_caption = view.findViewById(R.id.et_caption);
        btn_upload = view.findViewById(R.id.btn_upload);
        add = new Add();

        imageCaptureLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK){
                        Intent data = result.getData();
                        if (data != null) {
                            Uri image = data.getData();
                            iv_addPhoto.setImageURI(image);
                            add.setImage(image);
                        }
                    }
                });

        iv_addPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            imageCaptureLauncher.launch(Intent.createChooser(intent, "Choose a photo"));
        });

        btn_upload.setOnClickListener(v -> {
            add.setCaption(et_caption.getText().toString());
            if (add.getImage() != null) {
                HomeFragment.adapter.add(add);
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                        new HomeFragment(), HomeFragment.class.getSimpleName()).commit();
                mainActivity.tv_tollbar.setText("Inigaram");
                Toast.makeText(getActivity(), "Post success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Please pick a photo first", Toast.LENGTH_SHORT).show();
            }
        });
    }
}