package com.example.blogapp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SuperUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long admin_Id;
    @Column(name = "username", nullable = false,unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @OneToMany(mappedBy = "superUser",cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    public SuperUser(Long admin_Id, String username, String password) {
        this.admin_Id = admin_Id;
        this.username = username;
        this.password = password;
    }
}
