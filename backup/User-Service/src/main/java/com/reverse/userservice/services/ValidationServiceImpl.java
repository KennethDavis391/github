package com.reverse.userservice.services;

import com.reverse.userservice.exceptions.CredentialsInvalid;
import com.reverse.userservice.models.Credentials;
import com.reverse.userservice.models.ReverseJWT;
import com.reverse.userservice.models.User;
import com.reverse.userservice.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Slf4j
@Component("ValidationServiceImpl")
public class ValidationServiceImpl implements ValidationService{

    private UserRepository userRepo;

    @Value("${SECRET_KEY}")
    private String secret;

    @Autowired
    public ValidationServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public ReverseJWT validateCredentials(Credentials loginRequest) throws Exception {
        log.debug("validateCredentials called for username = "+loginRequest.getUsername());
        User query = this.userRepo.findByUsername(loginRequest.getUsername());
        if(query!=null) {
            log.trace("Found user");
            if(BCrypt.checkpw(loginRequest.getPassword(), query.getPassword())) {
                log.debug("Login succeeded for username ="+loginRequest.getUsername());
                return new ReverseJWT(query.getId(), this.secret);
            }
            log.trace("Bad password");
        }
        log.warn("Login attempt failed for username = "+loginRequest.getUsername());
        throw new CredentialsInvalid("Invalid Credentials!");
    }

    @Override
    public boolean validateJwt(ReverseJWT jwt) {
        log.debug("Validating JWT");
        Long userId = jwt.getUserID(secret);
        log.trace("JWT id = "+userId);
        if(userId != null && userRepo.findById(userId).isPresent()) {
            log.debug("JWT is valid");
            return true;
        }
        log.debug("JWT is invalid");
        return false;
    }

    @Override
    public boolean validateJwt(ReverseJWT jwt, long userID) {
        log.debug("Validating JWT with userID = "+userID);
        Long jwtID = jwt.getUserID(secret);
        log.trace("JWT id = "+jwtID);
        log.trace("userID = "+userID+" is present in userRepo: "+userRepo.findById(userID).isPresent());
        if(jwtID != null && jwtID.longValue() == userID && userRepo.findById(userID).isPresent()) {
            log.debug("JWT is valid");
            return true;
        }
        log.debug("JWT is invalid");
        return false;
    }

}