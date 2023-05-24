package com.example.fragmenttask;

import android.net.Uri;

import com.example.fragmenttask.models.Post;
import com.example.fragmenttask.models.User;

import java.util.ArrayList;

public class DataSource {
    private static final String URI_CONST = "android.resource://com.example.fragmenttask/drawable/";

    public static ArrayList<Post> listpost = new ArrayList<>();

    public DataSource() {
        this.listpost.addAll(generateDummyPosts());
    }

    public ArrayList<Post> getPosts() {
        return listpost;
    }

    public ArrayList<User> getUsersByQuery(String q) {
        ArrayList<User> filteredUsers = new ArrayList<>();

        User tempUser = listpost.get(0).getUser();

        for (int i = 0; i < listpost.size(); i++) {
            final User user = listpost.get(i).getUser();

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
        this.listpost.add(0, post);
    }

    private ArrayList<Post> generateDummyPosts() {
        ArrayList<Post> listpost = new ArrayList<>();

        for (int i = 0; i < names.length; i++) {
            Post post = new Post();
            post.setImageUrl(Uri.parse(URI_CONST + photoRes[i]));
            post.setCaption("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In gravida risus sit amet felis ornare, nec dignissim ante ultrices.");
            post.setUser(new User(names[i][0],
                    names[i][1],
                    photoRes[i]));
            listpost.add(post);
        }

        return listpost;
    }

        private final String[][] names = new String[][]{
                {"Adell", "dellsss"},
                {"Puspitaa", "psptaaa"},
                {"Selviani", "sellvv"},
                {"Sahriyuni", "unsss"},
                {"IstyyHam", "istyykk"},
        };

        private final int[] photoRes = new int[]{
                R.drawable.foto1,
                R.drawable.foto2,
                R.drawable.foto3,
                R.drawable.salma,
                R.drawable.rony,
        };

    }

