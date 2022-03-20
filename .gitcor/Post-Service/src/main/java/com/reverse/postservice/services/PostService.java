package com.reverse.postservice.services;

import com.reverse.postservice.models.Post;
import com.reverse.postservice.models.Like;
import com.reverse.postservice.models.User;

import java.util.List;

public interface PostService {

    /**
     * Create a new post.
     * @param post Post to be created.
     */
    void createPost(Post post);

    /**
     * Retrieve a specific post by Id.
     * @param postId Post Id of the post to retrieve.
     * @return The specified post retrieved by Id.
     */
    Post getPostById(int postId);

    /**
     * Like a post.
     * @param like A Like object which contains the post Id to be liked and the user Id of the user who liked the post.
     */
    void likePost(Like like);

    /**
     * Unlike a post.
     * @param like A Like object which contains the post Id to be unliked and the user Id of the user who unliked the post.
     */
    void unlikePost(Like like);

    /**
     * Update a post with new information.
     * @param post New post information.
     */
    void updatePost(Post post);

    /**
     * Delete a post by Id.
     * @param postId Id of the post to delete.
     */
    void deletePost(int postId);

    /**
     * Retrieve all posts from the database.
     * @return A list of posts retrieved.
     */
    List<Post> getAllPosts();

    /**
     * Get a feed of posts for a user.
     * @param userId The user to get the post feed for.
     * @return A list of posts.
     */
    List<Post> getPostFeed(int userId);

    /**
     * Get the most recently created posts.
     * @param number how many posts to include
     * @return A list of posts.
     */
    List<Post> getRecent(int number);

    /**
     * Gets all of a user's posts
     * @return
     */
    List<Post> getUserPosts(int userID);
}
