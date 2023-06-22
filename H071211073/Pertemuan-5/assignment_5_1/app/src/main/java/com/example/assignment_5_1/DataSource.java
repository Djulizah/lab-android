package com.example.assignment_5_1;

import java.util.ArrayList;

public class DataSource {
    public static ArrayList<Post> posts = generateDummy();

    private static ArrayList<Post> generateDummy() {
        ArrayList<Post> posts = new ArrayList<>();

        posts.add(new Post("/storage/emulated/0/myPaintings/a.png", "this is caption"));
        posts.add(new Post("/storage/emulated/0/myPaintings/b.png", "this is caption too!"));
        return posts;
    }
}
