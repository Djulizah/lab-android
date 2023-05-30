package com.example.inigaram.datasources;

import com.example.inigaram.R;
import com.example.inigaram.model.User;

import java.util.ArrayList;

public class UserDataSource {
    private static ArrayList<User> userArrayList;
    public UserDataSource() {
        userArrayList = new ArrayList<>();
        userArrayList.add(new User(0, "Spongebob Squarepants", "spongebob", R.drawable.spongebob));
        userArrayList.add(new User(1, "Sana", "sana", R.drawable.sana));
        userArrayList.add(new User(2, "Jihyo", "jihyo", R.drawable.jihyo));
        userArrayList.add(new User(3, "Charlie Puth", "charlie", R.drawable.charlie_puth));
        userArrayList.add(new User(4, "Nicole Zefanya", "niki", R.drawable.nicole_zefanya));
        userArrayList.add(new User(5, "Dewa", "dewa", R.drawable.dewa));
        userArrayList.add(new User(6, "Kobo Kanaeru", "kobo", R.drawable.kobo));
        userArrayList.add(new User(7, "Chia", "chia", R.drawable.chia));
        userArrayList.add(new User(8, "Alex Turner", "alex", R.drawable.alex_turner));
        userArrayList.add(new User(9, "Saena", "+saena", R.drawable.saena));
        userArrayList.add(new User(10, "Jyp", "jyp", R.drawable.jyp));
        userArrayList.add(new User(11, "Once Mekel", "once", R.drawable.once_mekel));
        userArrayList.add(new User(12, "Mahalini", "mahalini", R.drawable.mahalini));
    }

    public static ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    public static User getUserById(int id) {
        for (User user : userArrayList) {
            if (user.getUserId() == id) {
                return user;
            }
        }

        return null;
    }
}
