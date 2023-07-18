package com.example.blogapp.repository;

import com.example.blogapp.entities.SuperUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SuperUserRepository extends JpaRepository<SuperUser,Long> {
    Optional<SuperUser> findByUsername(String Username);
    Optional<SuperUser> findByUsernameAndPassword(String email, String password);

}
