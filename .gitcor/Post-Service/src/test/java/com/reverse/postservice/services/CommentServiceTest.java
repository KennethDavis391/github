package com.reverse.postservice.services;

import com.reverse.postservice.models.Post;
import com.reverse.postservice.models.User;
import com.reverse.postservice.models.dto.CommentCreationDto;
import com.reverse.postservice.repositories.CommentDao;
import com.reverse.postservice.repositories.dto.CommentCreationRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CommentServiceTest {

    private CommentService testCommentService;
    private CommentDao mockCommentDao;

    @BeforeEach
    public void init() {
        mockCommentDao = mock(CommentDao.class);
        CommentCreationRepo mockCommentCreationRepo = mock(CommentCreationRepo.class);
        testCommentService = new CommentServiceImpl(mockCommentDao, mockCommentCreationRepo);
    }

    @Test
    public void commentOnPostSucceedTest() {
        User mockUser = new User();
        mockUser.setId(1);
        Post mockPost = new Post();
        mockPost.setId(1);

        CommentCreationDto mockComment = new CommentCreationDto();
//        mockComment.setPost(mockPost);
//        mockComment.setCommenter(mockUser);


        testCommentService.commentOnPost(mockComment);
    }

    @Test
    public void deleteCommentTest() {
        int id = 1;
        testCommentService.deleteComment(id);
        verify(mockCommentDao).deleteById(1);
    }

    @Test
    public void getAllCommentsOnPostTest() {
        int id = 1;
        testCommentService.getAllCommentsOnPost(id);
        verify(mockCommentDao).getCommentsByPost_Id(id);
    }
}
