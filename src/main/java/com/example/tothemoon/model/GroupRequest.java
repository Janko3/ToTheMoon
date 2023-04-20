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
public class GroupRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "approved")
    private boolean approved;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "at")
    private LocalDateTime at;
    @ManyToOne
    private Group group;
    @ManyToOne
    private User user;
}
