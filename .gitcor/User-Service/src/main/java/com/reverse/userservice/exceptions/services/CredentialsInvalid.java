package com.reverse.userservice.exceptions.services;

public class CredentialsInvalid extends RuntimeException{
    public CredentialsInvalid(String message) {
        super(message);
    }
}
