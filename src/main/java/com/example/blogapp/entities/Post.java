package com.example.blogapp.entities;

import com.example.blogapp.enums.Category;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long post_id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "createdAt",nullable = true)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt",nullable = true)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    @Column(name = "comment")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    @Column(name = "likes")
    private List<Likes> likes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "superuser_Id")
    private SuperUser superUser;







}
