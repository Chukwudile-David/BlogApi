package com.example.blogapp.repository;

import com.example.blogapp.entities.BlogUser;
import com.example.blogapp.entities.Comment;
import com.example.blogapp.entities.Post;
import com.example.blogapp.entities.SuperUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByBlogUser(BlogUser blogUser);
    List<Comment> findByBlogUserAndPost(BlogUser blogUser, Post post);
}
