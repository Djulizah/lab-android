package com.example.tugas5;

import java.util.ArrayList;
import java.util.Arrays;

public class DataSource {
    public static ArrayList<User> users = new ArrayList<>(
            Arrays.asList(
                    new User("Monkey D. Luffy", R.drawable.luffy,  "Luffy", new Add("lorem",R.drawable.luffy )),
                    new User("Roronoa Zoro", R.drawable.luffy, "Zoro", new Add("lorem", R.drawable.luffy)),
                    new User("Siti Nami", R.drawable.luffy, "Nami", new Add("lorem", R.drawable.luffy))
            )
    );
}
