package com.example.tothemoon.model;

import com.example.tothemoon.model.enums.EUserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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
    @Column(name = "role",nullable = false)
    private EUserType role;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User>friends = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Post>posts = new HashSet<>();
    @Column(name = "blocked",nullable = false)
    private boolean isBlocked;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "id")
    private Set<Comment>comments = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Reaction>reactions = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Report>reports = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY)
    private Set<FriendRequest>receivedRequests = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY)
    private Set<FriendRequest>sentRequests = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY)
    private Set<GroupRequest> requests = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY)
    private Set<GroupAdmin>admins = new HashSet<>();
    @OneToMany(fetch =  FetchType.LAZY)
    private Set<Banned>towards = new HashSet<>();

}
