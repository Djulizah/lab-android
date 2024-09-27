package com.example.tugas8;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tugas8.models.Post;
import com.example.tugas8.models.User;

import java.util.ArrayList;
public class HomeFragment extends Fragment {
    private RecyclerView rv_home;
    PostAdapter postAdapter;
    Parcelable post;
    User user;

    static ArrayList<User> postingan = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_home = view.findViewById(R.id.rv_postingan);
        rv_home.setHasFixedSize(true);
        rv_home.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = getArguments();
        if (bundle !=null){
            post = bundle.getParcelable("PhotoPost");
            user = new User("Muh Hudri Perdana Hutba", "Mr_Clasher", R.drawable.fp, (Post) post);
            postingan.add(user);
            System.out.println(postingan);
        }

        if(postingan.isEmpty()){
            Uri imageUri = Uri.parse("android.resource://"+getContext()+"/"+R.drawable.id);
            Post post1 = new Post(imageUri, "Aloha!!!");
            user = new User("Muh Hudri Perdana Hutba", "Mr_Clasher", R.drawable.id, (Post) post1);
            postingan.add(user);

            Uri imageUri1 = Uri.parse("android.resource://"+getContext()+"/"+R.drawable.fp);
            Post post2 = new Post(imageUri1, "Aloha!!!");
            user = new User("Ikhsan", "Clasher", R.drawable.fp, (Post) post2);
            postingan.add(user);
        }

        postAdapter = new PostAdapter(postingan);
        rv_home.setAdapter(postAdapter);
        System.out.println(postingan);
    }

}