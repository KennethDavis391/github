package com.reverse.postservice.repositories.dto;

import com.reverse.postservice.models.dto.FullPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FullPostDao extends JpaRepository<FullPost, Integer> {
}
