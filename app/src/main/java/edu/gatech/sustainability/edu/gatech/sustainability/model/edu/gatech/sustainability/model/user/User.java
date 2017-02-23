package edu.gatech.sustainability.edu.gatech.sustainability.model.edu.gatech.sustainability.model.user;

import edu.gatech.sustainability.MainActivity;

/**
 * Created by Paul on 2/22/2017.
 */

public class User {

    private int userId;
    private String username;
    private String email;
    private String password;

    private UserType type;

    private String homeAddress;
    private String phoneNumber;


    /**
     * Default no-arg constructor
     */
    public User() {

    }

    /**
     * Create a user with a predetermined user ID based on user map size. Non-persistent
     * @param username Username to register
     * @param email Email
     * @param password Password
     */
    public User(String username, String email, String password, UserType type) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
        this.userId = MainActivity.userSet.size() + 1;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public int getUserId() {
        return this.userId;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHomeAddress() {
        return homeAddress == null ? "" : homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber == null ? "" : phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserType getType() { return this.type; }

    public void setType(UserType type) { this.type = type; }

}
