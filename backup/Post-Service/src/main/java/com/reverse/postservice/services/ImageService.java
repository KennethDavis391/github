package com.reverse.postservice.services;

import com.reverse.postservice.models.PostImage;
import com.reverse.postservice.models.dto.ImageDto;

import java.util.List;

public interface ImageService {

    public List<PostImage> addImagesToBucket(List<ImageDto> imageDtos, Long userId, Integer postId);

}
