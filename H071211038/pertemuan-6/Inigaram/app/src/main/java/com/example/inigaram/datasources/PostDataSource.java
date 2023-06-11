package com.example.inigaram.datasources;

import android.net.Uri;

import com.example.inigaram.R;
import com.example.inigaram.model.Post;

import java.util.ArrayList;

public class PostDataSource {
    private static ArrayList<Post> postArrayList;

    public static ArrayList<Post> getPostList() {
        if (postArrayList == null) {
            postArrayList = new ArrayList<>();
            // Add posts to the list
            postArrayList.add(new Post(1, R.drawable.spongebob, "Test"));
            postArrayList.add(new Post(2, R.drawable.spongebob, "Test"));
            postArrayList.add(new Post(3, R.drawable.spongebob, "Test"));
            postArrayList.add(new Post(4, R.drawable.spongebob, "Test"));
            postArrayList.add(new Post(5, R.drawable.spongebob, "Test"));
        }
        return postArrayList;
    }

    public static void addPost(Post post) {
        postArrayList.add(post);
    }


}
