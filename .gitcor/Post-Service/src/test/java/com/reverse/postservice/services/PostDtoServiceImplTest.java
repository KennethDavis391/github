package com.reverse.postservice.services;

import com.reverse.postservice.models.Comment;
import com.reverse.postservice.models.Like;
import com.reverse.postservice.models.Post;
import com.reverse.postservice.models.dto.*;
import com.reverse.postservice.repositories.LikeDao;
import com.reverse.postservice.repositories.dto.CommentDtoDao;
import com.reverse.postservice.repositories.dto.FullPostDao;
import com.reverse.postservice.repositories.dto.PostCreationRepo;
import com.reverse.postservice.repositories.dto.PostImagesDtoDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class PostDtoServiceImplTest {

    private static PostDtoService postDtoService;
    private static FullPostDao fullPostDao;
    private static PostCreationRepo postCreationDao;
    private static CommentDtoDao commentDao;
    private static PostImagesDtoDao postImagesDao;
    private static LikeDao likeDao;
    private static ImageService imageService;

    @BeforeAll
    static void setUp() {
        fullPostDao = mock(FullPostDao.class);
        postCreationDao = mock(PostCreationRepo.class);
        commentDao = mock(CommentDtoDao.class);
        postImagesDao = mock(PostImagesDtoDao.class);
        likeDao = mock(LikeDao.class);
        imageService = mock(ImageService.class);

        postDtoService = new PostDtoServiceImpl(fullPostDao, postCreationDao, commentDao, postImagesDao, likeDao,imageService);

    }

    @Test
    void getPostById() {

        CommentDto comment = new CommentDto();
        List<CommentDto> commentList = new ArrayList<>();
        commentList.add(comment);

        ImageReturnDto postImage = new ImageReturnDto();
        PostImagesDto fullPostImage = new PostImagesDto();

        List<ImageReturnDto> postImagesDtoList = new ArrayList<>();
        postImagesDtoList.add(postImage);
        List<PostImagesDto> fullPostImageList = new ArrayList<>();
        fullPostImageList.add(fullPostImage);

        FullPost post = new FullPost();
        post.setComments(commentList);
        post.setImages(postImagesDtoList);

        when(fullPostDao.findById(1)).thenReturn(java.util.Optional.of(post));
        when(likeDao.countByLikeId_PostId(1)).thenReturn(12L);
        when(commentDao.findAllCommentsByPostId(1)).thenReturn(commentList);
        when(postImagesDao.findAllPostImagesByPostId(1)).thenReturn(fullPostImageList);

        FullPost postRetrieved = postDtoService.getPostById(1);
        assertEquals(post, postRetrieved);
    }

    @Test
    void createPost() {
        PostCreationDto postDto = new PostCreationDto();
        postDto.setId(1);
        postDto.setTitle("Title");
        postDto.setBody("Body");
        postDtoService.createPost(postDto);
        assertNotNull(postCreationDao.findById(1));
    }

    @Test
    void updatePost() {
        PostCreationDto postDto = new PostCreationDto();
        postDto.setId(1);
        postDto.setTitle("Title");
        postDto.setBody("Body");
        postDtoService.updatePost(postDto);
        assertNotNull(postCreationDao.findById(1));
    }
}