package com.example.blogapp.repository;

import com.example.blogapp.dto.request.PostRequestDto;
import com.example.blogapp.entities.Post;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Configuration
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    public void GivenPost_WhenSave_ThenReturnObject(){

        Post heyyy = Post.builder().title("heyyy").content("234455").build();
        //When
        Post actual = postRepository.save(heyyy);

        //Then
        assertNotNull(actual);


    }



    @BeforeEach
    void setUp() {
    }
}