package com.example.blogapp.repository;

import com.example.blogapp.entities.BlogUser;
;
import com.example.blogapp.entities.SuperUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BlogUserRepository extends JpaRepository<BlogUser,Long> {
    Optional<BlogUser> findByUsername(String Username);
    Optional<BlogUser> findByUsernameAndPassword(String email, String password);
}
