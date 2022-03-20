package com.reverse.userservice.services;

import com.reverse.userservice.models.Credentials;
import com.reverse.userservice.models.User;
import com.reverse.userservice.repositories.UserRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component("UserServiceImpl")
public class UserServiceImpl implements UserService{

    @Autowired
    @Setter
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        log.debug("Returning all users");
        return userRepository.findAll();
    }

    @Override
    public User getUserByID(Long id) {
        log.debug("Get user by id "+id);
        Optional<User> user = this.userRepository.findById(id);
        if(user.isPresent()){
            log.debug("Found user "+id);
            return user.get();
        } else {
            log.debug("User "+id+" not found");
            return null;
        }
    }

    @Override
    public User getUserByUsername(String username) {
        log.debug("Getting user by username "+username);
        return this.userRepository.findByUsername(username);
    }

    @Override
    public Long createNewUser(User user) {
        log.debug("Creating new user id = "+user.getId()+" username = "+user.getUsername());
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        log.trace("Password for user "+user.getId()+" hashed successfully");
        User newUser = this.userRepository.save(user);
        log.debug("Created new user "+user.getId());
        return newUser.getId();
    }

    @Override
    public void updateUser(User user) {
        log.debug("UserService updating user id = "+user.getId()+" username = "+user.getUsername());
        this.userRepository.save(user);
    }

}
