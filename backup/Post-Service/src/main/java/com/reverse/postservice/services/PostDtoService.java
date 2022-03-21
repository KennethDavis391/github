package com.reverse.postservice.services;

import com.reverse.postservice.models.dto.FullPost;
import com.reverse.postservice.models.dto.PostCreationDto;
public interface PostDtoService {

    /**
     * Retrieve a post by Id.
     * @param postId Post Id of the post to retrieve.
     * @return A FullPost which contains everything related to the post such as comments, likes, etc.
     */
    FullPost getPostById(int postId);

    /**
     * Create a new post.
     * @param post Post to be created.
     */
    void createPost(PostCreationDto post);

    /**
     * Update an existing post with new information.
     * @param post New post changes.
     */
    void updatePost(PostCreationDto post);
}
