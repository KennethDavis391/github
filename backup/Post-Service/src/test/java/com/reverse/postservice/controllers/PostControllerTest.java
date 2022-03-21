package com.reverse.postservice.controllers;

import com.reverse.postservice.exceptions.InvalidJwtException;
import com.reverse.postservice.models.Like;
import com.reverse.postservice.models.LikeId;
import com.reverse.postservice.models.dto.FullPost;
import com.reverse.postservice.models.dto.PostCreationDto;
import com.reverse.postservice.services.PostDtoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.reverse.postservice.models.Post;
import com.reverse.postservice.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

public class PostControllerTest {

    private PostController testPostController;

    private PostService mockPostService;
    private PostDtoService mockPostDtoService;
    private ValidationUtils mockValidationUtils;

    @BeforeEach
    public void init() {
        mockPostService = mock(PostService.class);
        mockPostDtoService = mock(PostDtoService.class);
        mockValidationUtils = mock(ValidationUtils.class);

        testPostController = new PostController(mockPostService, mockPostDtoService, mockValidationUtils);
    }

    @Test
    public void createPostSucceedTest() {
        PostCreationDto post = mock(PostCreationDto.class);

        ResponseEntity response = testPostController.createPost(post, "");
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void createPostFailTest() {
        PostCreationDto post = mock(PostCreationDto.class);

        when(testPostController.createPost(post, "")).thenThrow(new NullPointerException());

        ResponseEntity response = testPostController.createPost(post, "");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void getPostSucceedTest() {
        FullPost post = mock(FullPost.class);

        when(mockPostDtoService.getPostById(1)).thenReturn(post);
        ResponseEntity<FullPost> response = testPostController.getPost(1, "");

        assertEquals(post, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getPostFailTest() {
        when(mockPostDtoService.getPostById(1)).thenReturn(null);
        ResponseEntity<FullPost> response = testPostController.getPost(1, "");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getPostValidationFailTest() {
        try {
            doThrow(new InvalidJwtException("Invalid jwt")).when(mockValidationUtils).validateJwt("");
            ResponseEntity response = testPostController.getPost(1, "");
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        } catch (Exception e) {

        }
    }

    @Test
    public void likePostSucceedTest() {
        Like like = mock(Like.class);

        ResponseEntity response = testPostController.likePost(like, "");

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void likePostFailTest() {
        Like like = mock(Like.class);

        when(testPostController.likePost(like, "")).thenThrow(new NullPointerException());
        ResponseEntity response = testPostController.likePost(like, "");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void editPostSucceedTest() {
        PostCreationDto post = mock(PostCreationDto.class);

        ResponseEntity response = testPostController.editPost(post, "");

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void editPostFailTest() {
        PostCreationDto post = mock(PostCreationDto.class);

        when(testPostController.editPost(post, "")).thenThrow(new NullPointerException());
        ResponseEntity response = testPostController.editPost(post, "");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void deletePostSucceedTest() {
        ResponseEntity response = testPostController.deletePost(1, "");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deletePostFailTest() {
        when(testPostController.deletePost(1, "")).thenThrow(new NullPointerException());

        ResponseEntity response = testPostController.deletePost(1, "");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getAllPostsSucceedTest() {
        Post post = mock(Post.class);

        when(mockPostService.getAllPosts()).thenReturn(Arrays.asList(post));
        ResponseEntity<List<Post>> response = testPostController.getAllPosts("");

        assertEquals(post, response.getBody().get(0));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getAllPostsFailTest() {
        when(testPostController.getAllPosts("")).thenThrow(new NullPointerException());
        ResponseEntity<List<Post>> response = testPostController.getAllPosts("");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getRecentPostsSuccessTest(){
        Post post = mock(Post.class);

        when(mockPostService.getRecent(1)).thenReturn(Collections.singletonList(post));
        ResponseEntity<List<Post>> response = testPostController.getRecentPosts(1,"");

        assertEquals(post, response.getBody().get(0));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getRecentPostsFailTest(){
        when(testPostController.getRecentPosts(1,"")).thenThrow(new NullPointerException());
        ResponseEntity<List<Post>> response = testPostController.getRecentPosts(1,"");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getUserPostsSuccessTest(){
        Post post = mock(Post.class);

        when(mockPostService.getUserPosts(1)).thenReturn(Collections.singletonList(post));
        ResponseEntity<List<Post>> response = testPostController.getUserPosts(1,"");

        assertEquals(post, response.getBody().get(0));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getUserPostsFailTest(){
        when(testPostController.getUserPosts(1,"")).thenThrow(new NullPointerException());
        ResponseEntity<List<Post>> response = testPostController.getUserPosts(1,"");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void unlikePostSucceedTest() {
        Like like = mock(Like.class);
        like.setLikeId(new LikeId(1,1));

        testPostController.unlike(1,1, "");
        verify(mockPostService).unlikePost(any());
    }
}
