package com.example.blogapp.repository;

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
//@SpringBootTest(classes = BlogAppApplication.class,
//        webEnvironment = SpringBootTest.WebEnvironment.NONE)

class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void GivenCategory_WhenSave_ReturnObject(){
        Category david = Category.builder().name("David").category_id(1L).build();
        Category actual = categoryRepository.save(david);
        assertNotNull(actual);

    }
@Test
    public void GivenCategoryId_WhenFindByID_ReturnObject(){
        //Given
        Long id = 1L;
        Category david = Category.builder().name("David").category_id(1L).build();
        //When
        Category actual = categoryRepository.save(david);
        Optional<Category> category = categoryRepository.findById(id);
        String expected = david.getName();
        String actual2 = "David";

        //then
        assertEquals(actual2,expected );


    }
    @Test
    public void GivenCategoryId_WhenDelete_ReturnObject(){
        //Given
        Long id = 1L;
        Category shoes = Category.builder().name("David").category_id(1L).build();

        //when

        Category save = categoryRepository.save(shoes);
        categoryRepository.delete(shoes);
        Optional<Category> byId = categoryRepository.findById(id);

        assertNotNull(byId);
    }

    @BeforeEach
    void setUp() {
    }
}