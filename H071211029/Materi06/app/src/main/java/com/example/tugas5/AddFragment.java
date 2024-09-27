package com.example.tugas5;

import static android.app.Activity.RESULT_OK;

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

public class AddFragment extends Fragment {
    ImageView iv_addPhoto;
    EditText et_caption;
    Button btn_upload;
    User user;
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

        user = new User("Abd. Rafiq Anwar",  R.drawable.emoji, "abd.rafiqanwar", new Add());
//        user.setAdd(new Add());
//        User user1 = new User("Monkey D. Luffy", "Luffy");
//        User user2 = new User("Roronoa Zoro", "Zoro");
//        User user3 = new User("Siti Nami", "Nami");
//        User user4 = new User("Ahmad Usopp", "Usopp");
//        User user5 = new User("Muhammad Sanji", "Sanji");
//        User user6 = new User("Tony Tony Chopper", "Chopper");
//        User user7 = new User("Nico Robin", "Robin");
//        User user8 = new User("Yusuf Franky", "Franky");
//        User user9 = new User()

        imageCaptureLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK){
                        Intent data = result.getData();
                        if (data != null) {
                            Uri image = data.getData();
                            iv_addPhoto.setImageURI(image);
                            user.getAdd().setImage(image);
                        }
                    }
                });

        iv_addPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            imageCaptureLauncher.launch(Intent.createChooser(intent, "Choose a photo"));
        });

        btn_upload.setOnClickListener(v -> {
            user.getAdd().setCaption(et_caption.getText().toString());
            if (user.getAdd().getImage() != null) {
                HomeFragment.adapterHome.add(user);
                SearchFragment.adapterSearch.add(user);
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