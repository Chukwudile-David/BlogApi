package com.example.blogapp.repository;

import com.example.blogapp.dto.request.BlogUserRequestDto;
import com.example.blogapp.entities.BlogUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class BlogUserRepositoryTest {
    @Autowired
    private BlogUserRepository blogUserRepository;


    @Test
    public void GivenBlogUserdto_WhenSave_ReturnBloguser(){
        //Given
        BlogUser blogUser = new BlogUser(1L,"Optadave","1234");

        //when
        BlogUser actual = blogUserRepository.save(blogUser);

        //Then
        assertNotNull(actual);

    }
    @Test
    public void GivenBlogUserId_WhenfindById_ReturnObject(){
        Long id = 1L;
        BlogUser blogUser = new BlogUser(1L,"Optadave","1234");

        blogUserRepository.save(blogUser);
        Optional<BlogUser> bloguser = blogUserRepository.findById(id);

        String actual = "Optadave";
        String expected = bloguser.get().getUsername();

        assertEquals(actual,expected);

    }
    @Test
    public void GivenBlogUserUsernameAndPassword_WhenfindByUsernameandPassword_ReturnBloguser(){
        String username = "Optadave";
        String password = "1234";
        BlogUser blogUser = new BlogUser(1L,"Optadave","1234");

        blogUserRepository.save(blogUser);
        Optional<BlogUser> User = blogUserRepository.findByUsernameAndPassword(username, password);
        String actualusername = "Optadave";
        String actualpassword = "1234";
        String expectedusername = "Optadave";
        String expectedpassword = "1234";

        assertEquals(actualusername,expectedusername);
        assertEquals(actualpassword,expectedpassword);
    }

    @BeforeEach
    void setUp() {
    }
}