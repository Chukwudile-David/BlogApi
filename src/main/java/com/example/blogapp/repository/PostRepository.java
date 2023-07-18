package com.example.blogapp.repository;

import com.example.blogapp.dto.request.PostRequestDto;
import com.example.blogapp.entities.Post;
import com.example.blogapp.entities.SuperUser;
import com.example.blogapp.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findBySuperUser(SuperUser superUser);
    List <Post> findAllByCategory(Category category);
    List<Post> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

}
