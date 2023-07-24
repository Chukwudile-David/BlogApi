package com.example.blogapp.repository;

import com.example.blogapp.entities.SuperUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class SuperUserRepositoryTest {

    @Autowired
    private SuperUserRepository superUserRepository;

    @Test
    public void GivenAdmin_WhenSave_ReturnAdmnin(){
        SuperUser superUser = new SuperUser(1L,"Optadave","1234");
        SuperUser actual = superUserRepository.save(superUser);
        assertNotNull(actual);
    }

    @Test
    public void GivenId_WhenfindById_ReturnAdmin(){
        Long id = 1L;
        SuperUser superUser = new SuperUser(1L,"Optadave","1234");
        superUserRepository.save(superUser);
        Optional<SuperUser> saveduser = superUserRepository.findById(id);
        String actual = superUser.getUsername();
        String expected = saveduser.get().getUsername();
        assertEquals(actual,expected);


    }

    @Test
    void findByUsername() {
    }

    @Test
    void findByUsernameAndPassword() {
        String username = "Optadave";
        String password = "1234";
        SuperUser superUser = new SuperUser(1L,"Optadave","1234");
        superUserRepository.save(superUser);
        Optional<SuperUser> UsernameAndPassword = superUserRepository.findByUsernameAndPassword(username, password);
        String expectedusername = UsernameAndPassword.get().getUsername();
        String expectedpassword = UsernameAndPassword.get().getPassword();


        assertEquals(username,expectedusername);
        assertEquals(password,expectedpassword);
    }

    @BeforeEach
    void setUp() {
    }


}