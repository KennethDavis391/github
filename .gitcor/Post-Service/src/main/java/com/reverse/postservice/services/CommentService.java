package com.reverse.postservice.services;

import com.reverse.postservice.models.Comment;
import com.reverse.postservice.models.dto.CommentCreationDto;

import java.util.List;

public interface CommentService {

    /**
     * Post a comment to a post.
     * @param comment Comment to be posted.
     */
    void commentOnPost(CommentCreationDto comment);

    /**
     * Delete a comment from a post.
     * @param commentId Comment to be deleted (by Id).
     */
    void deleteComment(int commentId);

    /**
     * Retrieve all comments from a post.
     * @param postId Id of the post to retrieve comments from.
     * @return A list of comments from the specified post.
     */
    List<Comment> getAllCommentsOnPost(int postId);
}
