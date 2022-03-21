package com.reverse.postservice.controllers;

import com.reverse.postservice.models.Comment;
import com.reverse.postservice.models.dto.CommentCreationDto;
import com.reverse.postservice.services.CommentService;
import com.reverse.postservice.tools.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The Comment Controller handles requests related to comments on posts such as adding, deleting, and retrieving.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/comments")
public class CommentController {

    CommentService commentService;
    ValidationUtils validationUtils;

    @Autowired
    public CommentController(CommentService commentService, ValidationUtils validationUtils) {
        this.commentService = commentService;
        this.validationUtils = validationUtils;
    }

    /**
     * Will add a comment to a post.
     * @param comment The comment object containing details of the comment.
     * @return Represents the HTTP response.
     */
    @PostMapping(value = "/comment")
    public ResponseEntity commentOnPost(@RequestBody CommentCreationDto comment, @RequestHeader (name="Authorization") String token) {
        Log.getLog().debug("Commenting on post from commentOnPost in CommentController.");
        Log.getLog().debug("Authorization Token: " + token);

        try {
            Log.getLog().debug("Calling validationUtils.validateJwt from commentOnPost in CommentController.");
            validationUtils.validateJwt(token);
            Log.getLog().debug("validationUtils.validateJwt completed.");

            Log.getLog().debug("Calling commentService.commentOnPost from commentOnPost in CommentController.");
            commentService.commentOnPost(comment);
            Log.getLog().debug("commentService.commentOnPost successful.");
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            Log.getLog().fatal("Exception caught from commentOnPost in CommentController.");
            Log.getLog().fatal(e);
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Delete a comment from a post.
     * @param id The Id of the comment.
     * @return Represents the HTTP response.
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteComment(@PathVariable int id, @RequestHeader (name="Authorization") String token) {
        Log.getLog().debug("Deleting comment id " + id + " from deleteComment in CommentController.");
        Log.getLog().debug("Authorization Token: " + token);

        try {
            Log.getLog().debug("Calling validationUtils.validateJwt from deleteComment in CommentController.");
            validationUtils.validateJwt(token);
            Log.getLog().debug("validationUtils.validateJwt completed.");

            Log.getLog().debug("Calling commentService.deleteComment from deleteComment in CommentController.");
            commentService.deleteComment(id);
            Log.getLog().debug("commentService.deleteComment successful.");
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            Log.getLog().fatal("Exception caught from deleteComment in CommentController.");
            Log.getLog().fatal(e);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieves all comments on a single post.
     * @param id The Id of the post.
     * @return Represents the HTTP response.
     */
    @GetMapping(value = "/post/{id}")
    public ResponseEntity<List<Comment>> getAllCommentsOnPost(@PathVariable int id, @RequestHeader (name="Authorization") String token) {
        Log.getLog().debug("Getting all comments on post id " + id + " from getAllCommentsOnPost in CommentController.");
        Log.getLog().debug("Authorization Token: " + token);

        try {
            Log.getLog().debug("Calling validationUtils.validateJwt from getAllCommentsOnPost in CommentController.");
            validationUtils.validateJwt(token);
            Log.getLog().debug("validationUtils.validateJwt completed.");

            Log.getLog().debug("Calling commentService.getAllCommentsOnPost from getAllCommentsOnPost in CommentController.");
            List<Comment> comments = commentService.getAllCommentsOnPost(id);
            Log.getLog().debug("commentService.getAllCommentsOnPost successful.");

            if(comments != null) {
                Log.getLog().debug("Comments not null.");
                if(comments.size() > 0) {
                    Log.getLog().debug("Comments size > 0, returning OK response.");
                    return ResponseEntity.ok().body(comments);
                }
                Log.getLog().debug("Comments size <= 0.");
            }
            Log.getLog().debug("Returning NOT_FOUND response.");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            Log.getLog().error("Exception caught from getAllCommentsOnPost in CommentController.");
            Log.getLog().error(e);
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }
}
