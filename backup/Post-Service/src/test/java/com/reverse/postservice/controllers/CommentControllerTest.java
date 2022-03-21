package com.reverse.postservice.controllers;

import com.reverse.postservice.exceptions.InvalidJwtException;
import com.reverse.postservice.models.Comment;
import com.reverse.postservice.models.dto.CommentCreationDto;
import com.reverse.postservice.services.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CommentControllerTest {

    private CommentController testCommentController;
    private CommentService mockCommentService;
    private ValidationUtils mockValidationUtils;

    @BeforeEach
    public void init() {
        mockCommentService = mock(CommentService.class);
        mockValidationUtils = mock(ValidationUtils.class);
        testCommentController = new CommentController(mockCommentService, mockValidationUtils);
    }

    @Test
    public void commentOnPostSucceedTest() {
        CommentCreationDto mockComment = mock(CommentCreationDto.class);

        ResponseEntity response = testCommentController.commentOnPost(mockComment, "");
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void commentOnPostFailTest() {
        CommentCreationDto mockComment = mock(CommentCreationDto.class);

        when(testCommentController.commentOnPost(mockComment, "")).thenThrow(new NullPointerException());

        ResponseEntity response = testCommentController.commentOnPost(mockComment, "");
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void deleteCommentSucceedTest() {
        ResponseEntity response = testCommentController.deleteComment(1, "");
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void deleteCommentFailTest() {
        when(testCommentController.deleteComment(1, "")).thenThrow(new NullPointerException());
        ResponseEntity response = testCommentController.deleteComment(1, "");
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void getAllCommentsOnPostSucceedTest() {
        Comment mockComment = mock(Comment.class);

        when(mockCommentService.getAllCommentsOnPost(1)).thenReturn(Arrays.asList(mockComment));
        ResponseEntity<List<Comment>> response = testCommentController.getAllCommentsOnPost(1, "");
        assertEquals(response.getBody().get(0), mockComment);
    }

    @Test
    public void getAllCommentsOnPostFailTest() {
        when(mockCommentService.getAllCommentsOnPost(1)).thenReturn(new ArrayList<>());
        ResponseEntity response = testCommentController.getAllCommentsOnPost(1, "");
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void getAllCommentsOnPostValidationFailTest() {
        try {
            doThrow(new InvalidJwtException("invalid jwt")).when(mockValidationUtils).validateJwt("");
            ResponseEntity response = testCommentController.getAllCommentsOnPost(1, "");
            assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {

        }
    }
}
