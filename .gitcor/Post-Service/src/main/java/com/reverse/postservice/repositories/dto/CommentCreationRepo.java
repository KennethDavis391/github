package com.reverse.postservice.repositories.dto;

import com.reverse.postservice.models.dto.CommentCreationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentCreationRepo extends JpaRepository<CommentCreationDto, Integer> {
}
