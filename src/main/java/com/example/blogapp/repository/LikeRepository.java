package com.example.blogapp.repository;

import com.example.blogapp.entities.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes,Long> {

}
