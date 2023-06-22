package com.example.assignment_5_1;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class PostFragment extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 1;
    Post newPost;
    Uri imgUri;
    String uri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imgPost = view.findViewById(R.id.img_post);
        EditText etCaption = view.findViewById(R.id.et_caption);
        Button btnUpload = view.findViewById(R.id.btn_upload);

        imgPost.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
//            startActivity(intent);
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        });

        btnUpload.setOnClickListener(view1 -> {

            String caption = String.valueOf(etCaption.getText());
            uri = imgUri.toString();

            newPost = new Post(uri, caption);
            DataSource.posts.add(newPost);

            PostAdapter adapter = new PostAdapter(getContext(), DataSource.posts);
            adapter.notifyDataSetChanged();

            HomeFragment homeFragment = new HomeFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, homeFragment,
                            HomeFragment.class.getSimpleName())
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            imgUri = data.getData();

            ImageView imgPost = getView().findViewById(R.id.img_post);
            imgPost.setImageURI(imgUri);

            uri = imgUri.toString();
        }
    }
}