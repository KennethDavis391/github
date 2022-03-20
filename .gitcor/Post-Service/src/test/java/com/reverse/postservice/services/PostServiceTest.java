package com.reverse.postservice.services;

import com.reverse.postservice.models.*;
import com.reverse.postservice.repositories.CommentDao;
import com.reverse.postservice.repositories.LikeDao;
import com.reverse.postservice.repositories.PostDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PostServiceTest {

    private PostService testPostService;
    private PostDao mockPostDao;
    private LikeDao mockLikeDao;
    private CommentDao mockCommentDao;

    @BeforeEach
    public void init() {
        mockPostDao = mock(PostDao.class);
        mockCommentDao = mock(CommentDao.class);
        mockLikeDao = mock(LikeDao.class);
        testPostService = new PostServiceImpl(mockPostDao, mockLikeDao, mockCommentDao);

    }

    @Test
    void createPostTest(){
        Post testPost = new Post();
        testPost.setTitle("Test");
        testPost.setBody("This is a test of the post creation system");

        User testUser = new User();
        testUser.setId(1);
        assertEquals(1,testUser.getId());

        testPost.setPoster(testUser);

        testPostService.createPost(testPost);
    }

    @Test
    void getPostByIdTest(){
        testPostService.getPostById(1);
    }

    @Test
    void likePostTest(){
        Like like = new Like();

        LikeId likeId = new LikeId(1,1);
        like.setLikeId(likeId);

        testPostService.likePost(like);
    }

    @Test
    void updatePostTest(){
        Post testPost = new Post();
        testPost.setId(1);
        testPost.setTitle("Test");
        testPost.setBody("This is a test of the post update system");
        testPost.setLastEdited(Instant.now());

        testPostService.updatePost(testPost);
    }

    @Test
    void deletePostTest(){
        testPostService.deletePost(1);
    }

    @Test
    void getAllPostsTest(){
        testPostService.getAllPosts();
    }

    @Test
    void getFeedTest(){
        testPostService.getPostFeed(1);
    }


    @Test
    void getPostById() {
        Post mockPost = mock(Post.class);

        when(mockPostDao.findById(1)).thenReturn(java.util.Optional.ofNullable(mockPost));
        Post post = testPostService.getPostById(1);
        assertSame(mockPost, post);
    }

    @Test
    void getRecent() {
        List<Post> posts = testPostService.getRecent(1);
        assertEquals(0, posts.size());
    }

    @Test
    void createPost() {
    }

    @Test
    void updatePost() {
    }

    @Test
    void unlikePostSuccess(){
        Like like = new Like(new LikeId(1,1));
        testPostService.unlikePost(like);
        verify(mockLikeDao).delete(like);
    }
}
