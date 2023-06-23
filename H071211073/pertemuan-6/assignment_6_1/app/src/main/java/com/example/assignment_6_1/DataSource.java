package com.example.assignment_6_1;

import java.util.ArrayList;

public class DataSource {

    public static ArrayList<Post> posts = generateDummy();
    private static ArrayList<Post> generateDummy() {
        ArrayList<Post> posts = new ArrayList<>();

        posts.add(new Post("marchtheseventh","finally got the new camera, will have lots of pics coming!",
                "https://i.pinimg.com/564x/c2/51/be/c251bed0a8c0ee280d821f0d6be40505.jpg",
                "https://i.pinimg.com/564x/f8/08/8f/f8088f214e01c8af83815f0f4af1edfd.jpg"));
        posts.add(new Post("serval.official", "Upcoming concert next thursday! Get ready everyone something's a brewin'!! ",
                "https://i.pinimg.com/564x/58/e2/6d/58e26d594aa7e4157b408a248d883b20.jpg",
                "https://static.wikia.nocookie.net/houkai-star-rail/images/2/2d/Character_Serval_Eidolon_4.png/revision/latest?cb=20230505153824"));
        posts.add(new Post("wolf_s", "GET REKT LOLLLLLLLLLLL",
                "https://i.pinimg.com/564x/48/2f/1d/482f1d8c4edf90d9df4ed3dd0dea3bb6.jpg",
                "https://i.pinimg.com/564x/b4/2f/14/b42f14692ef4312b39284c26199ce11b.jpg"));
        posts.add(new Post("koski.shop", "BEST DEALS!! GET YOUR BRAND NEW ITEM SUPER GOOD DEALS!!! click link here now!!!!!",
                "https://i.pinimg.com/564x/04/c9/68/04c968a63fbacf9c55047d7262f707b4.jpg",
                "https://i.pinimg.com/736x/83/48/d2/8348d21347317eaac303b8c4bc7fa83b.jpg"));
        return posts;
    }

//    public static ArrayList<User> users = _generateUsers();

    public static ArrayList<User> generateUsers() {
        ArrayList<User> users = new ArrayList<>();

        users.add(new User("March 7th","marchtheseventh",
                "https://i.pinimg.com/564x/c2/51/be/c251bed0a8c0ee280d821f0d6be40505.jpg"));
        users.add(new User("Serval Landau", "serval.official",
                "https://i.pinimg.com/564x/58/e2/6d/58e26d594aa7e4157b408a248d883b20.jpg"));
        users.add(new User("Silver Wolf", "wolf_s",
                "https://i.pinimg.com/564x/48/2f/1d/482f1d8c4edf90d9df4ed3dd0dea3bb6.jpg"));
        users.add(new User("Sampo Koski", "koski.shop",
                "https://i.pinimg.com/564x/04/c9/68/04c968a63fbacf9c55047d7262f707b4.jpg"));
        return users;
    }

}

