package model;

import com.example.inigaram.R;

public class User {
    private static User instance;
    private String fullName;
    private String userName;
    private int profilePicture;

    public User(String fullName, String userName, int profilePicture) {
        this.fullName = fullName;
        this.userName = userName;
        this.profilePicture = profilePicture;
    }

    public static synchronized User getInstance() {
        if (instance == null) {
            instance = new User("Spongebob SquarePants", "Spongebob", R.drawable.spongebob);
        }
        return instance;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserName() {
        return userName;
    }

    public int getProfilePicture() {
        return profilePicture;
    }

}
