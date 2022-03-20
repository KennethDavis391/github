package com.reverse.postservice.services;

import com.reverse.postservice.models.dto.FullPost;
import com.reverse.postservice.models.dto.ImageReturnDto;
import com.reverse.postservice.models.dto.PostCreationDto;
import com.reverse.postservice.models.dto.PostImagesDto;
import com.reverse.postservice.repositories.LikeDao;
import com.reverse.postservice.repositories.dto.CommentDtoDao;
import com.reverse.postservice.repositories.dto.FullPostDao;
import com.reverse.postservice.repositories.dto.PostCreationRepo;
import com.reverse.postservice.repositories.dto.PostImagesDtoDao;
import com.reverse.postservice.tools.Log;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service(value = "PostDtoService")
public class PostDtoServiceImpl implements PostDtoService{

    private FullPostDao fullPostDao;
    private PostCreationRepo postCreationDao;
    private CommentDtoDao commentDao;
    private PostImagesDtoDao postImagesDao;
    private LikeDao likeDao;
    private ImageService imageService;

    @Autowired
    @Generated
    public PostDtoServiceImpl(FullPostDao fullPostDao, PostCreationRepo postCreationDao, CommentDtoDao commentDao, PostImagesDtoDao postImagesDao, LikeDao likeDao, ImageService imageService) {
        this.fullPostDao = fullPostDao;
        this.postCreationDao = postCreationDao;
        this.commentDao = commentDao;
        this.postImagesDao = postImagesDao;
        this.likeDao = likeDao;
        this.imageService = imageService;
    }

    /**
     * Retrieve a post by Id.
     * @param postId Post Id of the post to retrieve.
     * @return A FullPost which contains everything related to the post such as comments, likes, etc.
     */
    @Override
    public FullPost getPostById(int postId) {
        Log.getLog().debug("Getting post by id " + postId + " from getPostById in PostDtoServiceImpl.");

        Log.getLog().debug("Calling fullPostDao.findById from getPostById in PostDtoServiceImpl.");
        FullPost post = this.fullPostDao.findById(postId).get();
        Log.getLog().debug("fullPostDao.findById successful.");

        post.setLikes(this.likeDao.findAllUsersForPostId(postId)); //List of user ids who liked this post
        post.setComments(this.commentDao.findAllCommentsByPostId(postId));

        List<PostImagesDto> images = this.postImagesDao.findAllPostImagesByPostId(postId);
        List<ImageReturnDto> returnImages = new ArrayList<>();

        for(PostImagesDto image : images){
            ImageReturnDto returnImage = new ImageReturnDto();
            returnImage.setFilename(image.getImageTitle());
            returnImage.setUrl(image.getUrl());
            returnImages.add(returnImage);
        }

        post.setImages(returnImages);
        return post;
    }

    /**
     * Create a new post.
     * @param post Post to be created.
     */
    @Override
    public void createPost(PostCreationDto post) {
        Log.getLog().debug("Creating post from createPost in PostDtoServiceImpl.");

        //todo: Make a image service. Extract and save images on the post.
        post.setCreated(Instant.now());
        post.setLastEdited(Instant.now());
        Log.getLog().debug("Calling postCreationDao.save from createPost in PostDtoServiceImpl.");
        PostCreationDto saved = this.postCreationDao.save(post);
        if(post.getImages() != null) {
            imageService.addImagesToBucket(post.getImages(), (long) post.getPosterId(), saved.getId());
        }
        Log.getLog().debug("postCreationDao.save successful.");
    }

    /**
     * Update an existing post with new information.
     * @param post New post changes.
     */
    @Override
    public void updatePost(PostCreationDto post) {
        Log.getLog().debug("Updating post from updatePost in PostDtoServiceImpl.");

        //Can't update images on post. So don't extract or save the images.
        int id = post.getId();
        String title = post.getTitle();
        String body = post.getBody();
        Instant edited = Instant.now();

        Log.getLog().debug("Calling postCreationDao.updatePosts from updatePost in PostDtoServiceImpl.");
        this.postCreationDao.updatePosts(id, title, body, edited);
        Log.getLog().debug("postCreationDao.updatePosts successful.");
    }

}
