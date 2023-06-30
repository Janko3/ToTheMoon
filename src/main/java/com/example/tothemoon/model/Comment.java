package com.example.tothemoon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "text",nullable = false)
    private String text;
    @Column(name = "timestamp",nullable = false)
    private LocalDate timestamp;
    @Column(name = "deleted",nullable = false)
    private boolean isDeleted;
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Comment> replies = new HashSet<>();
    @ManyToOne
    private User user;
    @ManyToOne
    private Post post;
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Reaction> reactions = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Report> reports = new HashSet<>();

}
