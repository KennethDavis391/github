package com.reverse.userservice.services;


import com.reverse.userservice.models.Credentials;
import com.reverse.userservice.models.ReverseJWT;

/**
 * Handles validation of ReverseJWT objects.
 */
public interface ValidationService {

    /**
     * Sets the secret key used to create and validate JWTs. This will cause existing JWTs to fail to authenticate.
     * @param secret The new secret key.
     */
    void setSecret(String secret);

    /**
     * Validates a username password combo held in a Credentials object and returns a ReverseJWT if valid.
     * @param loginRequest Stores the login credentials.
     * @return Returns a new valid ReverseJWT.
     * @throws Exception Throws an InvalidCredentials exception if Credentials are rejected.
     */
    ReverseJWT validateCredentials(Credentials loginRequest) throws Exception;

    /**
     * Checks if a ReverseJWT is valid.
     * @param jwt The ReverseJWT to validate.
     * @return true if valid, false if invalid.
     */
    boolean validateJwt(ReverseJWT jwt);

    /**
     * Checks if a ReverseJWT is valid and has the expected userID.
     * @param jwt The ReverseJWT to validate.
     * @param userID The expected userID.
     * @return true if valid, false if invalid.
     */
    boolean validateJwt(ReverseJWT jwt, long userID);
}
