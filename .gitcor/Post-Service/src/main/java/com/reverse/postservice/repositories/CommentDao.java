package com.reverse.postservice.repositories;

import com.reverse.postservice.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao extends JpaRepository<Comment, Integer> {
    /**
     * Retrieve comments from a post based on post Id.
     * @param postId The post Id to retrieve comments from.
     * @return A list of comments from the post.
     */
    List<Comment> getCommentsByPost_Id(int postId);
}
