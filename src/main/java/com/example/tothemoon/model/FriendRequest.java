package com.example.tothemoon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "approved",nullable = false)
    private boolean approved;
    @Column(name = "created_at",nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "at",nullable = true)
    private LocalDateTime at;
    @ManyToOne
    private User sender;
    @ManyToOne
    private User receiver;
}
