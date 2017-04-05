package edu.gatech.sustainability.model.user;

/**
 * Created by Paul on 2/22/2017.
 */

public class User {

    private String userId;
    public String name;
    public String email;
    private String password;

    public UserType userType;

    public String address;
    public String phoneNumber;


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
    public User(String userId, String username, String email, String password, UserType type) {
        this.name = username;
        this.email = email;
        this.password = password;
        this.userType = type;
        this.userId =  userId;
    }

    public String getUsername() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUserId() {
        return this.userId;
    }
    public void setUsername(String username) {
        this.name = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHomeAddress() {
        return address == null ? "" : address;
    }

    public void setHomeAddress(String homeAddress) {
        this.address = homeAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber == null ? "" : phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserType getUserType() { return this.userType; }

    public void setUserType(UserType userType) { this.userType = userType; }

    public void setUserId(String s) {
        this.userId = s;
    }
}
