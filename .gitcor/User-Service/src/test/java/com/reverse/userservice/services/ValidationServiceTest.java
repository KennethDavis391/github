package com.reverse.userservice.services;

import com.reverse.userservice.exceptions.CredentialsInvalid;
import com.reverse.userservice.models.Credentials;
import com.reverse.userservice.models.ReverseJWT;
import com.reverse.userservice.models.User;
import com.reverse.userservice.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ValidationServiceTest {

    private String secret = "g9vcBtznWr+HpLBjBi3IW/cOOd8gjnJYJ32ftiNDpBBPtcHO3ac/4IiK4eCz8x4xlEH0o6E53tS8UVOSQyY+yg==";

    private ValidationService validationServiceTest;
    private UserRepository mockRepo;

    @BeforeEach
    public void init() {
        this.mockRepo = mock(UserRepository.class);
        this.validationServiceTest = new ValidationServiceImpl(this.mockRepo);
        this.validationServiceTest.setSecret(this.secret);
    }

    @Test
    public void validateCredentialsTest() throws Exception{
        User user = new User();
        user.setId(10L);
        user.setUsername("test");
        user.setPassword("$2a$10$XL3q/vz6yI46hl/6kJQXyuEAQ0B.8tGEjO221CbJlaR.74opT90tW");
        Credentials credentials = new Credentials("test", "test");

        when(mockRepo.findByUsername("test")).thenReturn(user);

        ReverseJWT jwt = validationServiceTest.validateCredentials(credentials);

        assertNotNull(jwt.getToken());
    }

    @Test
    public void validateCredentialsTestFail() throws Exception{
        User user = new User();
        user.setId(10L);
        user.setUsername("test");
        user.setPassword("$2a$10$XL3q/vz6yI46hl/6kJQXyuEAQ0B.8tGEjO221CbJlaR.74opT90tW");
        Credentials credentials = new Credentials("test", "wrong");

        when(mockRepo.findByUsername("test")).thenReturn(user);

        assertThrows(CredentialsInvalid.class, () -> {
            ReverseJWT jwt = validationServiceTest.validateCredentials(credentials);
        });
    }

    @Test void validateJwtTestSuccess() {
        User mockUser = mock(User.class);
        when(mockRepo.findById(10L)).thenReturn(Optional.of(mockUser));

        ReverseJWT jwt = new ReverseJWT(10L, this.secret);
        boolean status = this.validationServiceTest.validateJwt(jwt);
        assertTrue(status);
    }

    @Test
    void validateJwtTestFailureBadJwt() {
        User mockUser = mock(User.class);
        when(mockRepo.findById(10L)).thenReturn(Optional.of(mockUser));

        ReverseJWT jwt2 = new ReverseJWT(null, this.secret);
        boolean failure = this.validationServiceTest.validateJwt(jwt2);
        assertFalse(failure);
    }

    @Test
    void validateJwtTestFailureBadUser() {
        User mockUser = mock(User.class);
        when(mockRepo.findById(10L)).thenReturn(Optional.empty());

        ReverseJWT jwt2 = new ReverseJWT(10L, this.secret);
        boolean failure = this.validationServiceTest.validateJwt(jwt2);
        assertFalse(failure);
    }

    @Test void validateJwtTest2Success() {
        User mockUser = mock(User.class);
        long correctID = 10L;
        when(mockRepo.findById(correctID)).thenReturn(Optional.of(mockUser));

        ReverseJWT jwt = new ReverseJWT(correctID, this.secret);
        boolean status = this.validationServiceTest.validateJwt(jwt, correctID);
        assertTrue(status);
    }

    @Test void validateJwtTest2FailureBadJwt() {
        User mockUser = mock(User.class);
        long correctID = 10L;
        when(mockRepo.findById(correctID)).thenReturn(Optional.of(mockUser));

        ReverseJWT jwt = new ReverseJWT(null, this.secret);
        boolean status = this.validationServiceTest.validateJwt(jwt, correctID);
        assertFalse(status);
    }

    @Test void validateJwtTest2FailureBadLong() {
        User mockUser = mock(User.class);
        long correctID = 10L;
        when(mockRepo.findById(correctID)).thenReturn(Optional.of(mockUser));

        ReverseJWT jwt = new ReverseJWT(correctID, this.secret);
        boolean status = this.validationServiceTest.validateJwt(jwt, 9L);
        assertFalse(status);
    }

    @Test void validateJwtTest2FailureBadUser() {
        User mockUser = mock(User.class);
        long correctID = 10L;
        when(mockRepo.findById(correctID)).thenReturn(Optional.empty());;

        ReverseJWT jwt = new ReverseJWT(correctID, this.secret);
        boolean status = this.validationServiceTest.validateJwt(jwt, correctID);
        assertFalse(status);
    }
}
