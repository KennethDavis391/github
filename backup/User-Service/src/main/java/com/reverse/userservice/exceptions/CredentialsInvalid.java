package com.reverse.userservice.exceptions;

public class CredentialsInvalid extends RuntimeException{
    public CredentialsInvalid(String message) {
        super(message);
    }
}