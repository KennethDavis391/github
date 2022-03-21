package com.reverse.postservice.repositories;

import com.reverse.postservice.models.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageDao extends JpaRepository<PostImage, Integer> {
}
