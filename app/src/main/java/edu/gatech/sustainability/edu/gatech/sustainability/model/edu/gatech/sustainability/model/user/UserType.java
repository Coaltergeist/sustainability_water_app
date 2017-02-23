package edu.gatech.sustainability.edu.gatech.sustainability.model.edu.gatech.sustainability.model.user;

/**
 * Created by Paul on 2/22/2017.
 */

public enum UserType {
    NORMALUSER("User"),
    WORKER("Worker"),
    MANAGER("Manager"),
    ADMINISTRATOR("Admin");

    private final String type;

    private UserType(String type) {
        this.type = type;
    }
}
