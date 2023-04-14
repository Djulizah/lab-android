package com.example.tugas4;

import java.util.ArrayList;
import java.util.Date;

public class UserDataSource {
    private static ArrayList<User> userList;

    public UserDataSource() {
        userList = new ArrayList<>();
        userList.add(new User(1, "Sana", "+62895306649209", "Shy shy shy", "December 20, 2022", R.drawable.sana));
        userList.add(new User(2, "Jihyo", "+6237387483784", "Busy", "February 28, 2022", R.drawable.jihyo));
        userList.add(new User(3, "Charlie Puth", "+6238832938838", "That's not how this works", "April 1, 2022", R.drawable.charlie_puth));
        userList.add(new User(4, "Nicole Zefanya", "+6283837283", "La la lost you", "May 10, 2022", R.drawable.nicole_zefanya));
        userList.add(new User(5, "Dewa", "+62373728733", "Risalah hati", "January 22, 2022", R.drawable.dewa));
        userList.add(new User(6, "Kobo Kanaeru", "+6273743845", "Bokobokobo", "August 29, 2022", R.drawable.kobo));
        userList.add(new User(7, "Chia", "+62666737384", "Anything you want", "March 8, 2022", R.drawable.chia));
        userList.add(new User(8, "Alex Turner", "+6284939484", "Body Paint", "June 11, 2022", R.drawable.alex_turner));
        userList.add(new User(9, "Saena", "+628392839892", "Cupid", "November 15, 2022", R.drawable.saena));
        userList.add(new User(10, "Jyp", "+6289393993999", "Gotta get", "April 24, 2022", R.drawable.jyp));
        userList.add(new User(11, "Nella Kharisma", "+62737373738", "Ditinggal rabi", "July 30, 2022", R.drawable.nella_kharisma));
        userList.add(new User(12, "Once Mekel", "+628989898989", "Dealova", "December 31, 2022", R.drawable.once_mekel));
        userList.add(new User(13, "Mahalini", "+6288999887373", "Melawan restu", "September 4, 2022", R.drawable.mahalini));
        userList.add(new User(14, "Diskoria", "+6289511149209", "CHRISYE", "October 10, 2022", R.drawable.diskoria));
    }

    public static ArrayList<User> getUserList() {
        return userList;
    }

    public User getUserById(int userId) {
        for (User user : userList) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }
}
