package com.reverse.postservice.services;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.reverse.postservice.models.ImageLocation;
import com.reverse.postservice.models.Post;
import com.reverse.postservice.models.PostImage;
import com.reverse.postservice.models.dto.ImageDto;
import com.reverse.postservice.repositories.ImageDao;
import lombok.Setter;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService{

    private ImageDao imageDao;
    private PostService postService;

    @Autowired
    @Generated
    public void setImageDao(ImageDao dao, PostService postService){
        this.imageDao = dao;
        this.postService = postService;
    }


    @Override
    @Generated
    public List<PostImage> addImagesToBucket(List<ImageDto> imageDtos, Long userId, Integer postId) {

        //Note: none of this should be hardcoded
        //      sadly, it is. Fix later
        String bucketName = "reverse-social-media";
        ImageLocation bucket = new ImageLocation();
        bucket.setBucketName(bucketName);
        bucket.setId(1);

        //**IMPORTANT**//
        //The environment credentials provider expects the AWS_ACCESS_KEY and AWS_SECRET_KEY \
        // environment variables to be set.
        AmazonS3 amazonS3 = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new EnvironmentVariableCredentialsProvider())
                .withRegion(Regions.US_EAST_1)
                .build();

        List<PostImage> createdImages = new ArrayList<>();
        Long now = Instant.now().toEpochMilli();
        Post relatedPost = postService.getPostById(postId);

        int count = 0;
        for(ImageDto imgDto : imageDtos){

            //Create the name of the image in the bucket
            String imageName = userId+"-"+now+"-"+count+".png";

            //Convert byteString to InputStream
            byte[] bytes = Base64.getDecoder().decode(imgDto.getBytes());
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);

            //Create and set metadata
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentEncoding("base64");
            meta.setContentType("image/png");
            meta.setContentLength(bytes.length);
            meta.setLastModified(DateTime.now().toDate());

            //Upload data to S3
            amazonS3.putObject(bucketName, imageName, in, meta);

            // Add image to the database
            PostImage image = new PostImage();
            image.setPost(relatedPost);
            image.setImageTitle(imgDto.getImageTitle());
            image.setImageName(imageName);
            image.setBucket(bucket);

            PostImage img = imageDao.save(image);
            createdImages.add(img);

            count++;
        }

        return createdImages;

    }
}
