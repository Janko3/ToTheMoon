package com.example.tothemoon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username",nullable = false)
    private String username;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "email",nullable = false)
    private String email;
    @Column(name = "last_login",nullable = false)
    private LocalDateTime lastLogin;
    @Column(name = "first_name",nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column(name = "profileImagePath")
    private String profileImagePath;
    @OneToMany
    private Set<User>friends = new HashSet<>();
    @OneToMany
    private Set<Post>posts = new HashSet<>();
    @Column(name = "blocked",nullable = false)
    private boolean isBlocked;
    @OneToMany
    private Set<Comment>comments = new HashSet<>();
    @OneToMany
    private Set<Reaction>reactions = new HashSet<>();


}
