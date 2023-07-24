package com.example.blogapp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long comment_Id;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "createdAt",nullable = true)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt",nullable = true)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "bloguserId")
    private BlogUser blogUser;

    @OneToMany(mappedBy = "comment",cascade = CascadeType.ALL)
    @Column(name = "likes")
    private List<Likes> likes = new ArrayList<>();

    public Comment(Long comment_Id, String description, LocalDateTime createdAt, LocalDateTime updatedAt, Post post) {
        this.comment_Id = comment_Id;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.post = post;
    }
}
