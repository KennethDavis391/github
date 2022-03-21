package com.reverse.userservice.services;

import com.reverse.userservice.models.User;

import java.util.List;


public interface UserService {

    /**
     * Returns a list of all the usesrs.
     * @return List of all current users.
     */
    List<User> getAllUsers();

    /**
     * Returns the user with this username, null if no user exists.
     * @param username The username to search for.
     * @return The User with the input username, null if none.
     */
    User getUserByUsername(String username);

    /**
     * Updates the User
     * @param user The updated User
     */
    void updateUser(User user);

    /**
     * Returns the user with this id, null if no user exists.
     * @param id The id to search for.
     * @return The User with the input id, null if none.
     */
    public User getUserByID(Long id);

    /**
     * Creates a new User and returns the id.
     * @param newUser The new User.
     * @return The id of the new user.
     */
    Long createNewUser(User newUser);

}
