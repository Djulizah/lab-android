package com.example.tp5;

import android.net.Uri;

import com.example.tp5.models.Post;
import com.example.tp5.models.User;

import java.util.ArrayList;

public class DataSource {
    // com.example.testfragment adalah nama package project (sesuaikan dengan project kalian)
    private static final String URI_CONST = "android.resource://com.example.tp5/drawable/";
    private final ArrayList<Post> posts = new ArrayList<>();

    public DataSource() {
        generateDummyPosts();
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public ArrayList<User> getUsersByQuery(String q) {
        ArrayList<User> filteredUsers = new ArrayList<>();

        User tempUser = posts.get(0).getUser();

        for (int i = 0; i < posts.size(); i++) {
            final User user = posts.get(i).getUser();

            // Pokoknya untuk handle supaya pas nge-search tidak ada user duplikat
            if (i > 0) {
                if (tempUser.getUsername().equals(user.getUsername()) || tempUser.getFullname().equals(user.getFullname())) {
                    continue;
                }
            }

            String query = q.toLowerCase();

            String fullName = user.getFullname().toLowerCase();
            String username = user.getUsername().toLowerCase();

            if (fullName.startsWith(query) || username.startsWith(query)) {
                filteredUsers.add(user);
            }
            tempUser = user;
        }

        return filteredUsers;
    }

    public void addPost(Post post) {
        this.posts.add(0, post);
    }

    private void generateDummyPosts() {
    posts.clear();
        for (int i = 0; i < names.length; i++) {
            Post post = new Post();
            post.setImageUrl(Uri.parse(URI_CONST + photoRes[i]));
            post.setCaption("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In gravida risus sit amet felis ornare, nec dignissim ante ultrices.");
            post.setUser(new User(names[i][0],
                    names[i][1],
                    photoRes[i]));
            posts.add(post);
        }
    }

    private final String[][] names = new String[][]{
            {"Monkey D. Luffy", "Luffy"},
            {"Roronoa Zoro", "Zoro"},
            {"Siti Nami", "Nami"},
            {"Ahmad Usopp", "Usopp"},
            {"Muhammad Sanji", "Sanji"}
    };

    private final int[] photoRes = new int[]{
            R.drawable.im1,
            R.drawable.im2,
            R.drawable.im3,
            R.drawable.im4,
            R.drawable.im5,
    };
}
