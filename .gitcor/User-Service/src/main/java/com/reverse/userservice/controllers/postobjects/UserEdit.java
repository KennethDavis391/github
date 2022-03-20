package com.reverse.userservice.controllers.postobjects;

import com.reverse.userservice.models.ReverseJWT;
import com.reverse.userservice.models.User;
import lombok.Data;

/**
 * Stores an attempt at the editing of a user.
 */
@Data
public class UserEdit {
    private ReverseJWT reverseJWT;
    private User user;
}