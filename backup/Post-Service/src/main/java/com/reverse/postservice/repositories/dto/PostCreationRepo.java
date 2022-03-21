package com.reverse.postservice.repositories.dto;

import com.reverse.postservice.models.dto.PostCreationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface PostCreationRepo extends JpaRepository<PostCreationDto, Integer> {

    /**
     * Used to update a post.
     * @param id Id of the post.
     * @param title Title of the post.
     * @param body Message body of the post.
     * @param edited Time when the post was edited.
     */
    @Modifying
    @Query(value = "UPDATE posts SET title= :title, body= :body, last_edited= :edited WHERE id= :id", nativeQuery = true)
    void updatePosts(@Param("id") int id, @Param("title") String title, @Param("body") String body, @Param("edited") Instant edited);
}
