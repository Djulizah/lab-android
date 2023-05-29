package com.example.task6_backgroundthread;

import android.net.Uri;

import com.example.task6_backgroundthread.models.Upload;
import com.example.task6_backgroundthread.models.User;

import java.util.ArrayList;

public class DataSources {
    private static final String URI_CONST = "android.resource://com.example.task6_backgroundthread/drawable/";
    public static ArrayList<Upload> listPost = new ArrayList<>();

    public DataSources() {
        this.listPost.addAll(generateDummyPosts());
    }

    public ArrayList<Upload> getPosts() {
        return listPost;
    }

    public ArrayList<User> getUsersByQuery(String q) {
        ArrayList<User> filteredUsers = new ArrayList<>();

        User tempUser = listPost.get(0).getUser();

        for (int i = 0; i < listPost.size(); i++) {
            final User user = listPost.get(i).getUser();

            // Pokoknya untuk handle supaya pas nge-search tidak ada user duplikat
            if (i > 0) {
                if (tempUser.getUserName().equals(user.getUserName()) || tempUser.getFullName().equals(user.getFullName())) {
                    continue;
                }
            }

            String query = q.toLowerCase();

            String fullName = user.getFullName().toLowerCase();
            String username = user.getUserName().toLowerCase();

            if (fullName.startsWith(query) || username.startsWith(query)) {
                filteredUsers.add(user);
            }

            tempUser = user;
        }

        return filteredUsers;
    }

    public void addPost(Upload upload) {
        this.listPost.add(0, upload);
    }

    private ArrayList<Upload> generateDummyPosts() {
        ArrayList<Upload> listPost = new ArrayList<>();

        for (int i = 0; i < dataName.length; i++) {
            Upload upload = new Upload();
            upload.setImageUrl(Uri.parse(URI_CONST + dataPhoto[i]));
            upload.setCaption("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In gravida risus sit amet felis ornare, nec dignissim ante ultrices.");
            upload.setUser(new User(dataName[i][0],
                    dataName[i][1],
                    dataPhoto[i]));
            listPost.add(upload);
        }

        return listPost;
    }

    private String[][] dataName = new String[][] {
            {"Adelia", "delsss"},
            {"firaa", "firrr"},
            {"selviani", "selll"},
            {"isty", "istttt"},
            {"besse", "uni"},
    };

    private int[] dataPhoto = new int[] {
            R.drawable.foto,
            R.drawable.foto2,
            R.drawable.foto3,
            R.drawable.foto4,
            R.drawable.foto5,
    };
}
