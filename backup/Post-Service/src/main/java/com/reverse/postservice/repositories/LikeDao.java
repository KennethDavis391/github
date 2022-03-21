package com.reverse.postservice.repositories;

import com.reverse.postservice.models.Like;
import com.reverse.postservice.models.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeDao extends JpaRepository<Like, Integer> {
    Long countByLikeId_PostId(int id);

    @Query(value = "SELECT user_id FROM user_likes where post_id = :id", nativeQuery = true)
    List<Integer> findAllUsersForPostId(@Param("id") int postId);
}
