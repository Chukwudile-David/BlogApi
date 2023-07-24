package com.example.blogapp.repository;

import com.example.blogapp.entities.BlogUser;
import com.example.blogapp.entities.Likes;
import com.example.blogapp.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes,Long> {
    boolean existsByPostAndUser(Post post, BlogUser user);


}
