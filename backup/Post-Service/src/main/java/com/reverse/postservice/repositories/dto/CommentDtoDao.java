package com.reverse.postservice.repositories.dto;

import com.reverse.postservice.models.dto.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDtoDao extends JpaRepository<CommentDto, Integer> {
    /**
     * Retrieve all comments from a specific post.
     * @param postId The post Id to retrieve comments from.
     * @return A list of CommentDtos.
     */
    List<CommentDto> findAllCommentsByPostId(int postId);


}
