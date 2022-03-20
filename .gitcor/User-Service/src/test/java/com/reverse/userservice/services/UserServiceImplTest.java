package com.reverse.userservice.services;

import com.reverse.userservice.models.BranchLocation;
import com.reverse.userservice.models.Gender;
import com.reverse.userservice.models.ImageLocation;
import com.reverse.userservice.models.ProfilePicture;
import com.reverse.userservice.models.User;
import com.reverse.userservice.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    private UserServiceImpl testUserServiceImpl;

    private UserRepository mockUserRepository;

    @BeforeEach
    public void init() {
        mockUserRepository = mock(UserRepository.class);
        testUserServiceImpl = new UserServiceImpl();
        testUserServiceImpl.setUserRepository(mockUserRepository);
    }

    @Test
    public void getAllUsersTest() {
        List<User> mockList = mock(List.class);
        when(mockUserRepository.findAll()).thenReturn(mockList);

        List testList = testUserServiceImpl.getAllUsers();

        assertSame(mockList, testList, "mockList not returned!");
    }

    @Test
    public void getUserByIdSuccess() {
        long correctID = 42L;

        User mockUser = mock(User.class);

        Optional<User> testOptional = Optional.of(mockUser);

        when(mockUserRepository.findById(correctID)).thenReturn(testOptional);

        User testUser = testUserServiceImpl.getUserByID(correctID);

        assertSame(mockUser, testUser, "mockUser not returned!");
    }

    @Test
    public void getUserByIdFailure() {
        long correctID = 42L;

        User mockUser = null;
        Optional<User> testOptional = Optional.ofNullable(mockUser);

        when(mockUserRepository.findById(correctID)).thenReturn(testOptional);

        User testUser = testUserServiceImpl.getUserByID(correctID);

        assertNull(testUser, "testUser not null!");
    }

    @Test
    public void getUserByUsernameTest() {
        String username = "username";

        User mockUser = mock(User.class);

        when(mockUserRepository.findByUsername(username)).thenReturn(mockUser);

        User testUser = testUserServiceImpl.getUserByUsername(username);

        assertSame(mockUser, testUser, "mockUser not returned!");
    }

    @Test
    void testCreateNewUser() {
        BranchLocation branchLocation = new BranchLocation();
        branchLocation.setCountry("GB");
        branchLocation.setId(123L);
        branchLocation.setCity("Oxford");
        branchLocation.setBranchName("janedoe/featurebranch");
        branchLocation.setState("MD");

        Gender gender = new Gender();
        gender.setId(123L);
        gender.setGender("Gender");

        ImageLocation imageLocation = new ImageLocation();
        imageLocation.setId(123L);
        imageLocation.setBucketName("bucket-name");

        ProfilePicture profilePicture = new ProfilePicture();
        profilePicture.setId(123L);
        profilePicture.setImageLocation(imageLocation);
        profilePicture.setImageName("Image Name");

        User user = new User();
        user.setLastName("Doe");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        user.setBranch(branchLocation);
        user.setGender(gender);
        user.setId(123L);
        user.setProfilePicture(profilePicture);
        user.setDateOfBirth(null);
        user.setFirstName("Jane");
        when(this.userRepository.save((User) any())).thenReturn(user);

        BranchLocation branchLocation1 = new BranchLocation();
        branchLocation1.setCountry("GB");
        branchLocation1.setId(123L);
        branchLocation1.setCity("Oxford");
        branchLocation1.setBranchName("janedoe/featurebranch");
        branchLocation1.setState("MD");

        Gender gender1 = new Gender();
        gender1.setId(123L);
        gender1.setGender("Gender");

        ImageLocation imageLocation1 = new ImageLocation();
        imageLocation1.setId(123L);
        imageLocation1.setBucketName("bucket-name");

        ProfilePicture profilePicture1 = new ProfilePicture();
        profilePicture1.setId(123L);
        profilePicture1.setImageLocation(imageLocation1);
        profilePicture1.setImageName("Image Name");

        User user1 = new User();
        user1.setLastName("Doe");
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");
        user1.setBranch(branchLocation1);
        user1.setGender(gender1);
        user1.setId(123L);
        user1.setProfilePicture(profilePicture1);
        user1.setDateOfBirth(null);
        user1.setFirstName("Jane");
        assertEquals(123L, this.userServiceImpl.createNewUser(user1).longValue());
        verify(this.userRepository).save((User) any());
        assertTrue(this.userServiceImpl.getAllUsers().isEmpty());
    }

    // Skipping createNewUser due to work being done on it. TODO - Add test

    @Test
    public void updateUser() {
        User mockUser = mock(User.class);

        testUserServiceImpl.updateUser(mockUser);

        verify(mockUserRepository).save(mockUser);
    }

}