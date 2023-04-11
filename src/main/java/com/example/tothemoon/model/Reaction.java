package com.example.tothemoon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "type",nullable = false)
    private EReactionType type;
    @Column(name = "timestamp")
    private LocalDate timestamp;
    @Column(name = "deleted")
    private boolean isDeleted;
    @ManyToOne
    private User user;
    @ManyToOne
    private Comment comment;
    @ManyToOne
    private Post post;
}
