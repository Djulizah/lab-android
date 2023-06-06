package com.example.task6_backgroundthread;

import android.app.Activity;
import android.content.Intent;
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

import com.example.task6_backgroundthread.models.Upload;
import com.example.task6_backgroundthread.models.User;

public class AddFragment extends Fragment {
    private ImageView iv_image;
    private EditText et_caption;
    private Button btn_upload;
    private Upload upload;
    private ActivityResultLauncher<Intent> launcherIntentPhotos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //buat post baru
        upload = new Upload();
        upload.setUser(new User("Astrina Wulan Putri", "astrinaawln", R.drawable.foto));

        launcherIntentPhotos = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent resultIntent = result.getData();
                        if (resultIntent != null) {
                            Uri selectedImageUri = resultIntent.getData();
                            //ubah gambar
                            iv_image.setImageURI(selectedImageUri);
                            upload.setImageUrl(selectedImageUri);
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
        iv_image = view.findViewById(R.id.iv_image);
        et_caption = view.findViewById(R.id.et_caption);
        btn_upload = view.findViewById(R.id.btn_upload);

        //tambah onclick ke foto
        iv_image.setOnClickListener(v->{
            //buka galeri
//            Intent intentPickPhotos = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            launcherIntentPhotos.launch(Intent.createChooser(intentPickPhotos, "Choose a photo"));
            Intent intentPicker = new Intent(Intent.ACTION_GET_CONTENT);
            intentPicker.setType("image/*");
            launcherIntentPhotos.launch(Intent.createChooser(intentPicker, "Choose a Photo"));
        });

        btn_upload.setOnClickListener(v->{
            upload.setCaption(et_caption.getText().toString());
            if (upload.getImageUrl()!=null){
                //menampilkan toast
                HomeFragment.dataSources.addPost(upload);


                Toast.makeText(getActivity(), "Post success", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getActivity(), "Add image first", Toast.LENGTH_SHORT).show();
            }
        });


    }

}