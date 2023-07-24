package com.example.blogapp.repository;

import com.example.blogapp.entities.BlogUser;
import com.example.blogapp.entities.Comment;
import com.example.blogapp.entities.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;
    @Test
    public void GivenComment_WhenSave_ReturnObject(){
        Post post = new Post();
        Comment comment = new Comment(1L,"biggg", LocalDateTime.now(),LocalDateTime.now(),post);

        Comment save = commentRepository.save(comment);
        assertNotNull(save);

    }

    @Test
    public  void GivenId_Whenfindbyid_ReturnObject(){
        Long id = 1L;
        Post post = new Post();
        Comment comment = new Comment(1L,"biggg", LocalDateTime.now(),LocalDateTime.now(),post);

        commentRepository.save(comment);
        Optional<Comment> byId = commentRepository.findById(id);
        String actual = comment.getDescription();
        String expected = byId.get().getDescription();

        assertEquals(actual,expected);

    }
    @Test
    public void GivenBloguserandpost_WhenfindByBloguserandPost_returnobject(){


    }

    @BeforeEach
    void setUp() {
    }
}