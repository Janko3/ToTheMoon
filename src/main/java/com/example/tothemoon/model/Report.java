package com.example.tothemoon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "reason",nullable = false)
    @Enumerated(EnumType.STRING)
    private EReportReason reason;
    @Column(name = "timestamp",nullable = false)
    private LocalDate timestamp;
    @ManyToOne
    private User byUser;
    @Column(name = "accepted",nullable = false)
    private boolean accepted;

    @Column(name = "deleted",nullable = false)
    private boolean isDeleted;
    @ManyToOne
    private Post post;
    @ManyToOne
    private Comment comment;

}
