package com.reverse.postservice.models;

import org.junit.jupiter.api.Test;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.*;

public class ModelsTest {
    String testString = "Test";
    int testNum = 1;
    Instant testTime = Instant.now();
    User testUser = new User();
    Gender testGender = new Gender(1,"Male");
    BranchLocation testBranch = new BranchLocation(testNum,testString,testString,testString,testString);
    LikeId testLikeId = new LikeId(testNum,testNum);
    ImageLocation testImageLocation = new ImageLocation(testNum,testString);
    Post testPost = new Post(testNum,testUser,testString,testString,testTime,testTime);

    @Test
    void branchLocationTest(){
        BranchLocation branchLocation = new BranchLocation();

        branchLocation.setBranchName(testString);
        assertEquals(testString,branchLocation.getBranchName());

        branchLocation.setId(testNum);
        assertEquals(testNum,branchLocation.getId());

        branchLocation.setCity(testString);
        assertEquals(testString,branchLocation.getCity());

        branchLocation.setCountry(testString);
        assertEquals(testString,branchLocation.getCountry());

        branchLocation.setState(testString);
        assertEquals(testString,branchLocation.getState());

        BranchLocation allArgsBranch = new BranchLocation(testNum,testString,testString,testString,testString);
        assertEquals(allArgsBranch.getBranchName(),branchLocation.getBranchName());
        assertEquals(allArgsBranch.getId(),branchLocation.getId());
        assertEquals(allArgsBranch.getCity(),branchLocation.getCity());
        assertEquals(allArgsBranch.getCountry(),branchLocation.getCountry());
        assertEquals(allArgsBranch.getState(),branchLocation.getState());
    }

    @Test
    void commentTest(){
        Comment comment = new Comment();
        Comment allArgs = new Comment(testNum,testPost,testUser,testString,testTime);

        comment.setId(testNum);
        assertEquals(testNum, comment.getId());
        assertEquals(testNum, allArgs.getId());

        comment.setCommenter(testUser);
        assertEquals(testUser, comment.getCommenter());
        assertEquals(testUser, allArgs.getCommenter());

        comment.setCreated(testTime);
        assertEquals(testTime, comment.getCreated());
        assertEquals(testTime, allArgs.getCreated());

        comment.setMessage(testString);
        assertEquals(testString, comment.getMessage());
        assertEquals(testString, allArgs.getMessage());

        comment.setPost(testPost);
        assertEquals(testPost, comment.getPost());
        assertEquals(testPost, allArgs.getPost());

    }

    @Test
    void genderTest(){
        Gender gender = new Gender();
        Gender allArgs = new Gender(testNum,testString);

        gender.setId(testNum);
        assertEquals(testNum,gender.getId());
        assertEquals(testNum,allArgs.getId());

        gender.setGender(testString);
        assertEquals(testString,gender.getGender());
        assertEquals(testString,allArgs.getGender());
    }

    @Test
    void imageLocationTest(){
        ImageLocation imageLocation = new ImageLocation();
        ImageLocation allArgs = new ImageLocation(testNum,testString);

        imageLocation.setId(testNum);
        assertEquals(testNum,imageLocation.getId());
        assertEquals(testNum,allArgs.getId());

        imageLocation.setBucketName(testString);
        assertEquals(testString,imageLocation.getBucketName());
        assertEquals(testString,allArgs.getBucketName());
    }
  
    @Test
    void likeTest(){
        Like like = new Like();
        Like allArgs = new Like(testLikeId);

        like.setLikeId(testLikeId);
        assertEquals(testLikeId,like.getLikeId());
        assertEquals(testLikeId,allArgs.getLikeId());
    }

    @Test
    void likeIdTest(){
        LikeId likeId = new LikeId();
        LikeId allArgs = new LikeId(testNum,testNum);

        likeId.setUserId(testNum);
        assertEquals(testNum,likeId.getUserId());
        assertEquals(testNum,allArgs.getUserId());

        likeId.setPostId(testNum);
        assertEquals(testNum,likeId.getPostId());
        assertEquals(testNum,allArgs.getPostId());
    }

    @Test
    void postTest(){
        Post post = new Post();

        post.setId(testNum);
        assertEquals(testNum,post.getId());

        post.setLastEdited(testTime);
        assertEquals(testTime,post.getLastEdited());

        post.setPoster(testUser);
        assertSame(testUser,post.getPoster());

        post.setBody(testString);
        assertEquals(testString,post.getBody());

        post.setTitle(testString);
        assertEquals(testString,post.getTitle());

        post.setCreated(testTime);
        assertEquals(testTime,post.getCreated());

        Post allArgsPost = new Post(testNum,testUser,testString,testString,testTime,testTime);
        assertEquals(allArgsPost.getId(),post.getId());
        assertEquals(allArgsPost.getLastEdited(),post.getLastEdited());
        assertSame(allArgsPost.getPoster(),post.getPoster());
        assertEquals(allArgsPost.getBody(),post.getBody());
        assertEquals(allArgsPost.getTitle(),post.getTitle());
        assertEquals(allArgsPost.getCreated(),post.getCreated());
    }


    @Test
    void userTest(){
        User currentUser = new User();
        User allArgsUser = new User(testNum,testString,testString,testString,testString,testTime,testGender,testBranch,null);

        currentUser.setId(testNum);
        assertEquals(testNum,currentUser.getId());
        assertEquals(testNum,allArgsUser.getId());

        currentUser.setGender(testGender);
        assertEquals(testGender,currentUser.getGender());
        assertEquals(testGender,allArgsUser.getGender());

        currentUser.setEmail(testString);
        assertEquals(testString,currentUser.getEmail());
        assertEquals(testString,allArgsUser.getEmail());

        currentUser.setDateOfBirth(testTime);
        assertEquals(testTime,currentUser.getDateOfBirth());
        assertEquals(testTime,allArgsUser.getDateOfBirth());

        currentUser.setUsername(testString);
        assertEquals(testString,currentUser.getUsername());
        assertEquals(testString,allArgsUser.getUsername());

        currentUser.setBranch(testBranch);
        assertEquals(testBranch,currentUser.getBranch());
        assertEquals(testBranch,allArgsUser.getBranch());

        currentUser.setFirstName(testString);
        assertEquals(testString,currentUser.getFirstName());
        assertEquals(testString,allArgsUser.getFirstName());

        currentUser.setLastName(testString);
        assertEquals(testString,currentUser.getLastName());
        assertEquals(testString,allArgsUser.getLastName());

        currentUser.setProfilePicture(null);
        assertNull(currentUser.getProfilePicture());
        assertNull(allArgsUser.getProfilePicture());
    }
}
