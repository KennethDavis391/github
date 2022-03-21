package com.reverse.postservice.services;

import com.reverse.postservice.models.Post;
import com.reverse.postservice.models.Like;
import com.reverse.postservice.models.User;
import com.reverse.postservice.repositories.CommentDao;
import com.reverse.postservice.repositories.LikeDao;
import com.reverse.postservice.repositories.PostDao;
import com.reverse.postservice.tools.Log;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service(value = "PostService")
public class PostServiceImpl implements PostService{

    PostDao postDao;
    LikeDao likeDao;
    CommentDao commentDao;

    @Autowired
    @Generated
    public PostServiceImpl(PostDao postDao, LikeDao likeDao, CommentDao commentDao) {
        this.postDao = postDao;
        this.likeDao = likeDao;
        this.commentDao = commentDao;
    }

    /**
     * Create a new post.
     * @param post Post to be created.
     */
    @Override
    public void createPost(Post post) {
        Log.getLog().debug("Creating post from createPost in PostServiceImpl.");

        post.setCreated(Instant.now());
        post.setLastEdited(Instant.now());
        Log.getLog().debug("Calling postDao.save from createPost in PostServiceImpl.");
        this.postDao.save(post);
        Log.getLog().debug("postDao.save successful.");
    }

    /**
     * Retrieve a specific post by Id.
     * @param postId Post Id of the post to retrieve.
     * @return The specified post retrieved by Id.
     */
    @Override
    public Post getPostById(int postId) {
        Log.getLog().debug("Getting post by id " + postId + " from getPostById in PostServiceImpl.");

        Log.getLog().debug("Calling postDao.findById from getPostById in PostServiceImpl.");
        Optional<Post> p = this.postDao.findById(postId);
        Log.getLog().debug("postDao.findById successful.");
        if(p.isPresent()) {
            Log.getLog().debug("Post is present.");
            Log.getLog().debug("Calling postDao.findById from getPostById in PostServiceImpl.");
            Post p2 = this.postDao.findById(postId).get();
            Log.getLog().debug("postDao.findById successful.");
            return p2;
        }

        Log.getLog().debug("No post is present, returning null.");
        return null;
    }

    /**
     * Like a post.
     * @param like A Like object which contains the post Id to be liked and the user Id of the user who liked the post.
     */
    @Override
    public void likePost(Like like) {
        Log.getLog().debug("Liking post from likePost in PostServiceImpl.");

        Log.getLog().debug("Calling likeDao.save from likePost in PostServiceImpl.");
        this.likeDao.save(like);
        Log.getLog().debug("likeDao.save successful.");
    }

    @Override
    public void unlikePost(Like like) {
        Log.getLog().debug("Unliking post from unlikePost in PostServiceImpl.");

        Log.getLog().debug("Calling likeDao.save from unlikePost in PostServiceImpl.");
        this.likeDao.delete(like);
        Log.getLog().debug("likeDao.delete successful.");
    }


    /**
     * Update a post with new information.
     * @param post New post information.
     */
    @Override
    public void updatePost(Post post) {
        Log.getLog().debug("Updating post from updatePost in PostServiceImpl.");

        post.setLastEdited(Instant.now());
        Log.getLog().debug("Calling postDao.save from updatePost in PostServiceImpl.");
        this.postDao.save(post);
        Log.getLog().debug("postDao.save successful.");
    }

    /**
     * Delete a post by Id.
     * @param postId Id of the post to delete.
     */
    @Override
    public void deletePost(int postId) {
        Log.getLog().debug("Deleting post from deletePost in PostServiceImpl.");

        Log.getLog().debug("Calling postDao.deleteById from deletePost in PostServiceImpl.");
        this.postDao.deleteById(postId);
        Log.getLog().debug("postDao.deleteById successful.");
    }

    /**
     * Retrieve all posts from the database.
     * @return A list of posts retrieved.
     */
    @Override
    public List<Post> getAllPosts(){
        Log.getLog().debug("Getting all posts from getAllPosts in PostServiceImpl.");

        Log.getLog().debug("Calling postDao.findAll from deletePost in PostServiceImpl.");
        List<Post> posts = postDao.findAll();
        Log.getLog().debug("postDao.findAll successful.");

        return posts;
    }

    /**
     * Gets a personalized set of posts for a specific user
     * @param userId the ID of the user whose experience is being personalized
     * @return a list of posts customized based on some criteria
     */
    @Override
    public List<Post> getPostFeed(int userId){
        Log.getLog().debug("Getting a post feed from getPostFeed in PostServiceImpl for user id " + userId + ".");

        Log.getLog().debug("Calling getAllPosts from deletePost in PostServiceImpl.");
        List<Post> posts = getAllPosts();
        Log.getLog().debug("getAllPosts successful.");

        return posts;
    }


    /**
     * Get the most recently created posts.
     * @param number how many posts to return
     * @return A list of posts.
     */
    public List<Post> getRecent(int number){
        List<Post> posts = postDao.findAllByBodyNotNullOrderByCreatedDesc();
        return posts.size() >= number?posts.subList(0,number):posts;
    }

    @Override
    public List<Post> getUserPosts(int userID) {
        return postDao.findAllByPoster_IdOrderByCreated(userID);
    }
}
