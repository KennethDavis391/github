package com.reverse.postservice.repositories;

import com.reverse.postservice.models.Post;
import com.reverse.postservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostDao extends JpaRepository<Post, Integer> {
    List<Post> findAllByBodyNotNullOrderByLastEdited();
    List<Post> findAllByBodyNotNullOrderByCreatedDesc();

    List<Post> findAllByPoster_IdOrderByCreated(int userID);
}
