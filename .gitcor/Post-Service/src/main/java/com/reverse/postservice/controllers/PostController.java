package com.reverse.postservice.controllers;

import com.reverse.postservice.models.LikeId;
import com.reverse.postservice.models.Post;
import com.reverse.postservice.models.Like;
import com.reverse.postservice.models.dto.FullPost;
import com.reverse.postservice.models.dto.PostCreationDto;
import com.reverse.postservice.services.PostDtoService;
import com.reverse.postservice.services.PostService;
import com.reverse.postservice.tools.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* TODO:
*  When front-end retrieves a post, we need to return:
*       -A list of user IDs who liked the post.
*       -All comments on the post.
*       -All replies to each comment.
*
* TODO:
*  Add partial loading when retrieving a feed of posts.
*       -We should have it load in increments of 5, and continue to load in increments of 5
*        when a "load more" button is pressed.
* */

/**
 * The Post Controller handles requests related to creating, retrieving, liking, editing, and deleting posts.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/posts") //This being posts syncs with the current implementation of the gateway
public class PostController {

    PostService postService;
    PostDtoService postDtoService;
    ValidationUtils validationUtils;

    @Autowired
    @Lazy
    public PostController(PostService postService, @Qualifier("PostDtoService") PostDtoService postDtoService, ValidationUtils validationUtils) {
        this.postService = postService;
        this.postDtoService = postDtoService;
        this.validationUtils = validationUtils;
    }

    /**
     * Create a new post.
     * @param post Post object containing post details such as title, message body, poster, etc.
     * @return Represents the HTTP response.
     */
    @PostMapping(value = "/create")
    public ResponseEntity createPost(@RequestBody PostCreationDto post, @RequestHeader (name="Authorization") String token) {
        Log.getLog().debug("Creating post from createPost in PostController.");
        Log.getLog().debug("Authorization Token: " + token);

        try {
            Log.getLog().debug("Calling validationUtils.validateJwt from createPost in PostController.");
            validationUtils.validateJwt(token);
            Log.getLog().debug("validationUtils.validateJwt completed.");

            Log.getLog().debug("Calling postDtoService.createPost from createPost in PostController.");

            postDtoService.createPost(post);
            Log.getLog().debug("postDtoService.createPost successful.");
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            Log.getLog().fatal("Exception caught from createPost in PostController.");
            Log.getLog().fatal(e);
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieve a specific post by Id.
     * @param id The Id of the post.
     * @return Represents the HTTP response.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<FullPost> getPost(@PathVariable int id, @RequestHeader (name="Authorization") String token) {
        Log.getLog().debug("Getting post id " + id + " from getPost in PostController.");
        Log.getLog().debug("Authorization Token: " + token);

        try {
            Log.getLog().debug("Calling validationUtils.validateJwt from getPost in PostController.");
            validationUtils.validateJwt(token);
            Log.getLog().debug("validationUtils.validateJwt completed.");

            Log.getLog().debug("Calling postDtoService.getPostById from getPost in PostController.");
            FullPost post = this.postDtoService.getPostById(id);
            Log.getLog().debug("postDtoService.getPostById successful.");
            if(post != null) {
                Log.getLog().debug("Post not null, returning OK.");
                return ResponseEntity.ok().body(post);
            }

            Log.getLog().debug("Post is null, returning NOT_FOUND.");
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            Log.getLog().error("Exception caught from getPost in PostController.");
            Log.getLog().error(e);
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Like a post.
     * @param like A Like object containing the liked post id and the user's id of who liked the post.
     * @return Represents the HTTP response.
     */
    @PostMapping(value = "/like")
    public ResponseEntity likePost(@RequestBody Like like, @RequestHeader (name="Authorization") String token) {
        Log.getLog().debug("Liking post from likePost in PostController.");
        Log.getLog().debug("Authorization Token: " + token);

        try {
            Log.getLog().debug("Calling validationUtils.validateJwt from likePost in PostController.");
            validationUtils.validateJwt(token);
            Log.getLog().debug("validationUtils.validateJwt completed.");

            Log.getLog().debug("Calling postService.likePost from likePost in PostController.");
            postService.likePost(like);
            Log.getLog().debug("postService.likePost successful.");
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            Log.getLog().fatal("Exception caught from likePost in PostController.");
            Log.getLog().fatal(e);
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Edit an existing post.
     * @param post The post to be edited.
     * @return Represents the HTTP response.
     */
    @Transactional
    @PatchMapping(value = "/edit")
    public ResponseEntity editPost(@RequestBody PostCreationDto post, @RequestHeader (name="Authorization") String token) {
        Log.getLog().debug("Editing post from editPost in PostController.");
        Log.getLog().debug("Authorization Token: " + token);

        try {
            Log.getLog().debug("Calling validationUtils.validateJwt from editPost in PostController.");
            validationUtils.validateJwt(token);
            Log.getLog().debug("validationUtils.validateJwt completed.");

            Log.getLog().debug("Calling postDtoService.updatePost from editPost in PostController.");
            postDtoService.updatePost(post);
            Log.getLog().debug("postDtoService.updatePost successful.");
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            Log.getLog().fatal("Exception caught from editPost in PostController.");
            Log.getLog().fatal(e);
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete a specific post by Id.
     * @param id The Id of the post to delete.
     * @return Represents the HTTP response.
     */
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deletePost(@PathVariable int id, @RequestHeader (name="Authorization") String token) {
        Log.getLog().debug("Deleting post id " + id + " from deletePost in PostController.");
        Log.getLog().debug("Authorization Token: " + token);

        try {
            Log.getLog().debug("Calling validationUtils.validateJwt from deletePost in PostController.");
            validationUtils.validateJwt(token);
            Log.getLog().debug("validationUtils.validateJwt completed.");

            Log.getLog().debug("Calling postService.deletePost from deletePost in PostController.");
            postService.deletePost(id);
            Log.getLog().debug("postService.deletePost successful.");
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            Log.getLog().error("Exception caught from deletePost in PostController.");
            Log.getLog().error(e);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all posts from the database.
     * @return Represents the HTTP response.
     */
    @GetMapping()
    public ResponseEntity<List<Post>> getAllPosts(@RequestHeader (name="Authorization") String token){
        Log.getLog().debug("Getting all posts from getAllPosts in PostController.");
        Log.getLog().debug("Authorization Token: " + token);

        List<Post> posts;

        try {
            Log.getLog().debug("Calling validationUtils.validateJwt from getAllPosts in PostController.");
            validationUtils.validateJwt(token);
            Log.getLog().debug("validationUtils.validateJwt completed.");

            Log.getLog().debug("Calling postService.getAllPosts from getAllPosts in PostController.");
            posts = postService.getAllPosts();
            Log.getLog().debug("postService.getAllPosts successful.");
            return ResponseEntity.ok().body(posts);
        } catch (Exception e) {
            Log.getLog().error("Exception caught from getAllPosts in PostController.");
            Log.getLog().error(e);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get a specified number of recent posts from the database.
     * @return Represents the HTTP response.
     */
    @GetMapping("/recent/{number}")
    public ResponseEntity<List<Post>> getRecentPosts(@PathVariable(name="number") int number, @RequestHeader (name="Authorization") String token){
        Log.getLog().debug("Getting recent posts from getAllPosts in PostController.");
        Log.getLog().debug("Authorization Token: " + token);

        List<Post> posts;

        try {
            Log.getLog().debug("Calling validationUtils.validateJwt from getRecentPosts in PostController.");
            validationUtils.validateJwt(token);
            Log.getLog().debug("validationUtils.validateJwt completed.");

            Log.getLog().debug("Calling postService.getRecent from getRecentPosts in PostController.");
            posts = postService.getRecent(number);
            Log.getLog().debug("postService.getRecent successful.");
            return ResponseEntity.ok().body(posts);
        } catch (Exception e) {
            Log.getLog().error("Exception caught from getRecentPosts in PostController.");
            Log.getLog().error(e);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all of a specified user's posts from the database.
     * @return Represents the HTTP response.
     */
    @GetMapping("/byUser/{number}")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable(name="number") int userID, @RequestHeader (name="Authorization") String token){
        Log.getLog().debug("Getting all posts from getUserPosts in PostController.");
        Log.getLog().debug("Authorization Token: " + token);

        List<Post> posts;

        try {
            Log.getLog().debug("Calling validationUtils.validateJwt from getUserPosts in PostController.");
            validationUtils.validateJwt(token);
            Log.getLog().debug("validationUtils.validateJwt completed.");

            Log.getLog().debug("Calling postService.getUserPosts from getUserPosts in PostController.");
            posts = postService.getUserPosts(userID);
            Log.getLog().debug("postService.getUserPosts successful.");
            return ResponseEntity.ok().body(posts);
        } catch (Exception e) {
            Log.getLog().error("Exception caught from getUserPosts in PostController.");
            Log.getLog().error(e);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/unlike/{postId}/{userId}")
    public ResponseEntity unlike(@PathVariable(name="postId") int postId, @PathVariable(name="userId") int userId, @RequestHeader(name="Authorization") String token){
        Log.getLog().debug("Unliking post from likePost in PostController.");
        Log.getLog().debug("Authorization Token: " + token);

        try {
            Log.getLog().debug("Calling validationUtils.validateJwt from unlikePost in PostController.");
            validationUtils.validateJwt(token);
            Log.getLog().debug("validationUtils.validateJwt completed.");

            Log.getLog().debug("Calling postService.unlikePost from unlikePost in PostController.");
            Like toDelete = new Like(new LikeId(postId,userId));
            toDelete.setLikeId(new LikeId(postId,userId));
            postService.unlikePost(toDelete);
            Log.getLog().debug("postService.unlikePost successful.");
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            Log.getLog().fatal("Exception caught from unlikePost in PostController.");
            Log.getLog().fatal(e);
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
