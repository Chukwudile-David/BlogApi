package com.example.blogapp.entities;

import com.example.blogapp.enums.Roles;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BlogUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "username",nullable = false, unique = true)
    private String username;
    @Column(name = "password",nullable = false)
    private String password;


    @OneToMany(mappedBy = "blogUser",cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Likes> likes = new ArrayList<>();

    public BlogUser(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
