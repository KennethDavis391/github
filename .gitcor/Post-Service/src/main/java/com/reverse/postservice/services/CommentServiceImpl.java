package com.reverse.postservice.services;

import com.reverse.postservice.models.Comment;
import com.reverse.postservice.models.dto.CommentCreationDto;
import com.reverse.postservice.repositories.CommentDao;
import com.reverse.postservice.repositories.dto.CommentCreationRepo;
import com.reverse.postservice.tools.Log;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service("CommentService")
public class CommentServiceImpl implements CommentService{

    private CommentDao commentDao;
    private CommentCreationRepo commentCreationRepo;

    @Autowired
    @Generated
    public CommentServiceImpl(CommentDao commentDao, CommentCreationRepo commentCreationRepo) {
        this.commentDao = commentDao;
        this.commentCreationRepo = commentCreationRepo;
    }

    /**
     * Post a comment to a post.
     * @param comment Comment to be posted.
     */
    @Override
    public void commentOnPost(CommentCreationDto comment) {
        Log.getLog().debug("Commenting on post from commentOnPost in CommentServiceImpl.");

        comment.setCreated(Instant.now());
        Log.getLog().debug("Calling commentCreationRepo.save from commentOnPost in CommentServiceImpl.");
        this.commentCreationRepo.save(comment);
        Log.getLog().debug("commentCreationRepo.save successful.");
    }

    /**
     * Delete a comment from a post.
     * @param commentId Comment to be deleted (by Id).
     */
    @Override
    public void deleteComment(int commentId) {
        Log.getLog().debug("Deleting comment id " + commentId + " from deleteComment in CommentServiceImpl.");
        Log.getLog().debug("Calling commentDao.deleteById from deleteComment in CommentServiceImpl.");
        commentDao.deleteById(commentId);
        Log.getLog().debug("commentDao.deleteById successful.");
    }

    /**
     * Retrieve all comments from a post.
     * @param postId Id of the post to retrieve comments from.
     * @return A list of comments from the specified post.
     */
    @Override
    public List<Comment> getAllCommentsOnPost(int postId) {
        Log.getLog().debug("Getting all comments on post id " + postId + " from getAllCommentsOnPost in CommentServiceImpl.");
        Log.getLog().debug("Calling commentDao.getCommentsByPost_Id from getAllCommentsOnPost in CommentServiceImpl.");
        List<Comment> comments = commentDao.getCommentsByPost_Id(postId);
        Log.getLog().debug("commentDao.getCommentsByPost_Id successful.");
        return comments;
    }

}
