package com.example.tothemoon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "content",nullable = false)
    private String content;
    @Column(name = "creation_date",nullable = false)
    private LocalDateTime creationDate;
    @ElementCollection
    @CollectionTable(name = "post_image_paths",joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "image_paths")
    private Set<String> imagePaths = new HashSet<>();
    @ManyToOne
    private User user;
    @Column(name = "deleted",nullable = false)
    private boolean isDeleted;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "post")
    private Set<Comment>comments = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "post")
    private Set<Reaction> reactions = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "post")
    private Set<Report> reports = new HashSet<>();
    @JoinColumn(name = "group_id",referencedColumnName = "id")
    @ManyToOne
    private Group group;
}
