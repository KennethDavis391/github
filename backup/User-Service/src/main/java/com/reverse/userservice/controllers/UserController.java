package com.reverse.userservice.controllers;

import com.reverse.userservice.controllers.postobjects.UserEdit;
import com.reverse.userservice.models.Credentials;
import com.reverse.userservice.models.ReverseJWT;
import com.reverse.userservice.models.User;
import com.reverse.userservice.services.UserService;
import com.reverse.userservice.services.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "users")
@Slf4j
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    @Setter
    private ValidationService valService;

    @Autowired
    @Setter
    private UserService userService;

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody User user) {
        log.debug("Creating new user");
        Long id = userService.createNewUser(user);
        log.debug("User with id "+id+" successfully created");
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> checkLoginCredentials(@RequestBody Credentials loginRequest) {
        log.debug("Login attempt from "+loginRequest.getUsername());
        try {
            ReverseJWT jwt = this.valService.validateCredentials(loginRequest);
            log.debug("Login success from "+loginRequest.getUsername());
            return ResponseEntity.ok().body(jwt);
        } catch (Exception e) {
            log.warn("Failed login for"+loginRequest.getUsername());
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping(value = "/validate")
    public ResponseEntity validateJwt(@RequestBody ReverseJWT jwt) {
        log.debug("Validating JWT");
        boolean status = this.valService.validateJwt(jwt);
        if(status) {
            log.debug("Validation successful");
            return ResponseEntity.ok().build();
        }
        log.warn("Validation failed");
        return ResponseEntity.status(401).build();
    }

	@PostMapping(path = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity editUser(@RequestBody UserEdit userEdit) {
        log.debug("Start editUser");
        ReverseJWT reverseJWT = userEdit.getReverseJWT();
        User user = userEdit.getUser();

        if (!(valService.validateJwt(reverseJWT, user.getId()))) {
            log.warn("Bad JWT sent to /users/edit");
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        if (userService.getUserByID(user.getId()) != null) {
            userService.updateUser(user);
            log.debug("User with id "+user.getId()+" successfully edited");
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            log.warn("User sent to /users/edit not found in db");
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }



    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable Long id) {
        log.debug("getUserByID called");
        User user = userService.getUserByID(id);
        if (user != null) {
            log.debug("User sent to /users/{id} with id "+user.getId()+" located successfully");
            return ResponseEntity.ok().body(user);
        } else {
            log.debug("User sent to /users/{id} with id "+id+" not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="/user/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username){
        log.debug("getUserByUsername called");
        User user = this.userService.getUserByUsername(username);
        if(user != null) {
            log.debug("User sent to /users/user/{username} with username "+user.getUsername()+" located successfully");
            return ResponseEntity.ok().body(user);
        } else {
            log.debug("User sent to /users/user/{username} with username "+username+" not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @Author: Alberto Figallo
     * */
    @PatchMapping(path = "/updateUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser(@RequestBody User user) {
        log.debug("Begin updateUser");
        List<String> availableFields = Arrays.asList("username","email","firstName","lastName","password","dateOfBirth","gender","branch","profilePicture");
        User userData = userService.getUserByID(user.getId());

        if (userData != null) {
            User updUser = userData;
            for (String field : availableFields) {
                log.trace("Current field = "+field);
                switch (field){
                    case "username": if (user.getUsername() != null) updUser.setUsername(user.getUsername()); break;
                    case "email": if (user.getEmail() != null) updUser.setEmail(user.getEmail()); break;
                    case "firstName": if (user.getFirstName() != null) updUser.setFirstName(user.getFirstName()); break;
                    case "lastName": if (user.getLastName() != null) updUser.setLastName(user.getLastName()); break;
                    case "password": if (user.getPassword() != null) updUser.setPassword(user.getPassword()); break;
                    case "dateOfBirth": if (user.getDateOfBirth() != null) updUser.setDateOfBirth(user.getDateOfBirth()); break;
                    case "gender": if (user.getGender() != null) updUser.setGender(user.getGender()); break;
                    case "branch": if (user.getBranch() != null) updUser.setBranch(user.getBranch()); break;
                    case "profilePicture": if (user.getProfilePicture() != null) updUser.setProfilePicture(user.getProfilePicture()); break;
                }
            }
            userService.updateUser(updUser);
            log.debug("Successfully updated user id = "+user.getId()+" username = "+user.getUsername());
            return new ResponseEntity(HttpStatus.OK);
        } else {
            log.warn("Did not find updatedUser id = "+user.getId()+" username = "+user.getUsername());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
