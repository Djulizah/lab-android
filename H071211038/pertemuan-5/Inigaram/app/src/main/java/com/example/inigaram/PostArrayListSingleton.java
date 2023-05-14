package com.example.inigaram;

import java.util.ArrayList;

import model.Post;

public class PostArrayListSingleton {
    private static final PostArrayListSingleton instance = new PostArrayListSingleton();
    private static ArrayList<Post> postArrayList;

    private PostArrayListSingleton() {
        postArrayList = new ArrayList<>();
    }

    public static PostArrayListSingleton getInstance() {
        return instance;
    }

    public ArrayList<Post> getPostArrayList() {
        return postArrayList;
    }

    public static void addPost(Post post) {
        postArrayList.add(post);
    }

    public interface OnPostAddedListener {
        void onPostAdded(Post post);
    }
}